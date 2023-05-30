package com.blanke.mdwechat.hookers

import android.util.Log
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.hookers.base.Hooker
import com.blanke.mdwechat.hookers.base.HookerProvider
import com.blanke.mdwechat.util.LogUtil.exportLog
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge

object LogHooker : HookerProvider {

    override fun provideStaticHookers(): List<Hooker>? {
        return listOf(LogEHooker)
//        return listOf(LogIHooker, LogEHooker)
    }

    private val LogIHooker = Hooker {
        XposedBridge.hookAllMethods(Log::class.java, "i", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                if (!HookConfig.is_hook_log) {
                    return
                }
                val msg = param.args[1] as String
                if (msg.contains("mdwechat", true)) {
                    exportLog(msg)
                }
            }
        })
    }

    private val LogEHooker = Hooker {
        XposedBridge.hookAllMethods(Log::class.java, "e", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                if (!HookConfig.is_hook_log) {
                    return
                }
                val msg = param.args[1] as String
                if (msg.contains("mdwechat", true)) {
                    exportLog(msg)
                }
                val tr = param.args[param.args.size - 1]
                if (tr is Throwable) {
                    val msg1 = Log.getStackTraceString(tr)
                    if (msg1.contains("mdwechat", true)) {
                        exportLog(msg1)
                    }
                }
            }
        })
    }

}