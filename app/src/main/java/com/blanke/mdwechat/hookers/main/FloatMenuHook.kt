package com.blanke.mdwechat.hookers.main

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.*
import android.widget.FrameLayout
import android.widget.Toast
import com.blanke.mdwechat.Common
import com.blanke.mdwechat.Objects
import com.blanke.mdwechat.WeChatHelper
import com.blanke.mdwechat.bean.FLoatButtonConfigItem
import com.blanke.mdwechat.config.AppCustomConfig
import com.blanke.mdwechat.config.HookConfig
import com.blanke.mdwechat.util.ConvertUtils
import com.blanke.mdwechat.util.DrawableUtils
import com.blanke.mdwechat.util.LogUtil
import com.blanke.mdwechat.util.LogUtil.log
import com.blanke.mdwechat.util.NightModeUtils
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionButton.SIZE_MINI
import com.github.clans.fab.FloatingActionMenu
import de.robv.android.xposed.XposedHelpers


object FloatMenuHook {

    fun addFloatMenu(contentLayout: ViewGroup, bottomMargin: Int = 0) {
        FloatingActionMenu.OPENED_PLUS_ROTATION_LEFT = HookConfig.value_hook_float_button_angle.toFloat()
        val context = contentLayout.context.createPackageContext(Common.MY_APPLICATION_PACKAGE, Context.CONTEXT_IGNORE_SECURITY)
        val floatConfig = AppCustomConfig.getFloatButtonConfig()
        if (floatConfig?.items == null || floatConfig.menu?.icon == null) {
            log("floatButton 主 icon 为空")
            return
        }
        val primaryColor = NightModeUtils.colorPrimary
//        val secondaryColor = HookConfig.get_color_secondary
        val floatButtonColor = NightModeUtils.colorFloatButton
        val actionMenu = FloatingActionMenu(context)
        actionMenu.menuButtonColorNormal = primaryColor
        actionMenu.menuButtonColorPressed = primaryColor
        actionMenu.setmLabelsTextColor(floatButtonColor)
        val bitmap: Bitmap? = AppCustomConfig.getIcon(floatConfig.menu!!.icon)
        if (bitmap == null) {
            log("floatButton 主 icon 为空")
            return
        }
        var drawable: Drawable = BitmapDrawable(context.resources, bitmap)
        drawable = if (HookConfig.is_hook_float_button_color_up) DrawableUtils.setDrawableColor(drawable, floatButtonColor) else drawable
        actionMenu.setMenuIcon(drawable)
        actionMenu.initMenuButton()

        //menu items
        val floatItems = arrayListOf<FLoatButtonConfigItem>()
        floatConfig.items?.sortedBy { it.order }
                ?.forEach {
                    val drawable2: Bitmap? = AppCustomConfig.getIcon(it.icon)
                    if (drawable2 == null) {
                        log("${it.icon}不存在,忽略~")
                        return@forEach
                    }
                    floatItems.add(it)
                    getFloatButton(actionMenu, context, it.text,
                            BitmapDrawable(context.resources,
                                    AppCustomConfig.getScaleBitmap(drawable2)), primaryColor, floatButtonColor, HookConfig.is_hook_float_button_color_up)
                }

        actionMenu.setFloatButtonClickListener { fab, index ->
            //log("click fab,index=" + index + ",label" + fab.getLabelText());
            onFloatButtonClick(floatItems[index], index)
        }

        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val margin = ConvertUtils.dp2px(contentLayout.context, 12f)
        params.rightMargin = margin
        params.bottomMargin = margin + bottomMargin
        params.gravity = Gravity.END or Gravity.BOTTOM
        if (HookConfig.is_hook_float_button_move) {
            actionMenu.menuButton.setOnTouchListener(object : View.OnTouchListener {
                var lastX: Float = 0.toFloat()
                var lastY: Float = 0.toFloat()
                var downTime: Long = 0

                override fun onTouch(v: View, event: MotionEvent): Boolean {
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        lastX = event.rawX
                        lastY = event.rawY
                        downTime = System.currentTimeMillis()
                    } else if (event.action == MotionEvent.ACTION_MOVE) {
                        val nowX = event.rawX
                        val nowY = event.rawY
                        val dx = (nowX - lastX).toInt().toFloat()
                        val dy = (nowY - lastY).toInt().toFloat()
                        lastX = nowX
                        lastY = nowY
                        actionMenu.x = actionMenu.x + dx
                        actionMenu.y = actionMenu.y + dy
                    } else if (event.action == MotionEvent.ACTION_UP) {
                        val nowTime = System.currentTimeMillis()
                        if (nowTime - downTime > 300) {
                            actionMenu.menuButton.isPressed = false
                            return true
                        }
                    }
                    return false
                }
            })
        }

        val backgroundView = View(context)
        val params2 = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        backgroundView.visibility = View.GONE
        backgroundView.setOnClickListener { view ->
            actionMenu.close(true)
            view.visibility = View.GONE
        }
        actionMenu.setOnMenuToggleListener { opened ->
            backgroundView.visibility = if (opened) View.VISIBLE else View.GONE
        }
        contentLayout.addView(backgroundView, params2)
        contentLayout.addView(actionMenu, params)
    }

    private fun getFloatButton(actionMenu: FloatingActionMenu, context: Context,
                               label: String, drawable: Drawable, primaryColor: Int, floatButtonColor: Int, isColorUp: Boolean = false): FloatingActionButton {
        val fab = FloatingActionButton(context)
        val filteredDrawable = if (isColorUp) DrawableUtils.setDrawableColor(drawable, floatButtonColor) else drawable

        fab.setImageDrawable(filteredDrawable)
        fab.colorNormal = primaryColor
        fab.colorPressed = primaryColor
        fab.buttonSize = SIZE_MINI
        fab.labelText = label
//        fab.setLabelTextColor(floatButtonColor)
        actionMenu.addMenuButton(fab)
        fab.setLabelColors(primaryColor, primaryColor, primaryColor)
        return fab
    }

    private fun onFloatButtonClick(item: FLoatButtonConfigItem, index: Int) {
        log("点击悬浮按钮，index=$index,item=$item")
        when (item.type) {
            "weiX" -> {
                Objects.Main.LauncherUI_mWechatXMenuItem?.run {
                    this::class.java.declaredFields
                            .firstOrNull { it.type == MenuItem.OnMenuItemClickListener::class.java }
                            ?.apply {
                                this.isAccessible = true
                                val listener = this.get(this@run)
                                XposedHelpers.callMethod(listener, "onMenuItemClick", this@run)
                            }
                }
            }
            else -> {
                try {
                    WeChatHelper.startActivity(item.type)
                } catch (e: Throwable) {
                    LogUtil.log(e)
                    Objects.Main.LauncherUI?.apply {
                        Toast.makeText(this, "跳转失败，请检查类名是否正确", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}