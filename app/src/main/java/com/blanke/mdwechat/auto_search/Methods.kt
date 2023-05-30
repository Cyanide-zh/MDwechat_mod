package com.blanke.mdwechat.auto_search

import android.graphics.Bitmap
import com.blanke.mdwechat.CC
import com.blanke.mdwechat.CC.voidd
import com.blanke.mdwechat.auto_search.Classes.AvatarUtils
import com.blanke.mdwechat.auto_search.Classes.ContactFragment
import com.blanke.mdwechat.auto_search.Classes.ConversationWithAppBrandListView
import com.blanke.mdwechat.auto_search.Classes.HomeUI
import com.blanke.mdwechat.auto_search.Classes.MainTabUIPageAdapter
import com.blanke.mdwechat.auto_search.Classes.WXCustomSchemeEntryActivity
import com.blanke.mdwechat.auto_search.Classes.WxViewPager
import com.blanke.mdwechat.util.ReflectionUtil.findMethodsByExactParameters
import java.lang.reflect.Method
import java.lang.reflect.Modifier


object Methods {
    val MainTabUIPageAdapter_getCount: Method?
        get() {
            return MainTabUIPageAdapter!!.getMethod("getCount")
        }

    val MainTabUIPageAdapter_onPageScrolled: Method?
        get() {
            return findMethodsByExactParameters(MainTabUIPageAdapter!!, voidd, CC.Int, Float::class.java, CC.Int)
                    .firstOrNull()?.apply { isAccessible = true }
        }

    val WxViewPager_selectedPage: Method?
        get() {
            return findMethodsByExactParameters(WxViewPager!!, voidd, CC.Int, CC.Boolean, CC.Boolean, CC.Int)
                    .firstOrNull()?.apply { isAccessible = true }
        }
    val WxViewPager_onLayout: Method?
        get() {
            return findMethodsByExactParameters(WxViewPager!!, voidd, CC.Boolean, CC.Int, CC.Int, CC.Int, CC.Int)
                    .firstOrNull()?.apply { isAccessible = true }
        }

//    val LauncherUIBottomTabView_getTabItemView: Method?
//        get() {
//            return findMethodsByExactParameters(LauncherUIBottomTabView!!, LauncherUIBottomTabViewItem!!, CC.Int)
//                    .firstOrNull()?.apply { isAccessible = true }
//        }

    val AvatarUtils_getDefaultAvatarBitmap: Method?
        get() {
            return findMethodsByExactParameters(AvatarUtils!!, Bitmap::class.java)
                    .firstOrNull()?.apply { isAccessible = true }
        }

    val AvatarUtils_getAvatarBitmaps: List<Method>?
        get() {
            return findMethodsByExactParameters(AvatarUtils!!, Bitmap::class.java, CC.String)
        }

    val ConversationWithAppBrandListView_isAppBrandHeaderEnable: Method?
        get() {
            try {
                return findMethodsByExactParameters(ConversationWithAppBrandListView!!, CC.Boolean, CC.Boolean)
                        .firstOrNull()?.apply { isAccessible = true }
            } catch (e: Exception) {
                throw  Exception("ConversationWithAppBrandListView_isAppBrandHeaderEnable")
            }
        }

    // 所有生命周期方法
    val HomeFragment_lifecycles: List<Method>?
        get() {
            return findMethodsByExactParameters(ContactFragment!!.superclass, voidd)
                    .filter { it.modifiers and Modifier.ABSTRACT != 0 }
                    .filter { it.modifiers and Modifier.PROTECTED != 0 }
        }

    val WXCustomSchemeEntryActivity_entry: Method?
        get() {
            return findMethodsByExactParameters(WXCustomSchemeEntryActivity!!, CC.Boolean, CC.Intent)
                    .firstOrNull()?.apply { isAccessible = true }
        }

//    val NightModeClass_getNightModeMethod: Method?
//        get() {
//            if (NightModeClass!!::class.java.name == ClassNotSupported::class.java.name) {
//                return ClassNotSupported::class.java.getMethod("MethodNotSupported")
//            }
//            return findMethodsByExactParameters(NightModeClass!!, CC.String, CC.String, CC.String, CC.Boolean, CC.Boolean).firstOrNull()
//        }
    val HomeUI_setActionBarColor: List<Method>?
        get() {
            return findMethodsByExactParameters(HomeUI!!, null, Float::class.java, Int::class.java, Int::class.java)
        }
}