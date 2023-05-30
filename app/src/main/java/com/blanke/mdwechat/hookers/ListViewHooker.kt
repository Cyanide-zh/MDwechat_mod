package com.blanke.mdwechat.hookers

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.blanke.mdwechat.*
import com.blanke.mdwechat.WeChatHelper.defaultImageRippleDrawable
import com.blanke.mdwechat.WeChatHelper.drawableTransparent
import com.blanke.mdwechat.config.AppCustomConfig
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.hookers.base.Hooker
import com.blanke.mdwechat.hookers.base.HookerProvider
import com.blanke.mdwechat.hookers.main.BackgroundImageHook
import com.blanke.mdwechat.util.LogUtil
import com.blanke.mdwechat.util.NightModeUtils
import com.blanke.mdwechat.util.ViewTreeUtils
import com.blanke.mdwechat.util.ViewUtils
import com.gcssloop.widget.RCRelativeLayout
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import com.blanke.mdwechat.ViewTreeRepoThisVersion as VTTV

object ListViewHooker : HookerProvider {
    private var wechatId: CharSequence = ""
    private val excludeContext = arrayOf("com.tencent.mm.plugin.mall.ui.MallIndexUI")

    private val titleTextColor: Int
        get() {
            return NightModeUtils.getTitleTextColor()
        }
    private val summaryTextColor: Int
        get() {
            return NightModeUtils.getContentTextColor()
        }

    private val isHookTextColor: Boolean
        get() {
            return HookConfig.is_hook_main_textcolor || NightModeUtils.isNightMode()
        }

    override fun provideStaticHookers(): List<Hooker>? {
        return listOf(listViewHook)
    }

    private val listViewHook = Hooker {
        XposedHelpers.findAndHookMethod(AbsListView::class.java, "setSelector", Drawable::class.java, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam?) {
                param?.args!![0] = drawableTransparent
            }
        })
        XposedHelpers.findAndHookMethod(AbsListView::class.java, "obtainView", CC.Int, BooleanArray::class.java, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam?) {
                try {
                    val view = param?.result as View
                    val context = view.context
                    val tmp = excludeContext.find { context::class.java.name.contains(it) }
                    if (tmp != null) {
                        return
                    }


                    // 按照使用频率重排序
                    val hookBubbles: Boolean = ((!NightModeUtils.isNightMode()) || HookConfig.is_hook_bubble_in_night_mode) && HookConfig.is_hook_chat_settings
                    //气泡
                    // 聊天消息 item
                    if (ViewTreeUtils.equals(VTTV.ChatRightMessageItem.item, view)) {
                        LogUtil.logOnlyOnce("ListViewHooker.ChatRightMessageItem")
                        if (hookBubbles) {

                            //chat_label
                            if (HookConfig.is_hook_chat_label_color)
                                VTTV.ChatRightMessageItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(HookConfig.chat_label_color)
                                }

                            val chatMsgRightTextColor = HookConfig.get_hook_chat_text_color_right
                            VTTV.ChatRightMessageItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as View
//                    log("msgView=$msgView")
                                XposedHelpers.callMethod(msgView, "setTextColor", chatMsgRightTextColor)
                                XposedHelpers.callMethod(msgView, "setLinkTextColor", chatMsgRightTextColor)
                                XposedHelpers.callMethod(msgView, "setHintTextColor", chatMsgRightTextColor)
//                    val mText = XposedHelpers.getObjectField(msgView, "mText")
//                    log("msg right text=$mText")
                                val bubble = WeChatHelper.getRightBubble(msgView.resources)
                                msgView.background = bubble
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    msgView.setPadding(30, 25, 45, 25)
                                }
                            }
                        }
                    } else if (ViewTreeUtils.equals(VTTV.ChatLeftMessageItem.item, view)) {
                        LogUtil.logOnlyOnce("ListViewHooker.ChatLeftMessageItem")
                        if (hookBubbles) {

                            if (HookConfig.is_hook_chat_label_color) {
                                //chat_label
                                VTTV.ChatLeftMessageItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(HookConfig.chat_label_color)
                                }
                                //nickNameView
                                VTTV.ChatLeftMessageItem.treeStacks["nickNameView"]?.apply {
                                    val nickNameView = ViewUtils.getChildView1(view, this) as TextView
                                    nickNameView.setTextColor(HookConfig.chat_label_color)
                                }
                            }
                            val chatMsgLeftTextColor = HookConfig.get_hook_chat_text_color_left
                            VTTV.ChatLeftMessageItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as View
//                                LogUtil.log("=======start=========")
//                                LogUtil.log("msgView=$msgView")
//                                val mText = XposedHelpers.getObjectField(msgView, "mText")
//                                LogUtil.log("msg left text=$mText")
//                                LogUtil.logView(view)
//                                LogUtil.logViewStackTraces(ViewUtils.getParentViewSafe(view, 2))
//                                LogUtil.log("=======end=========")
                                XposedHelpers.callMethod(msgView, "setTextColor", chatMsgLeftTextColor)
                                XposedHelpers.callMethod(msgView, "setLinkTextColor", chatMsgLeftTextColor)
                                XposedHelpers.callMethod(msgView, "setHintTextColor", chatMsgLeftTextColor)
                                // 聊天气泡
                                val bubble = WeChatHelper.getLeftBubble(msgView.resources)
                                msgView.background = bubble
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    msgView.setPadding(45, 25, 30, 25)
                                }
                            }
                        }
                    }

                    // 聊天消息 audio
                    else if (ViewTreeUtils.equals(VTTV.ChatRightAudioMessageItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatRightAudioMessageItem")
                            val chatMsgTextColor = HookConfig.get_hook_chat_text_color_right

                            //chat_label
                            if (HookConfig.is_hook_chat_label_color)
                                VTTV.ChatRightAudioMessageItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(HookConfig.chat_label_color)
                                }
                            //audioLengthView
                            VTTV.ChatRightAudioMessageItem.treeStacks["audioLengthView"]?.apply {
                                val audioLengthView = ViewUtils.getChildView1(view, this) as TextView
                                audioLengthView.setTextColor(chatMsgTextColor)
                            }

                            VTTV.ChatRightAudioMessageItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                //播放语音时的view
                                VTTV.ChatRightAudioMessageItem.treeStacks["msgAnimView"]?.apply {
                                    val msgAnimView = ViewUtils.getChildView1(view, this) as View
                                    val bubble = WeChatHelper.getRightBubble(msgView.resources)
                                    msgView.background = null
                                    ViewUtils.getParentViewSafe(msgView, 1).background = bubble
                                    msgAnimView.background = null
                                    if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                        msgView.setPadding(30, 25, 45, 25)
//                                msgAnimView.setPadding(30, 25, 45, 25)
                                    }
                                }

