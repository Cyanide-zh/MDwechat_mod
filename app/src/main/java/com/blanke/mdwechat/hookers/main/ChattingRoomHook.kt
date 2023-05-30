package com.blanke.mdwechat.hookers.main

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.blanke.mdwechat.Common
import com.blanke.mdwechat.config.AppCustomConfig.getChatBg
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.util.LogUtil
import com.blanke.mdwechat.util.NightModeUtils
import com.blanke.mdwechat.util.ViewTreeUtils
import com.blanke.mdwechat.util.ViewUtils
import com.blanke.mdwechat.util.ViewUtils.getParentViewSafe
import com.blanke.mdwechat.ViewTreeRepoThisVersion as VTTV


object ChattingRoomHook {
    var actionBarBottom = 0
    private val transparentDark = Color.parseColor("#30000000")
    private val transparentLight = Color.parseColor("#30EEEEEE")
    private fun isBgLight(): Boolean {
        return !HookConfig.is_hook_scheme_dark && !NightModeUtils.isNightMode()
    }

    private fun transparentBackground(): Int {
        return if (isBgLight()) transparentLight else transparentDark
    }

    private var footerLocation = mutableListOf(-1, -1)

    fun setConversationColor(actionBar: View) {
        LogUtil.log("开始设置聊天页沉浸背景")
        try {
            val title = ViewUtils.getChildView1(actionBar, VTTV.ActionBarInConversationItem.treeStacks.getValue("title")) as TextView
            title.setTextColor(NightModeUtils.colorSecondary)
//        val infoButton = ViewUtils.getChildView1(actionBar, VTTV.ActionBarInConversationItem.treeStacks.getValue("infoButton")) as ImageButton
//        infoButton.setColorFilter(NightModeUtils.colorSecondary, PorterDuff.Mode.SRC_ATOP)
            val goBackButton = ViewUtils.getChildView1(actionBar, VTTV.ActionBarInConversationItem.treeStacks.getValue("goBackButton")) as ImageView
            goBackButton.setColorFilter(NightModeUtils.colorSecondary, PorterDuff.Mode.SRC_ATOP)

            val chattingUILayout = ViewUtils.getParentView(actionBar, 1) as ViewGroup
            if (ViewTreeUtils.equals(VTTV.ChattingUILayoutItem.item, chattingUILayout)) {
                val chattingScrollLayoutItem = ViewUtils.getChildView1(chattingUILayout,
                        VTTV.ChattingUILayoutItem.treeStacks.getValue("ChattingScrollLayoutItem")) as ViewGroup
                setConversationFooterColor(chattingScrollLayoutItem, VTTV.ChattingScrollLayoutItem.treeStacks)
            } else {
                LogUtil.log("聊天页沉浸背景底栏匹配错误")
                LogUtil.logViewStackTraces(chattingUILayout)
            }
        } catch (e: Exception) {
            LogUtil.log(e)
        }
    }

    fun setConversationInSearchColor(actionBar: View) {
        LogUtil.log("开始设置 - 由搜索进入的 - 聊天页沉浸背景")
        val title = ViewUtils.getChildView1(actionBar, VTTV.ActionBarInSearchConversationItem.treeStacks.getValue("title")) as TextView
        title.setTextColor(NightModeUtils.colorSecondary)
//        val infoButton = ViewUtils.getChildView1(actionBar, VTTV.ActionBarInSearchConversationItem.treeStacks.getValue("infoButton")) as ImageButton
//        infoButton.setColorFilter(NightModeUtils.colorSecondary, PorterDuff.Mode.SRC_ATOP)
        val goBackButton = ViewUtils.getChildView1(actionBar, VTTV.ActionBarInSearchConversationItem.treeStacks.getValue("goBackButton")) as ImageView
        goBackButton.setColorFilter(NightModeUtils.colorSecondary, PorterDuff.Mode.SRC_ATOP)

        val chattingUILayout = ViewUtils.getParentView(actionBar, 1) as ViewGroup
        if (ViewTreeUtils.equals(VTTV.ChattingUILayoutInSearchItem.item, chattingUILayout)) {
            val chattingScrollLayoutItem = ViewUtils.getChildView1(chattingUILayout,
                    VTTV.ChattingUILayoutInSearchItem.treeStacks.getValue("ChattingScrollLayoutItem")) as ViewGroup
            setConversationFooterColor(chattingScrollLayoutItem, VTTV.ChattingScrollLayoutItem.treeStacks)
        } else {
            LogUtil.log("聊天页沉浸背景底栏匹配错误")
            LogUtil.logViewStackTraces(chattingUILayout)
        }
    }

