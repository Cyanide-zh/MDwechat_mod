package com.blanke.mdwechat

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import com.blanke.mdwechat.config.AppCustomConfig
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.util.ColorUtils
import com.blanke.mdwechat.util.DrawableUtils
import com.blanke.mdwechat.util.NightModeUtils.colorPrimary
import de.robv.android.xposed.XSharedPreferences
import java.io.File

object WeChatHelper {
    lateinit var XMOD_PREFS: XSharedPreferences

    //微信8.0.0第四页底色
    val wechatWhite: Int = Color.parseColor("#ffffff")
    val wechatDark: Int = Color.parseColor("#111111")

    val colorWhite: Int = Color.parseColor("#FAFAFA")
    val colorDarkWhite: Int = Color.LTGRAY

    val colorDark: Int = Color.parseColor("#232323")

    //深色模式下 action bar 的颜色
    val colorDarkPrimary: Int = Color.parseColor("#333333")


    val drawableTransparent: ColorDrawable = ColorDrawable(Color.TRANSPARENT)

    val drawableWhite: ColorDrawable = ColorDrawable(colorWhite)

    val drawableDark: ColorDrawable = ColorDrawable(colorDark)

    val colorPrimaryDrawable: ColorDrawable
        get() {
            val colorDrawable = ColorDrawable()
            colorDrawable.color = colorPrimary
            return colorDrawable
        }
    val colorPrimaryDrawableDark: ColorDrawable
        get() = ColorDrawable(colorDarkPrimary)

    val defaultImageRippleDrawable: Drawable?
        get() {
            val context = Objects.Main.LauncherUI ?: return null
            val attrs = intArrayOf(android.R.attr.selectableItemBackground)
            val ta = context.obtainStyledAttributes(attrs)
            val imageRippleDrawable = ta.getDrawable(0)
            ta.recycle()
            return imageRippleDrawable
        }

    val defaultImageRippleBorderDrawable: Drawable?
        get() {
            val context = Objects.Main.LauncherUI ?: return null
            val attrs = intArrayOf(android.R.attr.selectableItemBackgroundBorderless)
            val ta = context.obtainStyledAttributes(attrs)
            val imageRippleDrawable = ta.getDrawable(0)
            ta.recycle()
            return imageRippleDrawable
        }

    fun getLeftRedPacketBubble(
            resources: Resources,
            isTint: Boolean = false,
            tintColor: Int = HookConfig.get_hook_bubble_tint_left
    ): Drawable? {
        val bubble = AppCustomConfig.getRedPacketBubbleLeftIcon() ?: return null
        return getBubble(bubble, resources, isTint, tintColor)
    }

    fun getUnopenedLeftRedPacketBubble(
            resources: Resources,
            isTint: Boolean = false,
            tintColor: Int = HookConfig.get_hook_bubble_tint_left
    ): Drawable? {
        val bubble = AppCustomConfig.getUnopenedRedPacketBubbleLeftIcon() ?: return null
        return getBubble(bubble, resources, isTint, tintColor)
    }

    fun getRightRedPacketBubble(
            resources: Resources,
            isTint: Boolean = false,
            tintColor: Int = HookConfig.get_hook_bubble_tint_right
    ): Drawable? {
        val bubble = AppCustomConfig.getRedPacketBubbleRightIcon() ?: return null
        return getBubble(bubble, resources, isTint, tintColor)
    }

    fun getUnopenedRightRedPacketBubble(
            resources: Resources,
            isTint: Boolean = false,
            tintColor: Int = HookConfig.get_hook_bubble_tint_right
    ): Drawable? {
        val bubble = AppCustomConfig.getUnopenedRedPacketBubbleRightIcon() ?: return null
        return getBubble(bubble, resources, isTint, tintColor)
    }

    fun getLeftBubble(
            resources: Resources,
            isTint: Boolean = HookConfig.is_hook_bubble_tint,
            tintColor: Int = HookConfig.get_hook_bubble_tint_left
    ): Drawable? {
        val bubble = AppCustomConfig.getBubbleLeftIcon() ?: return null
        return getBubble(bubble, resources, isTint, tintColor)
    }

    fun getRightBubble(
            resources: Resources,
            isTint: Boolean = HookConfig.is_hook_bubble_tint,
            tintColor: Int = HookConfig.get_hook_bubble_tint_right
    ): Drawable? {
        val bubble = AppCustomConfig.getBubbleRightIcon() ?: return null
        return getBubble(bubble, resources, isTint, tintColor)
    }

    private fun getBubble(
            sourceBitmap: Bitmap,
            resources: Resources,
            isTint: Boolean = HookConfig.is_hook_bubble_tint,
            tintColor: Int = HookConfig.get_hook_bubble_tint_right
    ): Drawable? {
        val bubbleDrawable = DrawableUtils.getNineDrawable(resources, sourceBitmap)
        val drawable = StateListDrawable()
        val pressBubbleDrawable = bubbleDrawable.constantState!!.newDrawable().mutate()
        if (isTint) {
            bubbleDrawable.setTint(tintColor)
            pressBubbleDrawable.setTint(getDarkColor(tintColor))
        } else {
            pressBubbleDrawable.setTint(getDarkColor(Color.WHITE))
        }
        drawable.addState(intArrayOf(android.R.attr.state_pressed), pressBubbleDrawable)
        drawable.addState(intArrayOf(android.R.attr.state_focused), pressBubbleDrawable)
        drawable.addState(intArrayOf(), bubbleDrawable)
        return drawable
    }

    fun startActivity(actName: String) {
        val context = Objects.Main.LauncherUI ?: return
        val intent = Intent()
        intent.setClassName(context as Context, actName)
        context.startActivity(intent)
    }

    fun initPrefs() {
        XMOD_PREFS = XSharedPreferences(File(AppCustomConfig.getConfigFile(Common.MOD_PREFS + ".xml")))
        XMOD_PREFS.makeWorldReadable()
        XMOD_PREFS.reload()
    }

    fun reloadPrefs() {
        XMOD_PREFS.reload()
    }

    private fun getDarkColor(color: Int): Int {
        return ColorUtils.getDarkerColor(color, 0.8F)
    }
}
