package com.blanke.mdwechat.util

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.blanke.mdwechat.WeChatHelper
import com.blanke.mdwechat.config.HookConfig

object NightModeUtils {
    private var _isNightMode = false

    //    微信8.0 第四页判断底色用
    private var _isWechatNightMode = true
    fun isNightMode(): Boolean {
        return _isNightMode
    }

    fun isWechatNightMode(): Boolean {
        return _isWechatNightMode
    }

    fun setNightMode(b: Boolean) {
        _isNightMode = HookConfig.is_hook_night_mode && b
        _isWechatNightMode = HookConfig.is_hook_scheme_dark || b
    }

    fun isHookMainColor(): Boolean {
        return HookConfig.is_hook_main_textcolor
    }

//    fun getBackgroundDrawable(defaultDrawable: Drawable?): Drawable {
//        return if (isNightMode()) WeChatHelper.drawableDark else defaultDrawable
//                ?: WeChatHelper.drawableWhite
//    }

    fun getBackgroundDrawable(res: Resources, defaultDrawable: Bitmap?): Drawable {
        return when {
            isNightMode() -> WeChatHelper.drawableDark
            defaultDrawable != null -> BitmapDrawable(res, defaultDrawable)
            else -> WeChatHelper.drawableWhite
        }
    }

    fun getForegroundDrawable(res: Resources?, defaultDrawable: Bitmap?): Drawable {
        return when {
            isNightMode() -> WeChatHelper.colorPrimaryDrawableDark
            defaultDrawable != null -> BitmapDrawable(res, defaultDrawable)
            else -> WeChatHelper.colorPrimaryDrawable
        }
    }

    fun getContentTextColor(): Int {
        return getTextColor(HookConfig.get_main_text_color_content)
    }

    fun getTitleTextColor(): Int {
        return getTextColor(HookConfig.get_main_text_color_title)
    }

    private fun getTextColor(defaultColor: Int?): Int {
        return when {
            isNightMode() -> WeChatHelper.colorDarkWhite
            !isHookMainColor() -> Color.BLACK
            else -> defaultColor ?: Color.BLACK
        }
    }

    val colorPrimary: Int
        get() {
            return if (isNightMode()) WeChatHelper.colorDarkPrimary else HookConfig.get_color_primary
        }

    val colorSecondary: Int
        get() {
            return if (isNightMode()) WeChatHelper.colorDarkWhite else HookConfig.get_color_secondary

        }
    val colorTeritary: Int
        get() {
            return if (isNightMode()) WeChatHelper.colorDarkWhite else HookConfig.get_color_tertiary
        }
    val colorFloatButton: Int
        get() {
            return if (isNightMode()) WeChatHelper.colorDarkWhite else HookConfig.get_float_button_color

        }
    val colorTip: Int
        get() {
            return HookConfig.get_color_tip
        }
    val is_hook_tab_bar: Boolean
        get() {
            return HookConfig.is_hook_tab_bar || _isNightMode
        }
    val is_tab_layout_main_page_filtered: Boolean
        get() {
            return HookConfig.is_tab_layout_main_page_filtered || _isNightMode
        }
    val is_tab_layout_filtered: Boolean
        get() {
            return HookConfig.is_tab_layout_filtered || _isNightMode
        }
}