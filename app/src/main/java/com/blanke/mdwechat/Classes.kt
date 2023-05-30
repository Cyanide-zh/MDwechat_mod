package com.blanke.mdwechat

import com.blanke.mdwechat.util.LogUtil.log
import de.robv.android.xposed.XposedHelpers

object Classes {
    fun findClass(className: String?): Class<*>? {
        className ?: return null
        try {
            return XposedHelpers.findClass(className, WechatGlobal.wxLoader)
        } catch (e: Exception) {
            log("$className = null")
            return null
        }
    }

    val HomeUI: Class<*> by WechatGlobal.wxLazy("HomeUI") {
        findClass(WechatGlobal.wxVersionConfig.classes.HomeUI)
    }
    val SnsTimeLineUI: Class<*> by WechatGlobal.wxLazy("SnsTimeLineUI") {
        findClass(WechatGlobal.wxVersionConfig.classes.SnsTimeLineUI)
    }

    var _LauncherUI: Class<*>? = null

    val LauncherUI: Class<*>
        get() {
            if (_LauncherUI == null) {
                setLauncherUI()
            }
            return _LauncherUI!!
        }

    fun setLauncherUI() {
        _LauncherUI = findClass(WechatGlobal.wxVersionConfig.classes.LauncherUI)!!
    }

    val WxViewPager: Class<*> by WechatGlobal.wxLazy("WxViewPager") {
        findClass(WechatGlobal.wxVersionConfig.classes.WxViewPager)
    }

    val CustomViewPager: Class<*> by WechatGlobal.wxLazy("CustomViewPager") {
        findClass(WechatGlobal.wxVersionConfig.classes.CustomViewPager)
    }

    val MainTabUI: Class<*> by WechatGlobal.wxLazy("MainTabUI") {
        findClass(WechatGlobal.wxVersionConfig.classes.MainTabUI)
    }

    val MainTabUIPageAdapter: Class<*> by WechatGlobal.wxLazy("MainTabUIPageAdapter") {
        findClass(WechatGlobal.wxVersionConfig.classes.MainTabUIPageAdapter)
    }

    val LauncherUIBottomTabView: Class<*> by WechatGlobal.wxLazy("LauncherUIBottomTabView") {
        findClass(WechatGlobal.wxVersionConfig.classes.LauncherUIBottomTabView)
    }

//    val TabIconView: Class<*> by WechatGlobal.wxLazy("TabIconView") {
//        findClass(WechatGlobal.wxVersionConfig.classes.TabIconView)
//    }

//    val ThreadExecutor: Class<*> by WechatGlobal.wxLazy("ThreadExecutor") {
//        findClass(WechatGlobal.wxVersionConfig.classes.ThreadExecutor)
//    }

    val LauncherUIBottomTabViewItem: Class<*> by WechatGlobal.wxLazy("LauncherUIBottomTabViewItem") {
        findClass(WechatGlobal.wxVersionConfig.classes.LauncherUIBottomTabViewItem)
    }

    val ActionBarContainer: Class<*> by WechatGlobal.wxLazy("ActionBarContainer") {
        findClass(WechatGlobal.wxVersionConfig.classes.ActionBarContainer)
    }

//    val ScrollingTabContainerView: Class<*> by WechatGlobal.wxLazy("ScrollingTabContainerView") {
//        findClass(WechatGlobal.wxVersionConfig.classes.ScrollingTabContainerView)
//    }

    val PhoneWindow: Class<*> by WechatGlobal.wxLazy("PhoneWindow") {
        findClass(WechatGlobal.wxVersionConfig.classes.PhoneWindow)
    }

    val AvatarUtils: Class<*> by WechatGlobal.wxLazy("AvatarUtils") {
        findClass(WechatGlobal.wxVersionConfig.classes.AvatarUtils)
    }

    val NoDrawingCacheLinearLayout: Class<*> by WechatGlobal.wxLazy("NoDrawingCacheLinearLayout") {
        findClass(WechatGlobal.wxVersionConfig.classes.NoDrawingCacheLinearLayout)
    }

