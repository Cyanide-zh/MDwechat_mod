package com.blanke.mdwechat.config

import android.graphics.Color
import com.blanke.mdwechat.Version
import com.blanke.mdwechat.WeChatHelper
import com.blanke.mdwechat.WechatGlobal
import com.blankj.utilcode.util.BarUtils
import com.joshcai.mdwechat.BuildConfig

/**
 * Created by blanke on 2017/8/25.
 */

object HookConfig {
    private val key_hook_debug = "key_hook_debug"
    private val key_hook_debug2 = "key_hook_debug2"
    private val debugConfigText = "debugConfigText"
    private val debugConfigText1 = "debugConfigText1"
    private val key_main_text_offset = "key_main_text_offset"
    private val key_tab_layout_offset = "key_tab_layout_offset"
    private val key_resolution = "key_resolution"

    private val key_hook_switch = "hookSwitch"
    private val key_fix_play = "key_fix_play"
    private val key_color_primary = "key_color_primary"
    private val key_color_secondary = "key_color_secondary"
    private val key_color_tertiary = "key_color_tertiary"
    private val key_color_tip = "key_color_tip"
    private val key_color_tip_num = "key_color_tip_num"
    private val key_change_guide_tip_color = "key_change_guide_tip_color"
    private val key_color_tip_in_guide = "key_color_tip_in_guide"
    private val key_color_tip_num_in_guide = "key_color_tip_num_in_guide"
    private val key_hook_conversation_background_alpha = "key_hook_conversation_background_alpha"
    private val key_hook_actionbar_color = "key_hook_actionbar_color"
    private val key_hook_actionbar = "key_hook_actionbar"
    private val key_hook_avatar = "key_hook_avatar"
    private val key_hide_wechatId = "key_hide_wechatId"
    private val key_hook_ripple = "key_hook_ripple"
    private val key_hook_float_button = "key_hook_float_button"
    private val key_hook_float_button_angle = "key_hook_float_button_angle"
    private val key_hook_float_button_color_up = "key_hook_float_button_color_up"
    private val key_hook_float_button_color_is_secondary = "key_hook_float_button_color_is_secondary"
    private val key_hook_search = "key_hook_search"
    private val key_hook_tab_bg = "key_hook_tab_bg"
    private val key_chat_bg_mode = "key_chat_bg_mode"
    private val key_bg_immersion_mode = "key_bg_immersion_mode"
    private val key_hook_bg_immersion = "key_hook_bg_immersion"
    private val key_enable_bg_chat = "key_enable_bg_chat"
    private val key_hook_tab = "key_hook_tab"
    private val key_hide_tab = "key_hide_tab"
    private val key_tab_layout_main_page_filtered = "key_tab_layout_main_page_filtered"
    private val key_tab_layout_filtered = "key_tab_layout_filtered"
    private val key_tab_layout_on_top = "key_tab_layout_on_top"
    private val key_settings_page_transparent = "key_settings_page_transparent"
    private val key_hook_hide_tab = "key_hook_hide_tab"
    private val key_hook_hide_wx_tab = "key_hook_hide_wx_tab"
    private val key_hook_hide_wx_tab_2 = "key_hook_hide_wx_tab_2"
    private val key_hook_hide_wx_tab_3 = "key_hook_hide_wx_tab_3"
    private val key_hook_tab_elevation = "key_hook_tab_elevation"
    private val key_hook_tab_bar = "key_hook_tab_bar"
    private val key_small_tab_bar_size = "key_small_tab_bar_size"
    private val key_hook_menu_game = "key_hook_menu_game"
    private val key_hook_menu_shop = "key_hook_menu_shop"
    private val key_is_play = "key_is_play"
    private val key_hook_bubble_tint = "key_hook_bubble_tint"
    private val key_hook_menu_qrcode = "key_hook_menu_qrcode"
    private val key_hook_menu_shake = "key_hook_menu_shake"
    private val key_hook_menu_near = "key_hook_menu_near"
    private val key_color_ripple = "key_color_ripple"
    private val key_hook_hide_actionbar = "key_hook_hide_actionbar"
    private val key_hook_float_button_move = "key_hook_float_button_move"
    private val key_color_conversation_top = "key_color_conversation_top"

