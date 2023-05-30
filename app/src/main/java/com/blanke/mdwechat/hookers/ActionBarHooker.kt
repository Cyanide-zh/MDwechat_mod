package com.blanke.mdwechat.hookers

import android.graphics.drawable.Drawable
import android.view.View
import com.blanke.mdwechat.*
import com.blanke.mdwechat.Classes.ActionBarContainer
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.hookers.base.Hooker
import com.blanke.mdwechat.hookers.base.HookerProvider
import com.blanke.mdwechat.hookers.main.BackgroundImageHook
import com.blanke.mdwechat.util.LogUtil
import com.blanke.mdwechat.util.NightModeUtils
import com.blanke.mdwechat.util.VXPUtils
import com.blanke.mdwechat.util.ViewTreeUtils
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

object ActionBarHooker : HookerProvider {

    override fun provideStaticHookers(): List<Hooker>? {
        val list = mutableListOf(actionBarHooker)
        if (!HookConfig.is_hook_hide_actionbar) {
            list.add(mainPageActionBarHooker)
            if (HookConfig.is_hook_actionbar_color && !VXPUtils.isVXPEnv()) list.add(mainPageActionBarTitleTextColor)
        }
        return list
    }

    private val actionBarHooker = Hooker {
        XposedHelpers.findAndHookMethod(ActionBarContainer, "setPrimaryBackground", Drawable::class.java, object : XC_MethodHook() {
//            override fun beforeHookedMethod(param: MethodHookParam) {
////                return
//                val drawable = param.args[0]
//                var needHook = true
//                val actionBar = param.thisObject as ViewGroup
////                LogUtil.log("====================")
////                LogUtil.logView(actionBar)
////                LogUtil.logViewStackTraces(actionBar)
////                LogUtil.log("====================")
//                if (drawable is ColorDrawable) {
//                    if (drawable.color == Color.parseColor("#F2F2F2")
//                            || drawable.color == Color.parseColor("#FFFAFAFA")
//                            || drawable.color == Color.TRANSPARENT) {
//                        needHook = false
//                    }
//                    if (WechatGlobal.wxVersion!! >= Version("7.0.0")) {
//                        if (actionBar.context::class.java.name == Classes.LauncherUI.name) {
//                            needHook = true
//                        }
//                    }
//                    if (drawable.color == colorPrimaryDrawable.color) {
//                        needHook = false
//                    }
//                    if (needHook) {
//                        param.args[0] = colorPrimaryDrawable
//                    }
//                }
//                val init_elevation = XposedHelpers.getAdditionalInstanceField(actionBar, "init_elevation")
//                if (init_elevation == null) {
//                    actionBar.elevation = 5F
//                    XposedHelpers.setAdditionalInstanceField(actionBar, "init_elevation", true)
//                }
//            }

            override fun afterHookedMethod(param: MethodHookParam) {
                val actionBar = param.thisObject as View
                //朋友圈不改
                if (Objects.Main.activityNow == Classes.SnsTimeLineUI) {
                    return
                }
                if (ViewTreeUtils.equals(ViewTreeRepoThisVersion.ActionBarContainerItem.item, actionBar)) {
                    Objects.Main.actionBar = actionBar
                    if (!HookConfig.is_hook_hide_actionbar) {
                        actionBar.background = NightModeUtils.getForegroundDrawable(actionBar.resources, BackgroundImageHook.getActionBarBitmap(actionBar.measuredHeight, Objects.Main.pagePosition))
                    }
                } else {
                    BackgroundImageHook.setActionBarBitmapInConversations(actionBar)
                }
            }
        })
    }

    //主界面下滑之后需要保持ActionBar不变( in TabLayoutHook.addTabLayout)
    private val mainPageActionBarHooker = Hooker {
        XposedBridge.hookAllMethods(CC.View, "setBackgroundColor", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val view = param.thisObject as View
                //朋友圈不改
                if (Objects.Main.activityNow == Classes.SnsTimeLineUI) {
                    return
                }
                val clazz = view::class.java.name
                if (clazz == ActionBarContainer.name) {
//                    if (ViewTreeUtils.equals(ViewTreeRepoThisVersion.ActionBarContainerItem.item, view)) {
                    Objects.Main.actionBar = view
                    if (!HookConfig.is_hook_hide_actionbar && !(Objects.Main.pagePosition == 3 && WechatGlobal.wxVersion!! >= Version("8.0.0"))) {
                        view.background = NightModeUtils.getForegroundDrawable(view.resources, BackgroundImageHook.getActionBarBitmap(view.measuredHeight, Objects.Main.pagePosition))
                    }
//                    } else {
//                        LogUtil.log("#############################################")
//                        view.background = NightModeUtils.getForegroundDrawable(BackgroundImageUtils.getActionBarBitmap(view.measuredHeight, 0))
//                    }
                }
            }
        })
    }

    private val mainPageActionBarTitleTextColor = Hooker {
        try {
//            LogUtil.logXp("\n\n\n\n\nactionBarTitleTextColor")
            val methods = Methods.HomeUI_setActionBarColor
//            LogUtil.logXp("\n\n\n\n\nmethods$methods")
            methods.forEach {
                XposedHelpers.findAndHookMethod(Classes.HomeUI, it,
                        Float::class.java, Int::class.java, Int::class.java, object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
//                        LogUtil.logXp("${Classes.HomeUI.name}.${it}(\"$ param.args[0]\"\"$ param.args[1]\"\"$param.args[2]\") called.")
                        param.args[1] = NightModeUtils.colorSecondary
                        param.args[2] = NightModeUtils.colorSecondary
//                        LogUtil.logStackTraceXp()
                    }
                })
            }
        } catch (e: Exception) {
            LogUtil.log(e)
        }
    }
}