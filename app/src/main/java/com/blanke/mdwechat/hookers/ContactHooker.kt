package com.blanke.mdwechat.hookers

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.blanke.mdwechat.*
import com.blanke.mdwechat.Fields.ContactFragment_mListView
import com.blanke.mdwechat.WeChatHelper.drawableTransparent
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.hookers.base.Hooker
import com.blanke.mdwechat.hookers.base.HookerProvider
import com.blanke.mdwechat.hookers.main.BackgroundImageHook
import com.blanke.mdwechat.util.LogUtil
import com.blanke.mdwechat.util.ViewTreeUtils
import com.blanke.mdwechat.util.ViewUtils
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XposedHelpers.getObjectField
import com.blanke.mdwechat.ViewTreeRepoThisVersion as VTTV

object ContactHooker : HookerProvider {
    const val keyInit = "key_init"


    override fun provideStaticHookers(): List<Hooker>? {
        return if (WechatGlobal.wxVersion!! < Version("8.0.14")) {
            listOf(resumeHook)
        } else {
            listOf(wxViewPagerHook, backgroundColorResetHook, contactItermsHook)
        }
    }

    private val wxViewPagerHook = Hooker {
        try {
            XposedHelpers.findAndHookMethod(Classes.findClass("com.tencent.mm.view.recyclerview.WxRecyclerView"),
                    "onLayout", CC.Boolean, CC.Int, CC.Int, CC.Int, CC.Int, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    val WxRecyclerView = param?.thisObject ?: return
                    if (WxRecyclerView !is ViewGroup) {
                        return
                    }

                    val contactView = ViewUtils.getParentViewSafe(WxRecyclerView, 5) as ViewGroup
                    if (!ViewTreeUtils.equals(VTTV.ContactLayoutListenerViewItem.item, contactView)) {
                        return
                    }
                    LogUtil.log("获取联系人界面成功.")

                    //背景
                    //背景遮罩
                    VTTV.ContactLayoutListenerViewItem.treeStacks["backgroundMask"]?.apply {
                        val backgroundITransparent = ViewUtils.getChildView1(contactView, this) as View
                        backgroundITransparent.setBackgroundColor(Color.TRANSPARENT)
                    }
                    //背景
                    if (HookConfig.is_hook_tab_bg) {
                        VTTV.ContactLayoutListenerViewItem.treeStacks["backgroundImage"]?.apply {
                            val backgroundImage = ViewUtils.getChildView1(contactView, this) as View
                            BackgroundImageHook.setContactBitmap(backgroundImage)
                        }
                    }

                    LogUtil.logOnlyOnce("ContactFragment Done")

                    //列表第一项(头)
                    WxRecyclerView.background = drawableTransparent

                    VTTV.ContactLayoutListenerViewItem.treeStacks["WxRecyclerView_ContactHeaderItem"]?.apply {
                        val ContactHeaderItem = ViewUtils.getChildView1(WxRecyclerView, this) as View
                        ContactHeaderItem.background = drawableTransparent

                        if (ContactHeaderItem is ViewGroup) {
                            ListViewHooker.setContactHeaderItemTop(ContactHeaderItem)
                        }
                        ListViewHooker.setContactHeaderItem(ContactHeaderItem)
                        (ViewUtils.getChildView1(contactView, intArrayOf(0, 1, 0)) as View).background = drawableTransparent
                    }
                }
            })
        } catch (e: Exception) {
            LogUtil.log(e)
        }
    }

    // 8.0.14 起微信修改联系人渲染样式之后经常会在滑动时重置背景
    private val backgroundColorResetHook = Hooker {
        try {
            Methods.findMethodsByName(
                    Classes.findClass("com.tencent.mm.ui.widget.pulldown.WeUIBounceViewV2"),
                    listOf("setStart2EndBgColorByActionBar", "setEnd2StartBgColorByNavigationBar",
                            "setStart2EndBgColor", "setEnd2StartBgColor", "setBgColor")
                    , CC.Int
            )
                    .forEach {
                        try {
                            XposedHelpers.findAndHookMethod(
                                    Classes.findClass("com.tencent.mm.ui.widget.pulldown.WeUIBounceViewV2"),
                                    it.name
                                    , CC.Int
                                    , object : XC_MethodHook() {
                                override fun beforeHookedMethod(param: MethodHookParam?) {
                                    param?.args?.apply {
                                        this[0] = Color.TRANSPARENT
                                    }
                                }
                            })
                        } catch (e: Exception) {
                            LogUtil.log(e)
                        }
                    }
        } catch (e: NoSuchMethodException) {
            LogUtil.log("find method error")
            LogUtil.log(e)
        } catch (e: Exception) {
            LogUtil.log("WHAT THE FUCK?")
            LogUtil.log(e)
        }
    }

    private val contactItermsHook = Hooker {
        try {
            XposedHelpers.findAndHookMethod(Classes.findClass("com.tencent.mm.view.recyclerview.WxRecyclerView"),
                    "onViewAdded", CC.View, object : XC_MethodHook() {
                //                        "onLayout", CC.Boolean,CC.Int,CC.Int,CC.Int, CC.Int, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    val view = param?.args?.get(0) ?: return
                    if (view !is ViewGroup) {
                        return
                    }
                    // 联系人列表
                    if (ViewTreeUtils.equals(VTTV.ContactListViewItem.item, view)) {
                        ListViewHooker.setContactListViewItem(view)
                    }
                }
            })
        } catch (e: NoSuchMethodException) {
            LogUtil.log("find method error")
            LogUtil.log(e)
        } catch (e: Exception) {
            LogUtil.log("WHAT THE FUCK?")
            LogUtil.log(e)
        }
    }

    private val resumeHook = Hooker {
        Methods.HomeFragment_lifecycles.forEach {
            XposedHelpers.findAndHookMethod(Classes.ContactFragment, it.name, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    val fragment = param?.thisObject ?: return
//                    LogUtil.log("ContactFragment fragment=$fragment,${Classes.ContactFragment.name}")
                    val isInit = XposedHelpers.getAdditionalInstanceField(fragment, keyInit)
                    if (isInit != null) {
                        LogUtil.log("ContactFragment 已经hook过")
                        return
                    }
                    init(fragment)
                }

                private fun init(fragment: Any) {
                    val listView = ContactFragment_mListView.get(fragment)
                    if (listView != null && listView is ListView) {
                        LogUtil.logOnlyOnce("ContactFragment Done")
                        XposedHelpers.setAdditionalInstanceField(fragment, keyInit, true)
                        if (HookConfig.is_hook_tab_bg) {
                            BackgroundImageHook.setContactBitmap(listView)
                        }
//                        LogUtil.log("ContactFragment listview= $listView, ${listView.javaClass.name}")
                        if (listView.headerViewsCount > 0) {
                            val mHeaderViewInfos = getObjectField(listView, "mHeaderViewInfos") as ArrayList<*>
                            for (j in 0 until mHeaderViewInfos.size) {
                                val header = (mHeaderViewInfos[j] as ListView.FixedViewInfo).view
                                if (header != null) {
//                                        printViewTree(header, 0)
                                    if (header is ViewGroup) {
                                        ListViewHooker.setContactHeaderItemTop(header)
                                    }
                                }
                            }
                        }
                        LogUtil.logOnlyOnce("ContactFragment")
                    }
                }
            })
        }
    }
}