package com.blanke.mdwechat

import android.app.Activity
import android.content.Context
import android.view.MenuItem
import android.view.View
import com.blanke.mdwechat.util.LogUtil
import com.flyco.tablayout.CommonTabLayout

object Objects {
    object Main {
        var context: Context? = null

        //当前的 activity, 用于朋友圈 actionbar 上色
        var activityNow: Class<*>? = null

        var LauncherUI: Activity? = null
        var LauncherUI_mContentLayout: View? = null
        var HomeUI_mActionBar: Any? = null
        var LauncherUI_mViewPager: View? = null
        var LauncherUI_mTabLayout: CommonTabLayout? = null
        var LauncherUI_mWechatXMenuItem: MenuItem? = null
        var statusView: View? = null
        var actionBar: View? = null

        var tabLayout: CommonTabLayout? = null
        var contactPageFix: View? = null
        var pagePosition = 0

        //小程序下拉之后的填空
        var actionBarAppbrandFix: View? = null
    }

    var setContextCount = 0
    fun setContext(context: Context) {
        if (context == Main.context) {
            return
        }
        setContextCount++
        LogUtil.log("context 已改变, 次数 = $setContextCount, context = $context")
        Main.context = context
    }

//    fun clear() {
//        Main.LauncherUI=null
//        Main.LauncherUI_mContentLayout=null
//        Main.HomeUI_mActionBar=null
//        Main.LauncherUI_mViewPager.clear()
//        Main.LauncherUI_mTabLayout.clear()
//        Main.LauncherUI_mWechatXMenuItem.clear()
//    }
}