//                            //喇叭图标
                                val speakerIcon = msgView.compoundDrawables[2]
                                speakerIcon.setColorFilter(chatMsgTextColor, PorterDuff.Mode.SRC_ATOP)
                                msgView.setCompoundDrawables(null, null, speakerIcon, null)
                            }
                        }
                    } else if (ViewTreeUtils.equals(VTTV.ChatLeftAudioMessageItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatLeftAudioMessageItem")
                            val chatMsgTextColor = HookConfig.get_hook_chat_text_color_left

                            if (HookConfig.is_hook_chat_label_color) {
                                //chat_label
                                VTTV.ChatLeftAudioMessageItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(HookConfig.chat_label_color)
                                }
                                //nickNameView
                                VTTV.ChatLeftAudioMessageItem.treeStacks["nickNameView"]?.apply {
                                    val nickNameView = ViewUtils.getChildView1(view, this) as TextView
                                    nickNameView.setTextColor(HookConfig.chat_label_color)
                                }
                            }
                            //audioLengthView
                            VTTV.ChatLeftAudioMessageItem.treeStacks["audioLengthView"]?.apply {
                                val audioLengthView = ViewUtils.getChildView1(view, this) as TextView
                                audioLengthView.setTextColor(chatMsgTextColor)
                            }

                            VTTV.ChatLeftAudioMessageItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                VTTV.ChatLeftAudioMessageItem.treeStacks["msgAnimView"]?.apply {
                                    val msgAnimView = ViewUtils.getChildView1(view, this) as View
                                    val bubble = WeChatHelper.getLeftBubble(msgView.resources)
                                    msgView.background = null
                                    ViewUtils.getParentViewSafe(msgView, 1).background = bubble
                                    msgAnimView.background = null
                                    if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                        msgView.setPadding(45, 25, 30, 25)
//                                msgAnimView.setPadding(45, 25, 30, 25)
                                    }
//                            //喇叭图标
                                    val speakerIcon = msgView.compoundDrawables[0]
                                    speakerIcon.setColorFilter(chatMsgTextColor, PorterDuff.Mode.SRC_ATOP)
                                    msgView.setCompoundDrawables(speakerIcon, null, null, null)
                                }
                            }
                        }
                    }

                    // 通话消息
                    else if (ViewTreeUtils.equals(VTTV.ChatRightCallMessageItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatRightCallMessageItem")
                            val chatMsgTextColor = HookConfig.get_hook_chat_text_color_right
                            //chat_label
                            if (HookConfig.is_hook_chat_label_color) {
                                VTTV.ChatRightCallMessageItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(HookConfig.chat_label_color)
                                }
                            }
                            VTTV.ChatRightCallMessageItem.treeStacks["bgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as View
                                val bubble = WeChatHelper.getRightBubble(msgView.resources)
                                msgView.background = bubble
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    msgView.setPadding(30, 25, 45, 25)
                                }
                            }
                            //icon
                            VTTV.ChatRightCallMessageItem.treeStacks["icon"]?.apply {
                                val icon = ViewUtils.getChildView1(view, this) as LinearLayout
                                val speakerIcon = icon.background
                                speakerIcon.setColorFilter(chatMsgTextColor, PorterDuff.Mode.SRC_ATOP)
                                icon.background = speakerIcon
                            }
                            //msgView
                            VTTV.ChatRightCallMessageItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                msgView.setTextColor(chatMsgTextColor)
                            }
                        }
                    } else if (ViewTreeUtils.equals(VTTV.ChatLeftCallMessageItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatLeftCallMessageItem")
                            val chatMsgTextColor = HookConfig.get_hook_chat_text_color_left

                            //chat_label
                            if (HookConfig.is_hook_chat_label_color)
                                VTTV.ChatLeftCallMessageItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(HookConfig.chat_label_color)
                                }

                            //msgView
                            VTTV.ChatLeftCallMessageItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                msgView.setTextColor(chatMsgTextColor)
                            }
                            //icon
                            VTTV.ChatLeftCallMessageItem.treeStacks["icon"]?.apply {
                                val icon = ViewUtils.getChildView1(view, this) as LinearLayout
                                val speakerIcon = icon.background
                                speakerIcon.setColorFilter(chatMsgTextColor, PorterDuff.Mode.SRC_ATOP)
                                icon.background = speakerIcon
                            }
                            VTTV.ChatLeftCallMessageItem.treeStacks["bgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as View
                                val bubble = WeChatHelper.getLeftBubble(msgView.resources)
                                msgView.background = bubble
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    msgView.setPadding(30, 25, 45, 25)
                                }
                            }
                        }
                    }

                    // 引用消息 item
                    else if (ViewTreeUtils.equals(VTTV.RefRightMessageItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.RefRightMessageItem")

                            //chat_label
                            if (HookConfig.is_hook_chat_label_color)
                                VTTV.RefRightMessageItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(HookConfig.chat_label_color)
                                }

                            val chatMsgRightTextColor = HookConfig.get_hook_chat_text_color_right
                            VTTV.RefRightMessageItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as View
                                XposedHelpers.callMethod(msgView, "setTextColor", chatMsgRightTextColor)
                                XposedHelpers.callMethod(msgView, "setLinkTextColor", chatMsgRightTextColor)
                                XposedHelpers.callMethod(msgView, "setHintTextColor", chatMsgRightTextColor)
                                val bubble = WeChatHelper.getRightBubble(msgView.resources)
                                msgView.background = bubble
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    msgView.setPadding(30, 25, 45, 25)
                                }
                            }
                        }
                    } else if (ViewTreeUtils.equals(VTTV.RefLeftMessageItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatLeftMessageItem")

                            if (HookConfig.is_hook_chat_label_color) {
                                //chat_label
                                VTTV.RefLeftMessageItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(HookConfig.chat_label_color)
                                }
                                //nickNameView
                                VTTV.RefLeftMessageItem.treeStacks["nickNameView"]?.apply {
                                    val nickNameView = ViewUtils.getChildView1(view, this) as TextView
                                    nickNameView.setTextColor(HookConfig.chat_label_color)
                                }
                            }

                            val chatMsgLeftTextColor = HookConfig.get_hook_chat_text_color_left
                            VTTV.RefLeftMessageItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as View
                                XposedHelpers.callMethod(msgView, "setTextColor", chatMsgLeftTextColor)
                                XposedHelpers.callMethod(msgView, "setLinkTextColor", chatMsgLeftTextColor)
                                XposedHelpers.callMethod(msgView, "setHintTextColor", chatMsgLeftTextColor)
                                // 聊天气泡
                                val bubble = WeChatHelper.getLeftBubble(msgView.resources)
                                msgView.background = bubble
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    msgView.setPadding(45, 25, 30, 25)
                                }
                            }
                        }
                    }

                    //提示信息
                    else if (ViewTreeUtils.equals(VTTV.ChatHinterItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatHinterItem")

                            if (HookConfig.is_hook_chat_label_color) {
                                val chatLabelColor = HookConfig.chat_label_color
                                //chat_label
                                VTTV.ChatHinterItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                                VTTV.ChatHinterItem.treeStacks["msgView"]?.apply {
                                    val msgView = ViewUtils.getChildView1(view, this) as View
                                    XposedHelpers.callMethod(msgView, "setTextColor", chatLabelColor)
                                }
                            }
                        }
                    }
                    //图片
                    else if (ViewTreeUtils.equals(VTTV.ChatLeftPictureItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatLeftPictureItem")
                            if (HookConfig.is_hook_chat_label_color) {

                                val chatLabelColor = HookConfig.chat_label_color

                                //timeView
                                VTTV.ChatLeftPictureItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                                //nickNameView
                                VTTV.ChatLeftPictureItem.treeStacks["nickNameView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                            }
                        }
                    } else if (ViewTreeUtils.equals(VTTV.ChatRightPictureItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatRightPictureItem")
                            if (HookConfig.is_hook_chat_label_color) {
                                val chatLabelColor = HookConfig.chat_label_color

                                //timeView
                                VTTV.ChatLeftPictureItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                            }
                        }
                    }
                    //名片
                    else if (ViewTreeUtils.equals(VTTV.ChatLeftContactCardItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatLeftContactCardItem")
                            val chatLabelColor = HookConfig.chat_label_color
                            val chatTextColorLeft = HookConfig.get_hook_chat_text_color_left

                            if (HookConfig.is_hook_chat_label_color) {
                                //timeView
                                VTTV.ChatLeftContactCardItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                                //nickNameView
                                VTTV.ChatLeftContactCardItem.treeStacks["nickNameView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                            }
                            VTTV.ChatLeftContactCardItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(msgView, "setTextColor", chatTextColorLeft)
                            }
                            VTTV.ChatLeftContactCardItem.treeStacks["titleView"]?.apply {
                                val titleView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(titleView, "setTextColor", chatTextColorLeft)
                            }
                            // 聊天气泡
                            VTTV.ChatLeftContactCardItem.treeStacks["bgView"]?.apply {
                                val bgView = ViewUtils.getChildView1(view, this) as View
                                bgView.background = WeChatHelper.getLeftBubble(bgView.resources)
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    bgView.setPadding(10, 0, 20, 25)
                                }
                            }
                        }
                    } else if (ViewTreeUtils.equals(VTTV.ChatRightContactCardItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatRightContactCardItem")
                            val chatTextColorRight = HookConfig.get_hook_chat_text_color_right

                            //timeView
                            if (HookConfig.is_hook_chat_label_color) {
                                val chatLabelColor = HookConfig.chat_label_color
                                VTTV.ChatRightContactCardItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                            }
                            VTTV.ChatRightContactCardItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(msgView, "setTextColor", chatTextColorRight)
                            }
                            VTTV.ChatRightContactCardItem.treeStacks["msgView1"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(msgView, "setTextColor", chatTextColorRight)
                            }
                            VTTV.ChatRightContactCardItem.treeStacks["titleView"]?.apply {
                                val titleView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(titleView, "setTextColor", chatTextColorRight)
                            }
                            // 聊天气泡
                            VTTV.ChatRightContactCardItem.treeStacks["bgView"]?.apply {
                                val bgView = ViewUtils.getChildView1(view, this) as View
                                bgView.background = WeChatHelper.getRightBubble(bgView.resources)
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    bgView.setPadding(20, 20, 10, 25)
                                }
                            }
                        }
                    }
                    //位置
                    else if (ViewTreeUtils.equals(VTTV.ChatLeftPositionItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatLeftPositionItem")
                            val chatTextColorLeft = HookConfig.get_hook_chat_text_color_left

                            if (HookConfig.is_hook_chat_label_color) {
                                val chatLabelColor = HookConfig.chat_label_color
                                //timeView
                                VTTV.ChatLeftPositionItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                                //nickNameView
                                VTTV.ChatLeftPositionItem.treeStacks["nickNameView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                            }
                            VTTV.ChatLeftPositionItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(msgView, "setTextColor", chatTextColorLeft)
                            }
                            VTTV.ChatLeftPositionItem.treeStacks["msgView1"]?.apply {
                                val msgView1 = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(msgView1, "setTextColor", chatTextColorLeft)
                            }
//                            VTTV.ChatLeftPositionItem.treeStacks.get("titleView")?.apply {
//                                val titleView = ViewUtils.getChildView1(view, this) as TextView
//                                XposedHelpers.callMethod(titleView, "setTextColor", chat_text_color_left)
//                            }
                            // 聊天气泡
                            VTTV.ChatLeftPositionItem.treeStacks["bgView"]?.apply {
                                val bgView = ViewUtils.getChildView1(view, this) as View
                                bgView.background = WeChatHelper.getLeftBubble(bgView.resources)
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    bgView.setPadding(20, 25, 20, 45)
                                }
                            }
                        }
                    } else if (ViewTreeUtils.equals(VTTV.ChatRightPositionItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatRightPositionItem")
                            val chatTextColor = HookConfig.get_hook_chat_text_color_right

                            if (HookConfig.is_hook_chat_label_color) {
                                val chatLabelColor = HookConfig.chat_label_color
                                //timeView
                                VTTV.ChatRightPositionItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                            }
                            VTTV.ChatRightPositionItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(msgView, "setTextColor", chatTextColor)
                            }
                            VTTV.ChatRightPositionItem.treeStacks["msgView1"]?.apply {
                                val msgView1 = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(msgView1, "setTextColor", chatTextColor)
                            }
//                            VTTV.ChatRightPositionItem.treeStacks.get("titleView")?.apply {
//                                val titleView = ViewUtils.getChildView1(view, this) as TextView
//                                XposedHelpers.callMethod(titleView, "setTextColor", chat_text_color_left)
//                            }
                            // 聊天气泡
                            VTTV.ChatRightPositionItem.treeStacks["bgView"]?.apply {
                                val bgView = ViewUtils.getChildView1(view, this) as View
                                bgView.background = WeChatHelper.getRightBubble(bgView.resources)
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    bgView.setPadding(20, 25, 20, 45)
                                }
                            }
                        }
                    }
                    //分享 / 小程序
                    else if (ViewTreeUtils.equals(VTTV.ChatLeftSharingItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatLeftSharingItem")
                            val chatTextColorLeft = HookConfig.get_hook_chat_text_color_left

                            if (HookConfig.is_hook_chat_label_color) {
                                val chatLabelColor = HookConfig.chat_label_color
                                //timeView
                                VTTV.ChatLeftSharingItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                                //nickNameView
                                VTTV.ChatLeftSharingItem.treeStacks["nickNameView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                            }
                            // 聊天气泡 miniProgramBgView
                            VTTV.ChatLeftSharingItem.treeStacks["miniProgramBgView"]?.apply {
                                val miniProgramBgView = ViewUtils.getChildView1(view, this) as View
                                miniProgramBgView.background = WeChatHelper.getLeftBubble(miniProgramBgView.resources)
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    miniProgramBgView.setPadding(20, 25, 20, 25)
                                }

                                VTTV.ChatLeftSharingItem.treeStacks["miniProgramBgView_miniProgramNameView"]?.apply {
                                    val miniProgramNameView = ViewUtils.getChildView1(miniProgramBgView, this) as TextView
                                    XposedHelpers.callMethod(miniProgramNameView, "setTextColor", chatTextColorLeft)
                                }
                                VTTV.ChatLeftSharingItem.treeStacks["miniProgramBgView_miniProgramTitleView"]?.apply {
                                    val miniProgramTitleView = ViewUtils.getChildView1(miniProgramBgView, this) as TextView
                                    XposedHelpers.callMethod(miniProgramTitleView, "setTextColor", chatTextColorLeft)
                                }

                                // 聊天气泡 null
                                VTTV.ChatLeftSharingItem.treeStacks["miniProgramBgView_bgView"]?.apply {
                                    val bgView = ViewUtils.getChildView1(miniProgramBgView, this) as View
                                    bgView.background = null
                                    VTTV.ChatLeftSharingItem.treeStacks["bgView_titleView"]?.apply {
                                        val titleView = ViewUtils.getChildView1(bgView, this) as TextView
                                        XposedHelpers.callMethod(titleView, "setTextColor", chatTextColorLeft)
                                    }
                                    VTTV.ChatLeftSharingItem.treeStacks["bgView_fileNameView"]?.apply {
                                        val fileNameView = ViewUtils.getChildView1(bgView, this) as View
                                        XposedHelpers.callMethod(fileNameView, "setTextColor", chatTextColorLeft)
                                    }
                                    VTTV.ChatLeftSharingItem.treeStacks["bgView_msgView"]?.apply {
                                        val msgView = ViewUtils.getChildView1(bgView, this) as TextView
                                        XposedHelpers.callMethod(msgView, "setTextColor", chatTextColorLeft)
                                    }
                                    VTTV.ChatLeftSharingItem.treeStacks["bgView_msgView1"]?.apply {
                                        val msgView1 = ViewUtils.getChildView1(bgView, this) as TextView
                                        XposedHelpers.callMethod(msgView1, "setTextColor", chatTextColorLeft)
                                    }
                                }
                            }
                        }
                    } else if (ViewTreeUtils.equals(VTTV.ChatRightSharingItem.item, view)) {
                        if (hookBubbles) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatRightSharingItem")
                            val chatTextColor = HookConfig.get_hook_chat_text_color_right

                            if (HookConfig.is_hook_chat_label_color) {
                                val chatLabelColor = HookConfig.chat_label_color
                                //timeView
                                VTTV.ChatRightSharingItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                                //nickNameView
                                VTTV.ChatRightSharingItem.treeStacks["nickNameView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                            }
                            // 聊天气泡 miniProgramBgView
                            VTTV.ChatRightSharingItem.treeStacks["miniProgramBgView"]?.apply {
                                val miniProgramBgView = ViewUtils.getChildView1(view, this) as View
                                miniProgramBgView.background = WeChatHelper.getRightBubble(miniProgramBgView.resources)
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    miniProgramBgView.setPadding(20, 25, 20, 25)
                                }

                                VTTV.ChatRightSharingItem.treeStacks["miniProgramBgView_miniProgramNameView"]?.apply {
                                    val miniProgramNameView = ViewUtils.getChildView1(miniProgramBgView, this) as TextView
                                    XposedHelpers.callMethod(miniProgramNameView, "setTextColor", chatTextColor)
                                }
                                VTTV.ChatRightSharingItem.treeStacks["miniProgramBgView_miniProgramTitleView"]?.apply {
                                    val miniProgramTitleView = ViewUtils.getChildView1(miniProgramBgView, this) as TextView
                                    XposedHelpers.callMethod(miniProgramTitleView, "setTextColor", chatTextColor)
                                }

                                // 聊天气泡 null
                                VTTV.ChatRightSharingItem.treeStacks["miniProgramBgView_bgView"]?.apply {
                                    val bgView = ViewUtils.getChildView1(miniProgramBgView, this) as View
                                    bgView.background = null
                                    VTTV.ChatRightSharingItem.treeStacks["bgView_titleView"]?.apply {
                                        val titleView = ViewUtils.getChildView1(bgView, this) as TextView
                                        XposedHelpers.callMethod(titleView, "setTextColor", chatTextColor)
                                    }
                                    VTTV.ChatRightSharingItem.treeStacks["bgView_fileNameView"]?.apply {
                                        val fileNameView = ViewUtils.getChildView1(bgView, this) as View
                                        XposedHelpers.callMethod(fileNameView, "setTextColor", chatTextColor)
                                    }
                                    VTTV.ChatRightSharingItem.treeStacks["bgView_msgView"]?.apply {
                                        val msgView = ViewUtils.getChildView1(bgView, this) as TextView
                                        XposedHelpers.callMethod(msgView, "setTextColor", chatTextColor)
                                    }
                                    VTTV.ChatRightSharingItem.treeStacks["bgView_msgView1"]?.apply {
                                        val msgView1 = ViewUtils.getChildView1(bgView, this) as TextView
                                        XposedHelpers.callMethod(msgView1, "setTextColor", chatTextColor)
                                    }
                                }
                            }
                        }
                    }

                    //红包 放最后
                    else if (ViewTreeUtils.equals(VTTV.ChatLeftRedPacketItem.item, view)) {
                        if (hookBubbles && HookConfig.is_hook_red_packet) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatLeftRedPacketItem")
                            val chatTextColor = HookConfig.get_hook_red_packet_text_color

                            if (HookConfig.is_hook_chat_label_color) {
                                val chatLabelColor = HookConfig.chat_label_color
                                //timeView
                                VTTV.ChatLeftRedPacketItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                                //nickNameView
                                VTTV.ChatLeftRedPacketItem.treeStacks["nickNameView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                            }
                            VTTV.ChatLeftRedPacketItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(msgView, "setTextColor", chatTextColor)
                            }
                            var unopened = true
                            // "已被领完"
                            VTTV.ChatLeftRedPacketItem.treeStacks["msgView1"]?.apply {
                                val msgView1 = ViewUtils.getChildView1(view, this) as TextView
                                if (msgView1.text.length > 0) {
                                    unopened = false
                                }
                                XposedHelpers.callMethod(msgView1, "setTextColor", chatTextColor)
                            }
                            VTTV.ChatLeftRedPacketItem.treeStacks["titleView"]?.apply {
                                val titleView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(titleView, "setTextColor", chatTextColor)
                            }
                            // 聊天气泡
                            VTTV.ChatLeftRedPacketItem.treeStacks["bgView"]?.apply {
                                val bgView = ViewUtils.getChildView1(view, this) as View
                                if (unopened) {
                                    bgView.background = WeChatHelper.getUnopenedLeftRedPacketBubble(bgView.resources)
                                } else {
                                    bgView.background = WeChatHelper.getLeftRedPacketBubble(bgView.resources)
                                }
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    bgView.setPadding(10, 0, 10, 25)
                                }
                            }
                            VTTV.ChatLeftRedPacketItem.treeStacks["adsView"]?.apply {
                                val adsView = ViewUtils.getChildView1(view, this) as FrameLayout
                                adsView.visibility = View.GONE
                            }
                            // 左侧图标
                            VTTV.ChatLeftRedPacketItem.treeStacks["leftPicView"]?.apply {
                                val leftPicView = ViewUtils.getChildView1(view, this) as ImageView
                                leftPicView.setImageDrawable(null)
                            }
                        }
                    }
                    // 右红包
                    else if (ViewTreeUtils.equals(VTTV.ChatRightRedPacketItem.item, view)) {
                        if (hookBubbles && HookConfig.is_hook_red_packet) {
                            LogUtil.logOnlyOnce("ListViewHooker.ChatRightRedPacketItem")
                            val chatTextColor = HookConfig.get_hook_red_packet_text_color

                            if (HookConfig.is_hook_chat_label_color) {
                                val chatLabelColor = HookConfig.chat_label_color
                                //timeView
                                VTTV.ChatRightRedPacketItem.treeStacks["timeView"]?.apply {
                                    val timeView = ViewUtils.getChildView1(view, this) as TextView
                                    timeView.setTextColor(chatLabelColor)
                                }
                            }
                            VTTV.ChatRightRedPacketItem.treeStacks["msgView"]?.apply {
                                val msgView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(msgView, "setTextColor", chatTextColor)
                            }
                            var unopened = true
                            // "已被领完"
                            VTTV.ChatRightRedPacketItem.treeStacks["msgView1"]?.apply {
                                val msgView1 = ViewUtils.getChildView1(view, this) as TextView
                                if (msgView1.text.length > 0) {
                                    unopened = false
                                }
                                XposedHelpers.callMethod(msgView1, "setTextColor", chatTextColor)
                            }
                            VTTV.ChatRightRedPacketItem.treeStacks["titleView"]?.apply {
                                val titleView = ViewUtils.getChildView1(view, this) as TextView
                                XposedHelpers.callMethod(titleView, "setTextColor", chatTextColor)
                            }
                            // 聊天气泡
                            VTTV.ChatRightRedPacketItem.treeStacks["bgView"]?.apply {
                                val bgView = ViewUtils.getChildView1(view, this) as View
                                if (unopened) {
                                    bgView.background = WeChatHelper.getUnopenedRightRedPacketBubble(bgView.resources)
                                } else {
                                    bgView.background = WeChatHelper.getRightRedPacketBubble(bgView.resources)
                                }
                                if (WechatGlobal.wxVersion!! >= Version("6.7.2")) {
                                    bgView.setPadding(10, 0, 10, 25)
                                }
                            }
                            VTTV.ChatRightRedPacketItem.treeStacks["adsView"]?.apply {
                                val adsView = ViewUtils.getChildView1(view, this) as FrameLayout
                                adsView.visibility = View.GONE
                            }
                            // 左侧图标
                            VTTV.ChatRightRedPacketItem.treeStacks["leftPicView"]?.apply {
                                val leftPicView = ViewUtils.getChildView1(view, this) as ImageView
                                leftPicView.setImageDrawable(null)
                            }
                        }
                    }

                    // ConversationFragment 聊天列表 item
                    else if (ViewTreeUtils.equals(VTTV.ConversationListViewItem.item, view)) {
                        LogUtil.logOnlyOnce("ListViewHooker.ConversationListViewItem")
                        try {
                            view.background.alpha = HookConfig.get_hook_conversation_background_alpha
                        } catch (e: Exception) {
                        }
                        val chatNameView = ViewUtils.getChildView1(view, VTTV.ConversationListViewItem.treeStacks["chatNameView"])
                        val chatTimeView = ViewUtils.getChildView1(view, VTTV.ConversationListViewItem.treeStacks["chatTimeView"])
                        val recentMsgView = ViewUtils.getChildView1(view, VTTV.ConversationListViewItem.treeStacks["recentMsgView"])
                        val unreadCountView = ViewUtils.getChildView1(view, VTTV.ConversationListViewItem.treeStacks["unreadCountView"]) as TextView
                        val unreadView = ViewUtils.getChildView1(view, VTTV.ConversationListViewItem.treeStacks["unreadView"]) as ImageView
//                    LogUtil.logXp("chatNameView=$chatNameView,chatTimeView=$chatTimeView,recentMsgView=$recentMsgView")
                        if (isHookTextColor) {
                            XposedHelpers.callMethod(chatNameView, "setTextColor", titleTextColor)
                            XposedHelpers.callMethod(chatTimeView, "setTextColor", summaryTextColor)
                            XposedHelpers.callMethod(recentMsgView, "setTextColor", summaryTextColor)
                        }
                        unreadCountView.backgroundTintList = ColorStateList.valueOf(NightModeUtils.colorTip)
                        unreadCountView.setTextColor(HookConfig.get_color_tip_num)
                        unreadView.backgroundTintList = ColorStateList.valueOf(NightModeUtils.colorTip)
                        // 下划线
                        ViewUtils.getChildView1(view, VTTV.ConversationListViewItem.treeStacks["contentView"])?.apply {
                            this.background = defaultImageRippleDrawable
                        }
                    }
                    //其他项, 背景置透明
                    // 联系人列表
                    else if (ViewTreeUtils.equals(VTTV.ContactListViewItem.item, view)) {
                        view.background = drawableTransparent
                        setContactListViewItem(view)
                    }
                    // 联系人列表头部
                    else if (ViewTreeUtils.equals(VTTV.ContactHeaderItem.item, view)) {
                        view.background = drawableTransparent
                        setContactHeaderItem(view)
                    }
                    // 发现 设置 item
                    else if (ViewTreeUtils.equals(VTTV.DiscoverViewItem.item, view)) {
                        view.background = drawableTransparent
//                        LogUtil.logViewStackTraces(view)
                        LogUtil.logOnlyOnce("ListViewHooker.DiscoverViewItem")
                        val iconImageView = ViewUtils.getChildView1(view, VTTV.DiscoverViewItem.treeStacks["iconImageView"]) as View
                        if (iconImageView.visibility == View.VISIBLE) {
                            val titleView = ViewUtils.getChildView1(view, VTTV.DiscoverViewItem.treeStacks["titleView"]) as TextView
                            if (isHookTextColor) {
                                titleView.setTextColor(titleTextColor)
                            }
                        }
//                        LogUtil.logViewStackTraces(view)
                        //group顶部横线
                        ViewUtils.getChildView1(view, VTTV.DiscoverViewItem.treeStacks["groupBorderTop"])
                                ?.background = drawableTransparent
                        //内容分割线
                        ViewUtils.getChildView1(view, VTTV.DiscoverViewItem.treeStacks["contentBorder"])
                                ?.background = drawableTransparent

                        //group底部横线
                        ViewUtils.getChildView1(view, VTTV.DiscoverViewItem.treeStacks["groupBorderBottom"])
                                ?.background = drawableTransparent

                        ViewUtils.getChildView1(view, VTTV.DiscoverViewItem.treeStacks["borderRight"])
                                ?.background = drawableTransparent



                        ViewUtils.getChildView1(view, VTTV.DiscoverViewItem.treeStacks["unreadPointView"])
                                ?.backgroundTintList = ColorStateList.valueOf(NightModeUtils.colorTip)
                        ViewUtils.getChildView1(view, VTTV.DiscoverViewItem.treeStacks["unreadCountView"])
                                ?.apply {
                                    this.backgroundTintList = ColorStateList.valueOf(NightModeUtils.colorTip)
                                    if (this is TextView) this.setTextColor(HookConfig.get_color_tip_num)
                                }
                    }
                    // 设置 头像
                    else if (ViewTreeUtils.equals(VTTV.SettingAvatarView.item, view)) {
                        view.background = drawableTransparent
                        LogUtil.logOnlyOnce("ListViewHooker.SettingAvatarView")

//                        微信号
                        ViewUtils.getChildView1(view, VTTV.SettingAvatarView.treeStacks["wechatTextView"])?.apply {
                            this as TextView
                            if (this.text.contains(": ") || this.text.contains("：")) {

                                //隐藏微信号
                                if (HookConfig.is_hide_wechatId) {
                                    if (wechatId.isEmpty()) wechatId = this.text
                                    this.text = "点击显示微信号"
                                    try {
                                        this.setOnClickListener {
                                            this.text = wechatId
                                            LogUtil.log("已显示微信号")
                                        }
                                    } catch (e: Exception) {
                                        LogUtil.log("显示微信号错误")
                                        LogUtil.log(e)
                                    }
                                }

                                //微信号颜色
                                if (isHookTextColor) {
                                    this.setTextColor(titleTextColor)
                                    ViewUtils.getChildView1(view, VTTV.SettingAvatarView.treeStacks["nickNameView"])?.apply {
                                        XposedHelpers.callMethod(this, "setTextColor", titleTextColor)
                                    }
                                }
                            }
                        }
                        if (WechatGlobal.wxVersion!! >= Version("8.0.0")) {
                            if (!HookConfig.is_settings_page_transparent) {
                                VTTV.SettingAvatarView.treeStacks["headView"]?.apply {
                                    ViewUtils.getChildView1(view, this)?.apply {
                                        //生成背景
                                        if (HookConfig.is_hook_bg_immersion) {
                                            if (BackgroundImageHook._backgroundBitmap[4] != null) {
                                                this.background = NightModeUtils.getBackgroundDrawable(this.resources, BackgroundImageHook._backgroundBitmap[4])
                                                return
                                            } else {
                                                //2s之后如果没生成背景就放弃
                                                BackgroundImageHook.setMainPageBitmap("设置页头像栏", this, AppCustomConfig.getTabBg(3), 4, 4)
                                            }
                                        } else {
                                            this.background = if (NightModeUtils.isWechatNightMode()) ColorDrawable(WeChatHelper.wechatDark) else ColorDrawable(WeChatHelper.wechatWhite)
                                        }
                                    }
                                }
                                VTTV.SettingAvatarView.treeStacks["q1"]?.apply {
                                    ViewUtils.getChildView1(view, this)?.apply {
                                        //生成背景
                                        if (HookConfig.is_hook_bg_immersion) {
                                            if (BackgroundImageHook._backgroundBitmap[5] != null) {
                                                this.background = NightModeUtils.getBackgroundDrawable(this.resources, BackgroundImageHook._backgroundBitmap[5])

                                            } else {
                                                //2s之后如果没生成背景就放弃
                                                BackgroundImageHook.setMainPageBitmap("设置页头像栏 (状态) ", this, AppCustomConfig.getTabBg(3), 5, 4)
                                            }
                                        } else {
                                            this.background = if (NightModeUtils.isWechatNightMode()) ColorDrawable(WeChatHelper.wechatDark) else ColorDrawable(WeChatHelper.wechatWhite)
                                        }
                                    }
                                }
                            }
                        } else {
                            VTTV.SettingAvatarView.treeStacks["headView"]?.apply {
                                ViewUtils.getChildView1(view, this)?.background = drawableTransparent
                            }
                        }
                    }
                    // (7.0.7 以上) 下拉小程序框
                    else if (ViewTreeUtils.equals(VTTV.ActionBarItem.item, view)) {
                        view.background = drawableTransparent
                        if (HookConfig.is_hook_tab_bg) {
                            LogUtil.logOnlyOnce("ListViewHooker.ActionBarItem")
                            try {
                                ViewUtils.getChildView1(view, VTTV.ActionBarItem.treeStacks["miniProgramPage"])?.apply {
                                    val miniProgramPage = this as RelativeLayout

                                    // old action bar
                                    ViewUtils.getChildView1(miniProgramPage, VTTV.ActionBarItem.treeStacks["miniProgramPage_actionBarPage"])?.apply {
                                        val actionBarPage = this as LinearLayout
                                        ViewUtils.getChildView1(actionBarPage, VTTV.ActionBarItem.treeStacks["actionBarPage_addIcon"])?.apply {
                                            actionBarPage.removeView(this)
                                        }
                                        ViewUtils.getChildView1(actionBarPage, VTTV.ActionBarItem.treeStacks["actionBarPage_searchIcon"])?.apply {
                                            actionBarPage.removeView(this)
                                        }
                                    }

                                    //小程序界面
                                    ViewUtils.getChildView1(miniProgramPage, VTTV.ActionBarItem.treeStacks["miniProgramPage_appBrandDesktopView"])?.apply {
                                        val appBrandDesktopView = this as ViewGroup
                                        if (HookConfig.is_hook_appbrand_bg_color) {
                                            appBrandDesktopView.setBackgroundColor(HookConfig.appbrand_bg_color)
                                        }
                                        // 小程序搜索框
                                        ViewUtils.getChildView1(appBrandDesktopView, VTTV.ActionBarItem.treeStacks["appBrandDesktopView_searchText"])?.apply {
                                            this.setBackgroundColor(Color.parseColor("#15000000"))
                                        }
//                                        //  小程序字体
//                                        if (!miniProgramTextItems.contains(appBrandDesktopView)) {
//                                            miniProgramTextItems.add(appBrandDesktopView)
//                                        }
//                                        ViewUtils.getChildView1(appBrandDesktopView, VTTV.ActionBarItem.treeStacks["appBrandDesktopView_miniProgramTitle"])?.apply {
//                                            if (this is ViewGroup && !miniProgramTextItems.contains(this)) {
//                                                LogUtil.log("############222")
//                                                miniProgramTextItems.add(this)
//                                            }
//                                        }
//                                        setMiniProgramTitleColor()
                                    }
                                }
                            } catch (e: ClassCastException) {
//                            LogUtil.log(e)
//                            LogUtil.logViewStackTraces(view)
                                return
                            }
                        }
                    }
                    // 其他情况, 记录一下
                    else {
                        view.background = drawableTransparent
                        if ((!Common.isVXPEnv) && (HookConfig.is_hook_debug || HookConfig.is_hook_debug2)) {
                            LogUtil.log("----------未识别的listview----------")
                            LogUtil.log(WechatGlobal.wxVersion.toString())
                            LogUtil.log("context=" + view.context)
                            LogUtil.logViewStackTraces(view)
//                            LogUtil.logParentView(view, 100)
                            LogUtil.log("--------------------")
                        }
                    }
                } catch (e: Exception) {
                    LogUtil.log(e)
                }
            }
        })
    }

    // 8.0.14 之后联系人列表从 LinearLayout 变为 NoDrawingCacheLinearLayout, 8.0.24 之后又变回去了 (?)
    fun setContactListViewItem(view: View) {
        LogUtil.logOnlyOnce("ListViewHooker.ContactListViewItem")
        // 标题下面的线
        if (VTTV.ContactListViewItem.treeStacks["headerView"] != null) {
            ViewUtils.getChildView1(view, VTTV.ContactListViewItem.treeStacks["headerView"])
                    ?.background = drawableTransparent
        }
        //内容下面的线 innerView
        ViewUtils.getChildView1(view, VTTV.ContactListViewItem.treeStacks["innerView"])
                ?.background = drawableTransparent

        ViewUtils.getChildView1(view, VTTV.ContactListViewItem.treeStacks["contentView"])
                ?.background = drawableTransparent

        val titleView = ViewUtils.getChildView1(view, VTTV.ContactListViewItem.treeStacks["titleView"])
        titleView?.background = drawableTransparent
        val titleView80 = ViewUtils.getChildView1(view, VTTV.ContactListViewItem.treeStacks["titleView_8_0"])
        titleView80?.background = drawableTransparent
//        titleView80?.apply{
//            LogUtil.logView(this)}
        if (isHookTextColor) {
            val headTextView = ViewUtils.getChildView1(view, VTTV.ContactListViewItem.treeStacks["headTextView"]) as TextView
            headTextView.setTextColor(summaryTextColor)
            titleView?.apply { XposedHelpers.callMethod(this, "setNickNameTextColor", ColorStateList.valueOf(titleTextColor)) }
            titleView80?.apply {
                XposedHelpers.callMethod(titleView80, "setTextColor", titleTextColor)
            }
        }
    }

    // 联系人列表头部 - 写的比较混乱:有些地方在contactHooker中
    fun setContactHeaderItem(view: View) {
        LogUtil.logOnlyOnce("ListViewHooker.ContactHeaderItem")

        //公众号的下边界 —— ContactHeaderItem.ContactWorkItemBorderTop
        ViewUtils.getChildView1(view, VTTV.ContactHeaderItem.treeStacks["ContactWorkItemBorderTop"])?.apply {
            this.background = drawableTransparent
        }

        //企业联系人分组
        ViewUtils.getChildView1(view, VTTV.ContactHeaderItem.treeStacks["ContactWorkItem"])?.apply {
            if (ViewTreeUtils.equals(VTTV.ContactWorkItem.item, this)) {
                LogUtil.logOnlyOnce("ListViewHooker.ContactWorkItem")

                //region borderLineTop
                ViewUtils.getChildView1(this, VTTV.ContactWorkItem.treeStacks["borderLineTop"])?.apply {
                    this.background = drawableTransparent
                }
                //endregion

                var contactContentsItem = ViewUtils.getChildView1(this, VTTV.ContactWorkItem.treeStacks["ContactContentsItem"])
                if (contactContentsItem != null) {
                    //region 企业联系人
                    if (ViewTreeUtils.equals(VTTV.ContactWorkContactsItem.item, contactContentsItem)) {
                        LogUtil.logOnlyOnce("ListViewHooker.ContactWorkContactsItem")
                        if (isHookTextColor) {
                            val headTextView = ViewUtils.getChildView1(contactContentsItem, VTTV.ContactWorkContactsItem.treeStacks["headTextView"]) as TextView
                            headTextView.setTextColor(titleTextColor)
                        }
                        //  titleView
                        ViewUtils.getChildView1(contactContentsItem, VTTV.ContactWorkContactsItem.treeStacks["titleView"])
                                ?.background = defaultImageRippleDrawable
                        ViewUtils.getChildView1(contactContentsItem, VTTV.ContactWorkContactsItem.treeStacks["borderLineBottom"])
                                ?.background = defaultImageRippleDrawable
                        //endregion


                        val tmpView = ViewUtils.getChildView1(this, VTTV.ContactWorkItem.treeStacks["ContactContentsItem1"])
                        tmpView?.apply {
                            contactContentsItem = tmpView
                        }
                    }
                    // 我的企业
                    if (ViewTreeUtils.equals(VTTV.ContactMyWorkItem.item, contactContentsItem!!)) {
                        LogUtil.logOnlyOnce("ListViewHooker.ContactMyWorkItem")
                        //  titleView
                        ViewUtils.getChildView1(contactContentsItem!!, VTTV.ContactMyWorkItem.treeStacks["titleView"])
                                ?.background = defaultImageRippleDrawable
                        ViewUtils.getChildView1(contactContentsItem!!, VTTV.ContactMyWorkItem.treeStacks["borderLineBottom"])
                                ?.background = drawableTransparent
                        if (isHookTextColor) {
                            val headTextView = ViewUtils.getChildView1(contactContentsItem!!, VTTV.ContactMyWorkItem.treeStacks["headTextView"]) as TextView
                            headTextView.setTextColor(titleTextColor)
                        }
                    }
                }
            }
        }
    }

    fun setContactHeaderItemTop(headLayout: ViewGroup) {
        for (i in 0 until headLayout.childCount) {
            val item = headLayout.getChildAt(i)
            if (item !is ViewGroup || item.childCount == 0) {
                continue
            }
            val itemContent = item.getChildAt(0)
            var titleTextView: View?
            var headTextView: View?
            if (itemContent != null) {
                // 新的朋友 等几个 item
                itemContent.background = defaultImageRippleDrawable
//                                                LogUtil.log("-------------")
//                                                LogUtil.logViewStackTraces(itemContent)
//                                                LogUtil.log("-------------")
                if (itemContent is ViewGroup) {
                    val childView = itemContent.getChildAt(0)
                    childView.background = drawableTransparent
                    if (childView is TextView) {// 企业号
                        headTextView = childView // 我的企业 textView
                        itemContent.background = drawableTransparent
                        val lll = (itemContent.getChildAt(1) as ViewGroup)
                        for (m in 0 until lll.childCount) {
                            val comItem = (lll.getChildAt(m) as ViewGroup)
                            val ll = comItem.getChildAt(0) as ViewGroup
                            ll.background = defaultImageRippleDrawable
                            // 去掉分割线
                            ll.getChildAt(0).background = drawableTransparent
                            titleTextView = ViewUtils.getChildView(ll, 0, 1)
                            titleTextView?.apply {
                                this.background = drawableTransparent
                                if (this is TextView && isHookTextColor) {
                                    this.setTextColor(titleTextColor)
                                }
                            }
                        }
                        if (isHookTextColor) {
                            headTextView.setTextColor(summaryTextColor)
                        }
                    } else if (childView is ViewGroup) {// 新的朋友 群聊 公众号
                        var maskLayout = childView.getChildAt(0)
                        titleTextView = childView.getChildAt(1) // 公众号 textView
//                                                        LogUtil.log("-------------")
//                                                        LogUtil.logViewStackTraces(childView)
//                                                        LogUtil.log("-------------")
                        if (titleTextView == null) {// 企业微信联系人
                            maskLayout = ViewUtils.getChildView(childView, 0, 0, 0, 0)
                            titleTextView = ViewUtils.getChildView(childView, 0, 0, 0, 1)
                            ViewUtils.getChildView(childView, 0, 0)?.background = drawableTransparent
                        }
                        if (maskLayout != null && maskLayout is ViewGroup) {
                            val iv = maskLayout.getChildAt(0)
                            if (iv is ImageView) {
                                val roundLayout = RCRelativeLayout(Objects.Main.LauncherUI!!)
                                roundLayout.isRoundAsCircle = true
                                maskLayout.addView(roundLayout, iv.layoutParams)
                                maskLayout.removeView(iv)
                                roundLayout.addView(iv)
                            }
                        }
                        if (titleTextView != null) {
                            titleTextView.background = drawableTransparent
                            if (isHookTextColor) {
                                titleTextView.apply {
                                    if (this is TextView) {
                                        this.setTextColor(titleTextColor)
                                    } else if (this is ViewGroup) {
                                        val tv = this.getChildAt(0)
                                        if (tv is TextView) {
                                            tv.setTextColor(titleTextColor)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
//    private val settingMiniProgramTitleColor = false
//
//    //设置小程序的字体颜色，需要重复设置
//    fun setMiniProgramTitleColor() {
//        if (settingMiniProgramTitleColor) {
//            return
//        }
//        LogUtil.log("循环修改小程序字体颜色...")
//        thread {
//            while (true) {
//                for (items in 0..miniProgramTextItems.size - 1) {
//                    try {
//                        val fatherView = miniProgramTextItems[items]
//                        LogUtil.logViewStackTraces(fatherView)
//                        for (childAt in 0 until fatherView.childCount) {
//                            val child = fatherView.getChildAt(childAt)
//                            if (child is ViewGroup) {
//                                val textView = findLastChildView(child, CC.TextView.name)
//                                if (textView is TextView) {
//                                        LogUtil.log("setting text color: ${textView.text}")
//                                    if (textView.currentTextColor != titleTextColor) {
//                                        textView.setTextColor(titleTextColor)
//                                    }
//                                }
//                            }
//                        }
//                    } catch (e: Exception) {
//                        LogUtil.log(e)
//                        miniProgramTextItems.removeAt(items)
//                    }
//                }
//                if (true||!HookConfig.is_hook_appbrand_text_color) {
//                    return@thread
//                }
//                Thread.sleep(2000)
//            }
//        }
//    }
}