    private val key_hook_remove_appbrand = "key_hook_remove_appbrand"
    private val key_hook_remove_foot_view = "key_hook_remove_foot_view"
    private val key_hook_menu_sns = "key_hook_menu_sns"
    private val key_hook_menu_appbrand = "key_hook_menu_appbrand"
    private val key_chat_label_color = "key_chat_label_color"
    private val key_hook_chat_settings = "key_hook_chat_settings"
    private val key_hook_red_packet = "key_hook_red_packet"
    private val key_hook_chat_label_color = "key_hook_chat_label_color"
    private val key_hook_bubble_in_night_mode = "key_hook_bubble_in_night_mode"
    private val key_hook_bubble_tint_left = "key_hook_bubble_tint_left"
    private val key_hook_bubble_tint_right = "key_hook_bubble_tint_right"
    private val key_hook_chat_text_color_left = "key_hook_chat_text_color_left"
    private val key_hook_chat_text_color_right = "key_hook_chat_text_color_right"
    private val key_hook_red_packet_text_color = "key_hook_red_packet_text_color"
    private val key_hook_statusbar_transparent = "key_hook_statusbar_transparent"
    private val key_hook_log = "key_hook_log"
    private val key_hook_log_xposed = "key_hook_log_xposed"
    private val key_hook_main_textcolor = "key_hook_main_textcolor"
    private val key_hook_appbrand_bg_color = "key_hook_appbrand_bg_color"
    private val key_appbrand_bg_color = "key_appbrand_bg_color"
    private val key_main_textcolor_title = "key_main_textcolor_title"
    private val key_main_textcolor_content = "key_main_textcolor_content"
    private val key_hook_mini_program = "key_hook_mini_program"

    //    private val key_mini_program_title = "key_mini_program_title"
    private val key_hook_scheme_dark = "key_hook_scheme_dark"
    private val key_hook_night_mode = "key_hook_night_mode"

    val is_hook_debug: Boolean
        get() {
            return is_hook_log && WeChatHelper.XMOD_PREFS.getBoolean(key_hook_debug, false)
        }
    val is_hook_debug2: Boolean
        get() {
            return is_hook_log && WeChatHelper.XMOD_PREFS.getBoolean(key_hook_debug2, false)
        }
    val debug_config_text: String
        get() {
            return WeChatHelper.XMOD_PREFS.getString(debugConfigText, "")!!
        }
    val debug_config_text_1: String
        get() {
            return WeChatHelper.XMOD_PREFS.getString(debugConfigText1, "")!!
        }
    val value_main_text_offset: Int
        get() {
            return 0
//        return WeChatHelper.XMOD_PREFS.getString(key_main_text_offset, "0")!!.toInt()
        }
    val value_tab_layout_offset: Int
        get() {
            return if (is_tab_layout_on_top) WeChatHelper.XMOD_PREFS.getString(key_tab_layout_offset, "0")!!.toInt() else 0
        }
    val value_resolution: List<Int> by lazy {
        val text = WeChatHelper.XMOD_PREFS.getString(key_resolution, "-1,-1")!!
        val resolution = text
                .replace(" ", "")
                .replace("，", ",")
                .split(",")
        var out = mutableListOf(-1, -1)
        try {
            if (resolution.count() == 2) {
                out[0] = resolution[0].toInt()
                out[1] = resolution[1].toInt()
            }
        } catch (e: java.lang.Exception) {
            out = mutableListOf(-1, -1)
        }
        out
    }


