package com.blanke.mdwechat.hookers.main

import android.view.View
import android.view.ViewGroup
import com.blanke.mdwechat.Objects
import com.blanke.mdwechat.Version
import com.blanke.mdwechat.WechatGlobal
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.util.LogUtil
import com.blanke.mdwechat.util.ViewUtils
import com.blanke.mdwechat.util.waitInvoke
import de.robv.android.xposed.XposedHelpers

object HomeActionBarHook {
    fun fix(viewPagerLinearLayout: ViewGroup) {
        if (WechatGlobal.wxVersion!! < Version("7.0.0")) {
            return
        }
        val is_tab_layout_on_top = !HookConfig.is_key_hide_tab && HookConfig.is_hook_tab && HookConfig.is_tab_layout_on_top
        val cb = { actionHeight: Int ->
            val viewpager = viewPagerLinearLayout.getChildAt(0)
            val layoutParams = viewpager.layoutParams as ViewGroup.MarginLayoutParams
            val offset = HookConfig.value_main_text_offset + HookConfig.value_tab_layout_offset
            LogUtil.log("offset: $offset = value_main_text_offset: ${HookConfig.value_main_text_offset} + value_tab_layout_offset: ${HookConfig.value_tab_layout_offset}")
            if (!HookConfig.is_hook_hide_actionbar && is_tab_layout_on_top) {
                layoutParams.topMargin = actionHeight + offset
            } else if (HookConfig.is_hook_hide_actionbar && !is_tab_layout_on_top) {
                layoutParams.topMargin = -actionHeight + offset
            } else {
                layoutParams.topMargin = offset
            }
            viewpager.layoutParams = layoutParams
            viewpager.requestLayout()
        }
        val mActionBar = Objects.Main.HomeUI_mActionBar!!
        var actionHeight = 0
        var quitFix = false
        waitInvoke(10, true, {
            try {
                actionHeight = XposedHelpers.callMethod(mActionBar, "getHeight") as Int
            } catch (e: Exception) {
                quitFix = true
                LogUtil.log("隐藏 actionBar 失败, 发生错误")
                LogUtil.log("mActionBar = ${mActionBar}")
                LogUtil.log(e)
            }
            quitFix || (actionHeight > 0)
        }, {
            LogUtil.log("actionBarHeight = $actionHeight")
            cb(actionHeight)
            if(quitFix){
                return@waitInvoke
            }
            if (HookConfig.is_hook_hide_actionbar) {
                LogUtil.log("隐藏 actionBar $mActionBar")
                if (mActionBar is View) {
                    mActionBar.visibility = View.GONE
                    mActionBar.layoutParams.height = 0
                    LogUtil.log("隐藏 actionBar 成功")
                } else {
                    LogUtil.log("隐藏 actionBar 失败, actionBar 不是 view")
                }
            }
        })
    }
}