    val ConversationWithAppBrandListView: Class<*> by WechatGlobal.wxLazy("ConversationWithAppBrandListView") {
        findClass(WechatGlobal.wxVersionConfig.classes.ConversationWithAppBrandListView)
    }

    val ConversationListView: Class<*> by WechatGlobal.wxLazy("ConversationListView") {
        findClass(WechatGlobal.wxVersionConfig.classes.ConversationListView)
    }

    val ConversationFragment: Class<*> by WechatGlobal.wxLazy("ConversationFragment") {
        findClass(WechatGlobal.wxVersionConfig.classes.ConversationFragment)
    }

    val ContactFragment: Class<*> by WechatGlobal.wxLazy("ContactFragment") {
        findClass(WechatGlobal.wxVersionConfig.classes.ContactFragment)
    }

    val FragmentActivity: Class<*> by WechatGlobal.wxLazy("FragmentActivity") {
        findClass("android.support.v4.app.FragmentActivity")
    }

    val Fragment: Class<*> by WechatGlobal.wxLazy("Fragment") {
        if (WechatGlobal.wxVersion!! < Version("8.0.3")) {
            findClass("android.support.v4.app.Fragment")
        } else {
            findClass("androidx.fragment.app.Fragment")
        }
    }

    val DiscoverFragment: Class<*> by WechatGlobal.wxLazy("DiscoverFragment") {
        findClass(WechatGlobal.wxVersionConfig.classes.DiscoverFragment)
    }

    val SettingsFragment: Class<*> by WechatGlobal.wxLazy("SettingsFragment") {
        findClass(WechatGlobal.wxVersionConfig.classes.SettingsFragment)
    }

    val PreferenceFragment: Class<*> by WechatGlobal.wxLazy("PreferenceFragment") {
        findClass(WechatGlobal.wxVersionConfig.classes.PreferenceFragment)
    }

    val NoMeasuredTextView: Class<*> by WechatGlobal.wxLazy("NoMeasuredTextView") {
        findClass(WechatGlobal.wxVersionConfig.classes.NoMeasuredTextView)
    }

    val ActionMenuView: Class<*> by WechatGlobal.wxLazy("ActionMenuView") {
        findClass(WechatGlobal.wxVersionConfig.classes.ActionMenuView)
    }

    val WXCustomSchemeEntryActivity: Class<*> by WechatGlobal.wxLazy("WXCustomSchemeEntryActivity") {
        findClass(WechatGlobal.wxVersionConfig.classes.WXCustomSchemeEntryActivity)
    }

    val RemittanceAdapterUI: Class<*> by WechatGlobal.wxLazy("RemittanceAdapterUI") {
        findClass("com.tencent.mm.plugin.remittance.ui.RemittanceAdapterUI")
    }

    val Toolbar: Class<*> by WechatGlobal.wxLazy("Toolbar") {
        findClass("android.support.v7.widget.Toolbar")
    }
    val ImageGalleryUI: Class<*> by WechatGlobal.wxLazy("ImageGalleryUI") {
        findClass("com.tencent.mm.ui.chatting.gallery.ImageGalleryUI")
    }
    val MMRecordUI: Class<*> by WechatGlobal.wxLazy("MMRecordUI") {
        findClass("com.tencent.mm.plugin.recordvideo.activity.MMRecordUI")
    }
    val SnsBrowseUI: Class<*> by WechatGlobal.wxLazy("SnsBrowseUI") {
        findClass("com.tencent.mm.plugin.sns.ui.SnsBrowseUI")
    }
    val SnsOnlineVideoActivity: Class<*> by WechatGlobal.wxLazy("SnsOnlineVideoActivity") {
        findClass("com.tencent.mm.plugin.sns.ui.SnsOnlineVideoActivity")
    }
//    val NightModeClass: Class<*> by WechatGlobal.wxLazy("NightModeClass") {
//        findClass(WechatGlobal.wxVersionConfig.classes.NightModeClass)
//    }
}