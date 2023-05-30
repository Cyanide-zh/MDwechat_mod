package com.blanke.mdwechat

import android.os.Environment
import com.blanke.mdwechat.util.VXPUtils
import com.joshcai.mdwechat.BuildConfig
import java.io.File

/**
 * Created by blanke on 2017/7/29.
 */

object Common {
    val MY_APPLICATION_PACKAGE = "com.joshcai.mdwechat"
    val WECHAT_PACKAGENAME = "com.tencent.mm"
    val MOD_PREFS = "md_wechat_settings_4_0"
    val APP_DIR = "mdwechat"
    val APP_VXP_DIR = "mdwechat_vxp_debug"
    val CONFIG_DIR = "config"
    val CONFIG_WECHAT_DIR = "config" + File.separator + "wechat"
    val CONFIG_VIEW_DIR = "config" + File.separator + "view"

    //    val CONFIG_readme_DIR = "config" + File.separator + "readme"
    val LOGS_DIR = "logs"
    val ICON_DIR = "icon"
    val CONVERSATION_BACKGROUND_FILENAME = "conversation.png"
    val CONTACT_BACKGROUND_FILENAME = "contact.png"
    val DISCOVER_BACKGROUND_FILENAME = "discover.png"
    val SETTINGS_BACKGROUND_FILENAME = "settings.png"
    val CHAT_RED_PACKET_BUBBLE_LEFT_FILENAME = "red_pocket_left.9.png"
    val CHAT_UNOPENED_RED_PACKET_BUBBLE_LEFT_FILENAME = "red_pocket_left_unopened.9.png"
    val CHAT_RED_PACKET_BUBBLE_RIGHT_FILENAME = "red_pocket_right.9.png"
    val CHAT_UNOPENED_RED_PACKET_BUBBLE_RIGHT_FILENAME = "red_pocket_right_unopened.9.png"
    val CHAT_BUBBLE_LEFT_FILENAME = "bubble_left.9.png"
    val CHAT_BUBBLE_RIGHT_FILENAME = "bubble_right.9.png"
    val FILE_NAME_TAB_PREFIX = "tab_icon"
    val FILE_NAME_TAB_BG_PREFIX = "tab_bg"
    val FILE_NAME_CHAT_BG = "tab_bg_chat.png"
    val FILE_NAME_FLOAT_BUTTON = "floatbutton.txt"
    val FILE_NAME_PIC_POSITION = "picposition.txt"

    val isVXPEnv: Boolean by lazy {
        VXPUtils.isVXPEnv()
    }
    val APP_DIR_PATH: String by lazy {
        //         debug VXP 环境下，区分目录
        val appDir = if (isVXPEnv && BuildConfig.DEBUG) APP_VXP_DIR else APP_DIR
//        val appDir = APP_DIR
//            LogUtil.log("isVXPEnv = $isVXPEnv")
//            LogUtil.log("app dir = $appDir")
        Environment.getExternalStorageDirectory().absolutePath + "/Documents" + File.separator + appDir + File.separator
    }

//    val URL_HELP_FLOAT_BUTTON = APP_DIR_PATH + File.separator + CONFIG_readme_DIR + File.separator + "floatbutton_readme.txt"
//    val URL_HELP_ICON = APP_DIR_PATH + File.separator + CONFIG_readme_DIR + File.separator + "icon_readme.txt"
//    val URL_HELP_BUBBLE = APP_DIR_PATH + File.separator + CONFIG_readme_DIR + File.separator + "bubble_readme.txt"
//    val URL_JOIN_GROUP = APP_DIR_PATH + File.separator + CONFIG_readme_DIR + File.separator + "join_group_readme.txt"

}
