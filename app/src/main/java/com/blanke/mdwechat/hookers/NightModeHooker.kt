package com.blanke.mdwechat.hookers

import android.content.res.Configuration
import android.content.res.Resources
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.hookers.base.Hooker
import com.blanke.mdwechat.hookers.base.HookerProvider
import com.blanke.mdwechat.util.NightModeUtils
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

object NightModeHooker : HookerProvider {
    override fun provideStaticHookers(): List<Hooker>? {
        return listOf(getNightModeConfiguration)
    }

    private val getNightModeConfiguration = Hooker {
        XposedHelpers.findAndHookMethod(Resources::class.java, "getConfiguration", object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                val configuration = param.result as Configuration
                val uiMode = configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                if (HookConfig.is_hook_scheme_dark)
                    configuration.uiMode = Configuration.UI_MODE_NIGHT_YES
                else {
                    NightModeUtils.setNightMode(uiMode == Configuration.UI_MODE_NIGHT_YES)
                }
            }
        })
    }
}