package com.blanke.mdwechat.hookers

import android.app.Application
import com.blanke.mdwechat.Objects
import com.blanke.mdwechat.hookers.base.Hooker
import com.blanke.mdwechat.hookers.base.HookerProvider
import com.blanke.mdwechat.util.LogUtil
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers


object ContextHooker : HookerProvider {
    override fun provideStaticHookers(): List<Hooker>? {
        return listOf(contextHook)
    }

    // 获取 context 用于 toast
    private val contextHook = Hooker {
        val clazz = XposedHelpers.findClass("android.app.Instrumentation", null)
        XposedHelpers.findAndHookMethod(clazz,
                "callApplicationOnCreate",
                Application::class.java,
                object : XC_MethodHook() {

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)
                        if (param.args[0] is Application) {
                            Objects.setContext((param.args[0] as Application).applicationContext)
//                            LogUtil.toast("${Objects.Main.context}")
                        }
                    }
                })
    }
}