    val is_fix_play: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_fix_play, false)
        }

    val is_hook_switch: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_switch, true)
        }

    val get_color_primary: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_color_primary, -1184275)
        }
    val get_color_secondary: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_color_secondary, -16537100)
        }
    val get_color_tertiary: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_color_tertiary, -1249295)
        }
    val get_color_tip: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_color_tip, -16537100)
        }
    val get_color_tip_num: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_color_tip_num, Color.WHITE)
        }
    val is_change_guide_tip_color: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_change_guide_tip_color, false)
        }
    val get_color_tip_in_guide: Int
        get() {
            return if (is_change_guide_tip_color) WeChatHelper.XMOD_PREFS.getInt(key_color_tip_in_guide, -16537100) else get_color_tip
        }
    val get_color_tip_num_in_guide: Int
        get() {
            return if (is_change_guide_tip_color) WeChatHelper.XMOD_PREFS.getInt(key_color_tip_num_in_guide, -16537100) else get_color_tip_num
        }
    val get_float_button_color: Int
        get() {
            val a = WeChatHelper.XMOD_PREFS.getBoolean(key_hook_float_button_color_is_secondary, true)
            return if (a) get_color_secondary else get_color_tertiary
        }

    val get_hook_conversation_background_alpha: Int
        get() {
            val alpha = WeChatHelper.XMOD_PREFS.getString(key_hook_conversation_background_alpha, "120")
            return Math.min(255, Math.max(alpha!!.toInt(), 0))
        }
    val is_hook_actionbar_color: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_actionbar_color, false)
        }
    val is_hook_actionbar: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_actionbar, true)
        }

    val is_hook_avatar: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_avatar, true)
        }

    val is_hide_wechatId: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hide_wechatId, true)
        }

    val is_hook_ripple: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_ripple, true)
        }

    val is_hook_float_button: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_float_button, true)
        }

    val value_hook_float_button_angle: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getString(key_hook_float_button_angle, "-135")!!.toInt()
        }
    val is_hook_float_button_color_up: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_float_button_color_up, true)
        }


    val is_hook_search: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_search, false)
        }
    val is_key_hide_tab: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hide_tab, true)
        }
    val is_hook_tab_bg: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_tab_bg, true)
        }
    val is_chat_bg_transparent_mode: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getString(key_chat_bg_mode, "") == "transparentMode"
        }
    val is_bg_preload_mode: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getString(key_bg_immersion_mode, "preloadMode") == "preloadMode"
        }
    val is_hook_bg_immersion: Boolean by lazy {
        val version = WechatGlobal.wxVersion!! >= Version("7.0.10")
        version && is_hook_tab_bg && WeChatHelper.XMOD_PREFS.getBoolean(key_hook_bg_immersion, true)
    }

    val is_enable_bg_chat: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_enable_bg_chat, true)
        }
    val is_hook_tab: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_tab, true)
        }
    val is_tab_layout_main_page_filtered: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_tab_layout_main_page_filtered, false)
        }
    val is_tab_layout_filtered: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_tab_layout_filtered, false)
        }
    val is_tab_layout_on_top: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_tab_layout_on_top, false)
        }
    val is_settings_page_transparent: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_settings_page_transparent, false)
        }
    val is_hook_tab_elevation: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_tab_elevation, false)
        }
    val is_hook_tab_bar: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_tab_bar, true)
        }
    val is_small_tab_bar_size: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_small_tab_bar_size, false)
        }
    val is_hook_hide_tab: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_hide_tab, true)
        }
    val is_hook_hide_wx_tab: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_hide_wx_tab, true)
        }
    val is_hook_hide_wx_tab_2: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_hide_wx_tab_2, false)
        }
    val is_hook_hide_wx_tab_3: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_hide_wx_tab_3, false)
        }
    val is_hook_menu_game: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_menu_game, true)
        }
    val is_hook_menu_shop: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_menu_shop, true)
        }
    val is_play: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_is_play, false)
        }
    val is_hook_bubble_tint: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_bubble_tint, false)
        }
    val is_hook_menu_qrcode: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_menu_qrcode, true)
        }
    val is_hook_menu_shake: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_menu_shake, true)
        }
    val is_hook_menu_near: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_menu_near, true)
        }
    val get_color_ripple: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_color_ripple, Color.GRAY)
        }
    val is_hook_hide_actionbar: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_hide_actionbar, false)
        }
    val is_hook_float_button_move: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_float_button_move, true)
        }
    val get_color_conversation_top: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_color_conversation_top, Color.GRAY)
        }

    val is_hook_remove_appbrand: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_remove_appbrand, false)
        }
    val is_hook_remove_foot_view: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_remove_foot_view, true)
        }
    val is_hook_menu_sns: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_menu_sns, false)
        }
    val is_hook_menu_appbrand: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_menu_appbrand, false)
        }
    val chat_label_color: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_chat_label_color, 10395294)
        }
    val is_hook_chat_settings: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_chat_settings, true)
        }
    val is_hook_red_packet: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_red_packet, true)
        }
    val is_hook_chat_label_color: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_chat_label_color, true)
        }
    val is_hook_bubble_in_night_mode: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_bubble_in_night_mode, false)
        }
    val is_hook_statusbar_transparent: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_statusbar_transparent, true)
        }
    val get_hook_bubble_tint_left: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_hook_bubble_tint_left, Color.WHITE)
        }
    val get_hook_bubble_tint_right: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_hook_bubble_tint_right, Color.WHITE)
        }
    val get_hook_chat_text_color_left: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_hook_chat_text_color_left, Color.BLACK)
        }
    val get_hook_chat_text_color_right: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_hook_chat_text_color_right, Color.BLACK)
        }
    val get_hook_red_packet_text_color: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_hook_red_packet_text_color, Color.BLACK)
        }
    val is_hook_log: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_log, BuildConfig.DEBUG)
        }
    val is_hook_log_xposed: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_log_xposed, false)
        }
    val is_hook_main_textcolor: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_main_textcolor, true)
        }
    val get_main_text_color_title: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_main_textcolor_title, Color.BLACK)
        }
    val is_hook_appbrand_bg_color: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_appbrand_bg_color, true)
        }
    val appbrand_bg_color: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_appbrand_bg_color, Color.parseColor("#80000000"))
        }
    val get_main_text_color_content: Int
        get() {
            return WeChatHelper.XMOD_PREFS.getInt(key_main_textcolor_content, Color.BLACK)
        }
    val is_hook_mini_program: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_mini_program, false)
        }
    val is_hook_scheme_dark: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_scheme_dark, false)
        }

    val is_hook_night_mode: Boolean
        get() {
            return WeChatHelper.XMOD_PREFS.getBoolean(key_hook_night_mode, true)
        }

    //    val value_mini_program_title: String
//        get() {
//            return WeChatHelper.XMOD_PREFS.getString(key_mini_program_title, "点击收起")
//        }
    val statusBarHeight: Int by lazy {
        BarUtils.getStatusBarHeight()
    }
}