    private fun setConversationFooterColor(ChattingScrollLayoutItem: ViewGroup, treeStacks: Map<String, IntArray>) {
        LogUtil.log("开始设置聊天页底栏")
        //判断是否为公众号页面
        if ((ChattingScrollLayoutItem.childCount >= 3) &&
                (ChattingScrollLayoutItem.getChildAt(2)::class.java.name == "com.tencent.mm.ui.chatting.ChatFooterCustom")) {
            //底栏
            val footer = ChattingScrollLayoutItem.getChildAt(2) as View
            val location = BackgroundImageHook.setBackgroundBitmap("公众号 footer", footer, getChatBg(), null)
            if ((footerLocation[1] < 0) && (location[1] > 0)) {
                footerLocation[0] = location[0]
                footerLocation[1] = location[1]
            }

        }


        // region去除默认聊天背景
        val bgGroup = ViewUtils.getChildView1(ChattingScrollLayoutItem,
                treeStacks.getValue("bgGroup")) as ViewGroup
        val chattingBgShade = ViewUtils.getChildView1(ChattingScrollLayoutItem,
                treeStacks.getValue("chattingBgShade")) as View
        if (HookConfig.is_hook_scheme_dark || !HookConfig.is_hook_night_mode) chattingBgShade.setBackgroundColor(0)
        LogUtil.log("去除聊天背景遮罩")
        //endregion

        val chatFooterChild2 = ViewUtils.getChildView1(ChattingScrollLayoutItem,
                treeStacks.getValue("chatFooterChild2")) as View
        try {
            if (HookConfig.is_chat_bg_transparent_mode) {
                if (HookConfig.is_enable_bg_chat) {
                    // region 自定义聊天背景
                    val context = bgGroup.context.createPackageContext(Common.MY_APPLICATION_PACKAGE, Context.CONTEXT_IGNORE_SECURITY)
                    val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    params.height = HookConfig.value_resolution[1] - actionBarBottom
                    val bgViewWithEditText = FrameLayout(context)
                    bgViewWithEditText.elevation = -100f
                    (getParentViewSafe(ChattingScrollLayoutItem, 1) as ViewGroup).addView(bgViewWithEditText, 1, params)
                    bgViewWithEditText.background = NightModeUtils.getBackgroundDrawable(bgViewWithEditText.resources, BackgroundImageHook.cutBitmap("ChattingImageBGView",
                            getChatBg(), actionBarBottom, params.height))
                    LogUtil.log("替换自定义聊天背景+输入框背景")
                    //endregion
                    //region 聊天背景 MIUI12
                    val bgViewMiui12fix = FrameLayout(context)
                    bgGroup.addView(bgViewMiui12fix, 1, params)
                    bgViewMiui12fix.background = NightModeUtils.getBackgroundDrawable(bgViewMiui12fix.resources, BackgroundImageHook.cutBitmap("ChattingImageBGView",
                            getChatBg(), actionBarBottom, params.height))
                    LogUtil.log("聊天背景 + MIUI12 fix test")
                    //endregion
                }
                //region 底栏输入框
                chatFooterChild2.background = ColorDrawable(transparentBackground())
                LogUtil.log("去除聊天底栏背景")
                //endregion
            } else {
                if (HookConfig.is_enable_bg_chat) {
                    //  region自定义聊天背景
                    val context = bgGroup.context.createPackageContext(Common.MY_APPLICATION_PACKAGE, Context.CONTEXT_IGNORE_SECURITY)
                    val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    val view = FrameLayout(context)
                    params.height = HookConfig.value_resolution[1] - actionBarBottom
                    bgGroup.addView(view, 1, params)
                    view.background = NightModeUtils.getBackgroundDrawable(view.resources, BackgroundImageHook.cutBitmap("ChattingImageBGView",
                            getChatBg(), actionBarBottom, params.height))
                    LogUtil.log("替换自定义聊天背景")
                    //endregion
                }
                // region底栏输入框
                if (footerLocation[1] > 0) {
                    chatFooterChild2.background = NightModeUtils.getBackgroundDrawable(chatFooterChild2.resources, BackgroundImageHook.cutBitmap("chatFooterChild2",
                            getChatBg(), footerLocation[0], ViewUtils.measureHeight(chatFooterChild2)))
                } else {
                    BackgroundImageHook.setBackgroundBitmap("chatFooterChild2", chatFooterChild2, getChatBg(), null)
                }
                LogUtil.log("替换聊天底栏背景")
                //endregion
            }


            //region 底栏
            //语音打字切换
            val switchButton = ViewUtils.getChildView1(chatFooterChild2,
                    treeStacks.getValue("chatFooterChild2_switchButton")) as ImageButton
            switchButton.setColorFilter(NightModeUtils.colorSecondary, PorterDuff.Mode.SRC_ATOP)

            // 7.0.17 + MIUI 12 不透明 fix
            var miuiEditTextFixed = false
            if (treeStacks.containsKey("chatFooterChild2_editText_MIUI12")) {
                ViewUtils.getChildView1(chatFooterChild2, treeStacks.getValue("chatFooterChild2_editText_MIUI12"))?.apply {
                    this.background = ColorDrawable(transparentBackground())
                    miuiEditTextFixed = true
                }
            }

            val editText = ViewUtils.getChildView1(chatFooterChild2,
                    treeStacks.getValue("chatFooterChild2_editText")) as EditText
            editText.setTextColor(NightModeUtils.colorSecondary)
//        editText.setTextColor(if (isBgLight) WeChatHelper.colorDark else WeChatHelper.colorWhite)
            if (miuiEditTextFixed) {
                editText.background = ColorDrawable(Color.parseColor("#00000000"))
            } else {
                editText.background = ColorDrawable(transparentBackground())
            }
            val talkButton = ViewUtils.getChildView1(chatFooterChild2,
                    treeStacks.getValue("chatFooterChild2_talkButton")) as View
            talkButton.background = ColorDrawable(transparentBackground())

            //表情
            val faceButton = ViewUtils.getChildView1(chatFooterChild2,
                    treeStacks.getValue("chatFooterChild2_faceButton")) as ImageButton
            faceButton.setColorFilter(NightModeUtils.colorSecondary, PorterDuff.Mode.SRC_ATOP)
            val addButton = ViewUtils.getChildView1(chatFooterChild2,
                    treeStacks.getValue("chatFooterChild2_addButton")) as ImageButton
            addButton.setColorFilter(NightModeUtils.colorSecondary, PorterDuff.Mode.SRC_ATOP)
            //发送
            val sendButton = ViewUtils.getChildView1(chatFooterChild2,
                    treeStacks.getValue("chatFooterChild2_sendButton")) as Button
            sendButton.setBackgroundColor(transparentBackground())
            sendButton.setTextColor(NightModeUtils.colorSecondary)
            LogUtil.log("替换聊天底栏控件")
            //endregion
        } catch (e: Exception) {
            LogUtil.log(e)
        }
    }
}