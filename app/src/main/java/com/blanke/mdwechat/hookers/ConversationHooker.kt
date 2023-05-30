package com.blanke.mdwechat.hookers

import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ListView
import com.blanke.mdwechat.CC
import com.blanke.mdwechat.Classes
import com.blanke.mdwechat.Classes.ConversationListView
import com.blanke.mdwechat.Classes.ConversationWithAppBrandListView
import com.blanke.mdwechat.Fields.ConversationFragment_mListView
import com.blanke.mdwechat.Methods.ConversationWithAppBrandListView_isAppBrandHeaderEnable
import com.blanke.mdwechat.Version
import com.blanke.mdwechat.WeChatHelper.defaultImageRippleDrawable
import com.blanke.mdwechat.WechatGlobal
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.hookers.base.Hooker
import com.blanke.mdwechat.hookers.base.HookerProvider
import com.blanke.mdwechat.hookers.main.BackgroundImageHook
import com.blanke.mdwechat.util.LogUtil
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

//小程序字体颜色在 ListViewHooker 中
object ConversationHooker : HookerProvider {
    const val keyInit = "key_init"

    override fun provideStaticHookers(): List<Hooker>? {
        return listOf(
                resumeHook,
                disableAppBrandHook,
                headViewHook
        )
    }

    private val resumeHook = Hooker {
        XposedHelpers.findAndHookMethod(Classes.Fragment, "performResume", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam?) {
//                LogUtil.log("Fragment.performResume f=${param!!.thisObject::class.java.name}")
                val fragment = param?.thisObject ?: return
                if (fragment.javaClass.name != Classes.ConversationFragment.name) {
                    return
                }
//                LogUtil.logSuperClasses(param!!.thisObject::class.java)
                val isInit = XposedHelpers.getAdditionalInstanceField(fragment, keyInit)
                if (isInit != null) {
                    LogUtil.log("ConversationFragment 已经hook过")
                    return
                }
                XposedHelpers.setAdditionalInstanceField(fragment, keyInit, true)
                if (HookConfig.is_hook_tab_bg) {
                    init(fragment)
                }
            }

            private fun init(fragment: Any) {
                val listView = ConversationFragment_mListView.get(fragment)
                if (listView != null && listView is View) {
                    BackgroundImageHook.setConversationBitmap(listView)
                }
            }
        })
        XposedBridge.hookAllMethods(CC.View, "setBackgroundColor", object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val view = param.thisObject as View
                val clazz = view::class.java.name
//                    LogUtil.logXp("=====================")
//                    LogUtil.logViewStackTracesXp(ViewUtils.getParentViewSafe(view, 15))
//                    LogUtil.logStackTraceXp()
//                    LogUtil.logXp("=====================")
                when (clazz) {
                    ConversationListView.name -> if (WechatGlobal.wxVersion!! >= Version("7.0.3") && HookConfig.is_hook_tab_bg) {
                        param.result = 0
                    }
                }
            }
        })
        if (WechatGlobal.wxVersion!! >= Version("7.0.4")) {
            XposedBridge.hookAllMethods(CC.View, "setBackground", object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    val view = param.thisObject as View
                    val pView = view.parent
                    if ((pView is View) && (pView::class.java.name == ConversationListView.name)) {
                        param.result = null
                    }
                }
            })
        }
    }

    //7.0.3以上无法使用
    private val disableAppBrandHook = Hooker {
        if (WechatGlobal.wxVersion!! <= Version("7.0.3")) {
            XposedHelpers.findAndHookMethod(ConversationWithAppBrandListView,
                    ConversationWithAppBrandListView_isAppBrandHeaderEnable.name, CC.Boolean, object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    if (HookConfig.is_hook_remove_appbrand) {
                        try {
                            if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                val listView = param.thisObject as ListView
                                val mHeaderViewInfos = XposedHelpers.getObjectField(listView, "mHeaderViewInfos") as List<*>
                                if (mHeaderViewInfos.isNotEmpty()) {
                                    val firstHeadView = mHeaderViewInfos[0] as ListView.FixedViewInfo
                                    val mAppBrandDesktopHalfContainer = firstHeadView.view as ViewGroup
//                                LogUtil.log("firstHeadView=${firstHeadView.view}")
                                    if (mAppBrandDesktopHalfContainer::class.java.name.contains("AppBrandDesktopHalfContainer")) {
                                        mAppBrandDesktopHalfContainer.getChildAt(1)?.visibility = View.GONE
                                    }
                                }
                            }
                        } catch (t: Throwable) {
                            LogUtil.log(t)
                        }
                        param.result = false
                    }
                }
            })
        }
    }

    private val headViewHook = Hooker {
        XposedHelpers.findAndHookMethod(CC.ListView, "addHeaderView", CC.View, CC.Object, CC.Boolean,
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        val listView = param?.thisObject
                        if (WechatGlobal.wxVersion!! < Version("7.0.3")) {
                            if (listView?.javaClass?.name != ConversationWithAppBrandListView.name) {
                                return
                            }
                        } else {
                            if (listView?.javaClass?.name != ConversationListView.name) {
                                return
                            }
                        }
                        val view = param?.args!![0] as View
//                        LogUtil.log("ConversationWithAppBrandListView addHeadView = ${view}")
                        if (view is ViewGroup && view.getChildAt(0) != null) {
                            LogUtil.logOnlyOnce("addHeaderView")
                            view.getChildAt(0).viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                                override fun onGlobalLayout() {
                                    val oldBackground = view.getChildAt(0).background
                                    if (oldBackground == defaultImageRippleDrawable) {
                                        view.getChildAt(0).viewTreeObserver.removeOnGlobalLayoutListener(this)
                                    } else {
                                        view.getChildAt(0).background = defaultImageRippleDrawable
                                    }
                                }
                            })
                            LogUtil.logOnlyOnce("addHeaderView Done")
                        }
                    }
                })
    }
}