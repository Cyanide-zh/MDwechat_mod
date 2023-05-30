package com.blanke.mdwechat.hookers

import android.view.MotionEvent
import android.view.View
import com.blanke.mdwechat.CC
import com.blanke.mdwechat.WechatGlobal
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.hookers.base.Hooker
import com.blanke.mdwechat.hookers.base.HookerProvider
import com.blanke.mdwechat.util.LogUtil
import com.blanke.mdwechat.util.ViewUtils
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

object DebugHooker : HookerProvider {
    override fun provideStaticHookers(): List<Hooker>? {
        return listOf(viewTouchHook)
    }

    private val viewTouchHook = Hooker {
        XposedHelpers.findAndHookMethod(CC.View, "onTouchEvent", CC.MotionEvent, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                val view = param.thisObject as View
                val event = param.args[0] as MotionEvent
                if (event.action == MotionEvent.ACTION_DOWN) {
//                    LogUtil.log("===============")
//                    view.background = ColorDrawable(Color.parseColor("#33333333"))
                    LogUtil.log("点击操作")
                    LogUtil.logViewStackTraces(view)
                    LogUtil.log("-------------------")
                    if (HookConfig.is_hook_debug2) {
                        LogUtil.logViewStackTraces(ViewUtils.getParentViewSafe(view, 35))
                    }
                }
            }
        })
        val XLogSetup = XposedHelpers.findClass("com.tencent.mm.xlog.app.XLogSetup", WechatGlobal.wxLoader)
        XposedBridge.hookAllMethods(XLogSetup, "keep_setupXLog", object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                param.args[5] = true
            }
        })
    }
}