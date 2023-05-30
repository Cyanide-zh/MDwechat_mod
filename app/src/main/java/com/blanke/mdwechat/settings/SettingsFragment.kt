package com.blanke.mdwechat.settings

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context.WINDOW_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.preference.*
import androidx.core.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import com.blanke.mdwechat.Common
import com.blanke.mdwechat.Version
import com.blanke.mdwechat.auto_search.Main
import com.blanke.mdwechat.auto_search.bean.LogEvent
import com.blanke.mdwechat.config.AppCustomConfig.getIconPath
import com.blanke.mdwechat.markdown.MarkDownActivity
import com.blanke.mdwechat.settings.view.DownloadWechatDialog
import com.blanke.mdwechat.util.FileUtils
import com.blanke.mdwechat.util.LogUtil.clearFileLogs
import com.blankj.utilcode.util.FileUtils.isFileExists
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils
import com.jaredrummler.android.colorpicker.ColorPreference
import com.joshcai.mdwechat.BuildConfig
import com.joshcai.mdwechat.R
import net.dongliu.apk.parser.ApkFile
import org.devio.takephoto.app.TakePhoto
import org.devio.takephoto.app.TakePhoto.TakeResultListener
import org.devio.takephoto.app.TakePhotoImpl
import org.devio.takephoto.model.*
import org.devio.takephoto.permission.InvokeListener
import org.devio.takephoto.permission.PermissionManager
import org.devio.takephoto.permission.PermissionManager.TPermissionType
import org.devio.takephoto.permission.TakePhotoInvocationHandler
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.util.*
import kotlin.concurrent.thread


/**
 * Created by blanke on 2017/6/8.
 */

class SettingsFragment : PreferenceFragment(), TakeResultListener, InvokeListener, Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    lateinit var wxVersion: Version
    var screenWidth = 0
    var screenHeight = 0
    val tab_icon_width = 150
    val tab_icon_height = 150

    object STATIC {
        lateinit var sharedPrefsFile: File
        lateinit var sdSPFile: File
        var isLogFile: Boolean = false
    }

    private fun getWechatPath(): String {
        try {
            val pm = activity.packageManager
            val ai = pm.getApplicationInfo(Common.WECHAT_PACKAGENAME, 0)
            return ai.publicSourceDir
        } catch (e: Exception) {
            throw e
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val _this = this
        takePhoto!!.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
//        preferenceManager.setSharedPreferencesMode(Context.MODE_WORLD_READABLE)
        preferenceManager.sharedPreferencesName = Common.MOD_PREFS
        addPreferencesFromResource(R.xml.pref_settings)
        setLayout(preferenceScreen)
        setResolution()

        // 禁用 详细日志
        findPreference(getString(R.string.key_hook_debug))?.apply {
            (this as SwitchPreference).isChecked = false
        }
        findPreference(getString(R.string.key_hook_debug2))?.apply {
            (this as SwitchPreference).isChecked = false
        }

        try {
            wxVersion = Version(ApkFile(getWechatPath()).apkMeta.versionName)
        } catch (e: Exception) {
            e.printStackTrace()
            ToastUtils.showShort(R.string.msg_wechat_notfound)
            generateWechatLogView?.append(getString(R.string.msg_wechat_notfound) + "\n\n")
            wxVersion = Version("999.999.999-unknown")
        }


        //检查微信坂本(适配文件 / 更新坂本)
        setWechatVersionWarning()
        setWechatConfigWarning()
        //测试版警告
        setBetaWarning()

//        //隐藏主界面部分选项
//        val main_settings=findPreference(getString(R.string.key_main_settings))as PreferenceScreen
//        if (wxVersion < Version("7.0.3")) {
////        隐藏 小程序下拉框文字
//            main_settings.removePreference(main_settings.findPreference(getString(R.string.key_mini_program_title)) as SwitchPreference)
//        } else {
////        隐藏 移除小程序
//            main_settings.removePreference(main_settings.findPreference(getString(R.string.key_hook_remove_appbrand)) as SwitchPreference)
//
//            val a = main_settings.findPreference(getString(R.string.key_mini_program_title)) as EditTextPreference
//            a.summary = "当前文字: ${a.text}"
//        }

//        findPreference(getString(R.string.key_tab_layout_on_top))?.onPreferenceChangeListener = this
//        findPreference(getString(R.string.key_mini_program_title))?.onPreferenceChangeListener = this

        findPreference(getString(R.string.key_hook_log))?.apply {
            onPreferenceChangeListener = _this
            STATIC.isLogFile = (this as SwitchPreference).isChecked
        }
        findPreference(getString(R.string.key_hook_log_xposed))?.apply {
            onPreferenceChangeListener = _this
            STATIC.isLogFile = !(this as SwitchPreference).isChecked && STATIC.isLogFile
        }
        findPreference("key_clear_logs")?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_hook_conversation_background_alpha))?.onPreferenceChangeListener = this
        findPreference(getString(R.string.key_pre_inst_color_schemes))?.onPreferenceChangeListener = this
        findPreference(getString(R.string.key_pre_inst_color_schemes1))?.onPreferenceChangeListener = this

        findPreference(getString(R.string.key_hide_launcher_icon))?.onPreferenceChangeListener = this
        findPreference(getString(R.string.key_alipay_red_packet))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_donate))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_donate_wechat))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_donate_joshcai))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_donate_wechat_joshcai))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_feedback))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_reset_wechat_config))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_reset_config))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_reset_float_bottom_config))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_reset_icon_config))?.onPreferenceClickListener = this
//        findPreference(getString(R.string.key_feedback_email_blanke))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_feedback_email_josh_cai))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_feedback_gitee))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_gitee_joshcai))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_github_blanke))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_github_joshcai))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_hook_conversation_bg))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_generate_wechat_config))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_download_wechat_config))?.onPreferenceClickListener = this

        findPreference(getString(R.string.key_joshcai_info))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_attention))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_feedback_group))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_releases))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_start_use))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_Q_A))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_color_scheme_help))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_hook_bg_immersion))?.onPreferenceChangeListener = this
        findPreference(getString(R.string.key_background_help))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_bubble_help))?.onPreferenceClickListener = this
        findPreference(getString(R.string.key_float_button_help))?.onPreferenceClickListener = this

        findPreference(getString(R.string.key_select_bg_chat))?.onPreferenceClickListener = this
        for (i in 0..4) {
            findPreference("${getString(R.string.key_select_bg)}_$i")?.onPreferenceClickListener = this
            findPreference("${getString(R.string.key_select_tab_icon)}_$i")?.onPreferenceClickListener = this
        }
    }

    private fun setBetaWarning() {
        if (BuildConfig.VERSION_NAME.contains("Beta", true)) {
            AlertDialog.Builder(activity)
                    .setTitle("警告")
                    .setMessage("请不要在太极上使用 Beta 版！\n请不要在太极上使用 Beta 版！\n请不要在太极上使用 Beta 版！\n\n" +
                            "当前版本为${BuildConfig.VERSION_NAME}版，不是正式版本，可能会遇到各种问题/无法预估的风险等。故也请不要上传至太极。" +
                            "如果你想反馈问题，请打开最底部的调试开关，重启微信，将/sdcard/mdwechat/logs/目录下当天的日志发我邮箱。" +
                            "谢谢合作！")
                    .setPositiveButton("我知道了", null)
                    .setCancelable(true)
                    .show()
        }
        showAppInfoDialog()
    }

    private fun setWechatConfigWarning() {
        val outputPath = Common.APP_DIR_PATH + Common.CONFIG_WECHAT_DIR + "/${wxVersion}.config"
        if (!isFileExists(outputPath)) {
            AlertDialog.Builder(activity)
//                    .setTitle("警告")
                    .setMessage("未检测到微信适配文件，是否成微信适配文件？（可在通用 -> 微信适配文件中生成）")
                    .setPositiveButton("朕同意了") { _, which -> generateWechatFile() }
                    .setNegativeButton("不了", null)
                    .setCancelable(true)
                    .show()
        }
        showAppInfoDialog()
    }

    private fun setWechatVersionWarning() {
        if (wxVersion > Version(getString(R.string.latest_wechat_version))) {
            findPreference(getString(R.string.key_joshcai_info))?.apply {
                this.layoutResource = R.layout.preference_warning
                this.summary = String.format("${getString(R.string.josh_cai_info_outdated)}\n${getString(R.string.josh_cai_info_outdated_suffix)}", wxVersion, getString(R.string.latest_wechat_version))
            }
        } else {
            findPreference(getString(R.string.key_joshcai_info))?.apply {
                this.summary = String.format(getString(R.string.josh_cai_info), getString(R.string.latest_wechat_version), wxVersion)
            }
        }
    }

    //设置layout
    private fun setLayoutResource(ps: Preference, defaultRes: Int) {
        if (ps.layoutResource != R.layout.preference_warning
                && ps.layoutResource != R.layout.preference_info) {
            ps.layoutResource = defaultRes
        }
    }

    //递归设置layout
    private fun setLayout(preference: Preference) {
        when (preference) {
            is PreferenceScreen -> {
                setLayoutResource(preference, R.layout.preference_screen)
                val cnt = preference.preferenceCount
                for (i in 0 until cnt) {
                    val p = preference.getPreference(i)
                    setLayout(p)
                }
            }
            is PreferenceCategory -> {
                setLayoutResource(preference, R.layout.preference_category)
                val cnt = preference.preferenceCount
                for (i in 0 until cnt) {
                    val p = preference.getPreference(i)
                    setLayout(p)
                }
            }
//            else -> {
//                setLayoutResource(preference, R.layout.preference_screen)
//            }
        }
    }

    override fun onPreferenceChange(preference: Preference, o: Any): Boolean {
        when (preference.key) {
            getString(R.string.key_hook_log) -> STATIC.isLogFile = (findPreference(getString(R.string.key_hook_log)) as SwitchPreference).isChecked
            getString(R.string.key_hook_log_xposed) -> STATIC.isLogFile = !((findPreference(getString(R.string.key_hook_log_xposed)) as SwitchPreference).isChecked)
            getString(R.string.key_hide_launcher_icon) -> showHideLauncherIcon(!(o as Boolean))
            getString(R.string.key_hook_conversation_background_alpha) -> verifyAlpha(o as String)
            getString(R.string.key_pre_inst_color_schemes) -> changeColorScheme(o as String)
            getString(R.string.key_pre_inst_color_schemes1) -> changeColorScheme(o as String)
            getString(R.string.key_hook_bg_immersion) -> showImmersionTips(o as Boolean)
//            getString(R.string.key_mini_program_title) -> setSummary(o as String)
//            getString(R.string.key_tab_layout_on_top) ->setTabLayoutOnTop((o as Boolean))
        }
        return true
    }

    @SuppressLint("ResourceAsColor")
    private fun changeColorScheme(s: String) {
        (findPreference(getString(R.string.key_pre_inst_color_schemes)) as ListPreference).value = s
        (findPreference(getString(R.string.key_pre_inst_color_schemes1)) as ListPreference).value = s
//        try {
        when (s) {
            "immersionDark" -> {
                //tip
                showImmersionTips(true)
                //深色模式
                findPreference(getString(R.string.key_hook_scheme_dark))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_hook_night_mode))?.apply { (this as SwitchPreference).isChecked = false }
                //三色
                findPreference(getString(R.string.key_color_primary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorTransparent)) }
                findPreference(getString(R.string.key_color_secondary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_color_tertiary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                //小程序界面
                findPreference(getString(R.string.key_hook_appbrand_bg_color))?.apply { (this as SwitchPreference).isChecked = false }
                //提示颜色
                findPreference(getString(R.string.key_color_tip))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorTip)) }
                findPreference(getString(R.string.key_color_tip_num))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_change_guide_tip_color))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_color_tip_in_guide))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorTip)) }
                findPreference(getString(R.string.key_color_tip_num_in_guide))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                //主界面字体
                findPreference(getString(R.string.key_hook_main_textcolor))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_main_textcolor_title))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_main_textcolor_content))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                //气泡
                findPreference(getString(R.string.key_lock_bubble_schemes))?.apply {
                    if (!(this as SwitchPreference).isChecked) {
                        //气泡文字
                        findPreference(getString(R.string.key_chat_label_color))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                        findPreference(getString(R.string.key_hook_chat_text_color_left))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                        findPreference(getString(R.string.key_hook_chat_text_color_right))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                        //气泡着色
                        findPreference(getString(R.string.key_hook_chat_settings))?.apply { (this as SwitchPreference).isChecked = true }
                        findPreference(getString(R.string.key_hook_bubble_tint))?.apply { (this as SwitchPreference).isChecked = true }
                        findPreference(getString(R.string.key_hook_bubble_tint_left))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.transparentDark)) }
                        findPreference(getString(R.string.key_hook_bubble_tint_right))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.transparentDark)) }
                    }
                }
                //背景
                findPreference(getString(R.string.key_hook_tab_bg))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_hook_conversation_background_alpha))?.apply { (this as EditTextPreference).text = "0" }
                findPreference(getString(R.string.key_hook_bg_immersion))?.apply { (this as SwitchPreference).isChecked = true }
                //悬浮按钮
                findPreference(getString(R.string.key_hook_float_button_color_is_secondary))?.apply { (this as SwitchPreference).isChecked = true }
                //tablayout 横杠
                findPreference(getString(R.string.key_hook_tab_bar))?.apply { (this as SwitchPreference).isChecked = true }
                //tablayout 着色
                findPreference(getString(R.string.key_tab_layout_main_page_filtered))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_tab_layout_filtered))?.apply { (this as SwitchPreference).isChecked = true }
            }
            "immersionLight" -> {
                showImmersionTips(true)
                //深色模式
                findPreference(getString(R.string.key_hook_scheme_dark))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_hook_night_mode))?.apply { (this as SwitchPreference).isChecked = true }
                //三色
                findPreference(getString(R.string.key_color_primary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white_87)) }
                findPreference(getString(R.string.key_color_secondary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_blue_grey_700)) }
                findPreference(getString(R.string.key_color_tertiary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_blue_grey_700)) }
                //小程序界面
                findPreference(getString(R.string.key_hook_appbrand_bg_color))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_appbrand_bg_color))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.transparentGray)) }
                //提示颜色
                findPreference(getString(R.string.key_color_tip))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_red)) }
                findPreference(getString(R.string.key_color_tip_num))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_change_guide_tip_color))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_color_tip_in_guide))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_red)) }
                findPreference(getString(R.string.key_color_tip_num_in_guide))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                //主界面字体
                findPreference(getString(R.string.key_hook_main_textcolor))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_main_textcolor_title))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_blue_grey_700)) }
                findPreference(getString(R.string.key_main_textcolor_content))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_blue_grey_700)) }
                //气泡
                findPreference(getString(R.string.key_lock_bubble_schemes))?.apply {
                    if (!(this as SwitchPreference).isChecked) {
                        //气泡文字
                        findPreference(getString(R.string.key_chat_label_color))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_blue_grey_700)) }
                        findPreference(getString(R.string.key_hook_chat_text_color_left))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_black)) }
                        findPreference(getString(R.string.key_hook_chat_text_color_right))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_black)) }
                        //气泡着色
                        findPreference(getString(R.string.key_hook_chat_settings))?.apply { (this as SwitchPreference).isChecked = true }
                        findPreference(getString(R.string.key_hook_bubble_tint))?.apply { (this as SwitchPreference).isChecked = true }
                        findPreference(getString(R.string.key_hook_bubble_tint_left))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.transparentLight)) }
                        findPreference(getString(R.string.key_hook_bubble_tint_right))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.transparentLight)) }
                    }
                }
                //背景
                findPreference(getString(R.string.key_hook_tab_bg))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_hook_conversation_background_alpha))?.apply { (this as EditTextPreference).text = "0" }
                findPreference(getString(R.string.key_hook_bg_immersion))?.apply { (this as SwitchPreference).isChecked = true }
                //悬浮按钮
                findPreference(getString(R.string.key_hook_float_button_color_is_secondary))?.apply { (this as SwitchPreference).isChecked = true }
                //tablayout 横杠
                findPreference(getString(R.string.key_hook_tab_bar))?.apply { (this as SwitchPreference).isChecked = true }
                //tablayout 着色
                findPreference(getString(R.string.key_tab_layout_main_page_filtered))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_tab_layout_filtered))?.apply { (this as SwitchPreference).isChecked = true }
            }
            "white" -> {
                //深色模式
                findPreference(getString(R.string.key_hook_scheme_dark))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_hook_night_mode))?.apply { (this as SwitchPreference).isChecked = true }
                //三色
                findPreference(getString(R.string.key_color_primary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_color_secondary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorSecondary)) }
                findPreference(getString(R.string.key_color_tertiary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorTertiary)) }
                //小程序界面
                findPreference(getString(R.string.key_hook_appbrand_bg_color))?.apply { (this as SwitchPreference).isChecked = false }
                //提示颜色
                findPreference(getString(R.string.key_color_tip))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorTip)) }
                findPreference(getString(R.string.key_color_tip_num))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_change_guide_tip_color))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_color_tip_in_guide))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorTip)) }
                findPreference(getString(R.string.key_color_tip_num_in_guide))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                //主界面字体
                findPreference(getString(R.string.key_hook_main_textcolor))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_main_textcolor_title))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_black)) }
                findPreference(getString(R.string.key_main_textcolor_content))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_black)) }
                //气泡
                //气泡文字
                findPreference(getString(R.string.key_chat_label_color))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorSecondary)) }
                findPreference(getString(R.string.key_hook_chat_text_color_left))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorSecondary)) }
                findPreference(getString(R.string.key_hook_chat_text_color_right))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorTertiary)) }
                //气泡着色
                findPreference(getString(R.string.key_hook_chat_settings))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_hook_bubble_tint))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_hook_bubble_tint_left))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_hook_bubble_tint_right))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorSecondary)) }
                //背景
                findPreference(getString(R.string.key_hook_tab_bg))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_hook_conversation_background_alpha))?.apply { (this as EditTextPreference).text = "0" }
                findPreference(getString(R.string.key_hook_bg_immersion))?.apply { (this as SwitchPreference).isChecked = false }
                //悬浮按钮
                findPreference(getString(R.string.key_hook_float_button_color_is_secondary))?.apply { (this as SwitchPreference).isChecked = true }
                //tablayout 横杠
                findPreference(getString(R.string.key_hook_tab_bar))?.apply { (this as SwitchPreference).isChecked = false }
                //tablayout 着色
                findPreference(getString(R.string.key_tab_layout_main_page_filtered))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_tab_layout_filtered))?.apply { (this as SwitchPreference).isChecked = false }
            }
            "classical" -> {
                //深色模式
                findPreference(getString(R.string.key_hook_scheme_dark))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_hook_night_mode))?.apply { (this as SwitchPreference).isChecked = true }
                //三色
                findPreference(getString(R.string.key_color_primary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_color_secondary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_green)) }
                findPreference(getString(R.string.key_color_tertiary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.colorTertiary)) }
                //小程序界面
                findPreference(getString(R.string.key_hook_appbrand_bg_color))?.apply { (this as SwitchPreference).isChecked = false }
                //提示颜色
                findPreference(getString(R.string.key_color_tip))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_red)) }
                findPreference(getString(R.string.key_color_tip_num))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_change_guide_tip_color))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_color_tip_in_guide))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_red)) }
                findPreference(getString(R.string.key_color_tip_num_in_guide))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                //主界面字体
                findPreference(getString(R.string.key_hook_main_textcolor))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_main_textcolor_title))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_black)) }
                findPreference(getString(R.string.key_main_textcolor_content))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_black)) }
                //气泡
                //气泡文字
                findPreference(getString(R.string.key_chat_label_color))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_black_87)) }
                findPreference(getString(R.string.key_hook_chat_text_color_left))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_black)) }
                findPreference(getString(R.string.key_hook_chat_text_color_right))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_black)) }
                //气泡着色
                findPreference(getString(R.string.key_hook_chat_settings))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_hook_bubble_tint))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_hook_bubble_tint_left))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_hook_bubble_tint_right))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.wechat_green)) }
                //背景
                findPreference(getString(R.string.key_hook_tab_bg))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_hook_conversation_background_alpha))?.apply { (this as EditTextPreference).text = "0" }
                findPreference(getString(R.string.key_hook_bg_immersion))?.apply { (this as SwitchPreference).isChecked = false }
                //悬浮按钮
                findPreference(getString(R.string.key_hook_float_button_color_is_secondary))?.apply { (this as SwitchPreference).isChecked = true }
                //tablayout 横杠
                findPreference(getString(R.string.key_hook_tab_bar))?.apply { (this as SwitchPreference).isChecked = false }
                //tablayout 着色
                findPreference(getString(R.string.key_tab_layout_main_page_filtered))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_tab_layout_filtered))?.apply { (this as SwitchPreference).isChecked = false }
            }
            "khaki" -> {
                //深色模式
                findPreference(getString(R.string.key_hook_scheme_dark))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_hook_night_mode))?.apply { (this as SwitchPreference).isChecked = true }
                //三色
                findPreference(getString(R.string.key_color_primary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.khaki)) }
                findPreference(getString(R.string.key_color_secondary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_color_tertiary))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                //小程序界面
                findPreference(getString(R.string.key_hook_appbrand_bg_color))?.apply { (this as SwitchPreference).isChecked = false }
                //提示颜色
                findPreference(getString(R.string.key_color_tip))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.khaki)) }
                findPreference(getString(R.string.key_color_tip_num))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_change_guide_tip_color))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_color_tip_in_guide))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_color_tip_num_in_guide))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.khaki)) }
                //主界面字体
                findPreference(getString(R.string.key_hook_main_textcolor))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_main_textcolor_title))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.khakiDark)) }
                findPreference(getString(R.string.key_main_textcolor_content))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.khakiDark)) }
                //气泡
                //气泡文字
                findPreference(getString(R.string.key_chat_label_color))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.khakiDark)) }
                findPreference(getString(R.string.key_hook_chat_text_color_left))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.khakiDark)) }
                findPreference(getString(R.string.key_hook_chat_text_color_right))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                //气泡着色
                findPreference(getString(R.string.key_hook_chat_settings))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_hook_bubble_tint))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_hook_bubble_tint_left))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.material_white)) }
                findPreference(getString(R.string.key_hook_bubble_tint_right))?.apply { (this as ColorPreference).saveValue(ContextCompat.getColor(context, R.color.khakiDark)) }
                //背景
                findPreference(getString(R.string.key_hook_tab_bg))?.apply { (this as SwitchPreference).isChecked = false }
                findPreference(getString(R.string.key_hook_conversation_background_alpha))?.apply { (this as EditTextPreference).text = "0" }
                findPreference(getString(R.string.key_hook_bg_immersion))?.apply { (this as SwitchPreference).isChecked = false }
                //悬浮按钮
                findPreference(getString(R.string.key_hook_float_button_color_is_secondary))?.apply { (this as SwitchPreference).isChecked = true }
                //tablayout 横杠
                findPreference(getString(R.string.key_hook_tab_bar))?.apply { (this as SwitchPreference).isChecked = true }
                //tablayout 着色
                findPreference(getString(R.string.key_tab_layout_main_page_filtered))?.apply { (this as SwitchPreference).isChecked = true }
                findPreference(getString(R.string.key_tab_layout_filtered))?.apply { (this as SwitchPreference).isChecked = true }
            }
        }
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//        }
    }

    private fun showImmersionTips(o: Boolean) {
        val dontShowImmersionTips = preferenceManager.sharedPreferences.getBoolean("dontShowImmersionTips", false)
        if (o && !dontShowImmersionTips) {
            AlertDialog.Builder(activity)
                    .setTitle("提示")
                    .setMessage(getString(R.string.text_resolution_alert_tips))
                    .setPositiveButton("好的", null)
                    .setNegativeButton("不再提示") { _, which ->
                        preferenceManager.sharedPreferences.edit().putBoolean("dontShowImmersionTips", true).apply()
                    }
                    .setCancelable(true)
                    .show()
        }
    }

    private fun verifyAlpha(s: String) {
        val alpha = s.toInt()
        val p = findPreference(getString(R.string.key_hook_conversation_background_alpha)) as EditTextPreference
        p.text = Math.min(255, Math.max(alpha, 0)).toString()
    }
//    private fun setSummary(s: String) {
//        val a = preferenceScreen.findPreference(getString(R.string.key_mini_program_title)) as EditTextPreference
//        a.summary = "当前文字：$s"
//    }
//    private fun setTabLayoutOnTop(o:Boolean){
//        val hook_hide_actionbar = preferenceScreen.findPreference(getString(R.string.key_hook_hide_actionbar)) as SwitchPreference
//        hook_hide_actionbar.setChecked(o)
//    }

    private fun myTest() {
//        val intent = Intent()
//        intent.action = Intent.ACTION_GET_CONTENT
//        val file = File(LOG_DIR)
//        intent.setDataAndType(Uri.fromFile(file), "*/*")
//        startActivity(intent)
    }


    override fun onPreferenceClick(preference: Preference): Boolean {
        when (preference.key) {
            getString(R.string.key_joshcai_info) -> myTest()

            "key_clear_logs" -> _clearLogs()
            getString(R.string.key_alipay_red_packet) -> donate("https://qr.alipay.com/cpx030345hsmmzm2j44ro47")
            getString(R.string.key_donate) -> donate("https://qr.alipay.com/tsx05730go4ditv2dmwia15")
            getString(R.string.key_donate_wechat) -> donateWechat("f2f0YjlNObKWk7zwpDQoGtBDBe-Cper5cndi")
            getString(R.string.key_donate_joshcai) -> donate("https://qr.alipay.com/fkx12707x8vvnh6mjpqseb4")
            getString(R.string.key_donate_wechat_joshcai) -> donateWechat("f2f0xQLV4IlGwE3CHY7LelHelT0Uqklc-n9W")
            getString(R.string.key_feedback) -> feedback()
            getString(R.string.key_reset_wechat_config) -> copyWechatConfig()
            getString(R.string.key_reset_config) -> deleteConfig()
            getString(R.string.key_reset_float_bottom_config) -> copyFloatBottomConfig()
            getString(R.string.key_reset_icon_config) -> resetIcons()
//            getString(R.string.key_feedback_email_blanke) -> sendEmail()
            getString(R.string.key_feedback_email_josh_cai) -> sendEmailCai()
            getString(R.string.key_feedback_gitee) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat/issues")
            getString(R.string.key_gitee_joshcai) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat")
            getString(R.string.key_github_blanke) -> gotoWebsite("https://github.com/Blankeer/MDWechat")
            getString(R.string.key_github_joshcai) -> gotoWebsite("https://github.com/JoshCai233/MDWechat")
            getString(R.string.key_generate_wechat_config) -> generateWechatFile()
            getString(R.string.key_download_wechat_config) -> downloadWechatConfig()
            getString(R.string.key_attention) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat/wikis/?sort_id=2161250")
            getString(R.string.key_feedback_group) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat/wikis/?sort_id=2161272")
            getString(R.string.key_releases) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat/releases")
            getString(R.string.key_start_use) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat/wikis/?sort_id=2157245")
            getString(R.string.key_Q_A) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat/wikis/?sort_id=2160333")
            getString(R.string.key_color_scheme_help) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat/wikis/?sort_id=2158297")
            getString(R.string.key_background_help) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat/wikis/?sort_id=2158305")
            getString(R.string.key_bubble_help) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat/wikis/?sort_id=2158251")
            getString(R.string.key_float_button_help) -> gotoWebsite("https://gitee.com/JoshCai/MDWechat/wikis/?sort_id=2158249")

            getString(R.string.key_select_bg_chat) -> getImage("tab_bg_chat.png", screenWidth, screenHeight)
            getString(R.string.key_select_bg_0) -> getImage("tab_bg0.png", screenWidth, screenHeight)
            getString(R.string.key_select_bg_1) -> getImage("tab_bg1.png", screenWidth, screenHeight)
            getString(R.string.key_select_bg_2) -> getImage("tab_bg2.png", screenWidth, screenHeight)
            getString(R.string.key_select_bg_3) -> getImage("tab_bg3.png", screenWidth, screenHeight)
            getString(R.string.key_select_tab_icon_0) -> getImage("tab_icon0.png", tab_icon_width, tab_icon_height)
            getString(R.string.key_select_tab_icon_1) -> getImage("tab_icon1.png", tab_icon_width, tab_icon_height)
            getString(R.string.key_select_tab_icon_2) -> getImage("tab_icon2.png", tab_icon_width, tab_icon_height)
            getString(R.string.key_select_tab_icon_3) -> getImage("tab_icon3.png", tab_icon_width, tab_icon_height)
        }
        return true
    }

    private fun _clearLogs() {
        clearFileLogs(STATIC.isLogFile)
        Toast.makeText(activity, getString(R.string.msg_clear_ok), Toast.LENGTH_SHORT).show()
    }

    private fun gotoMarkDownAct(title: String, url: String) {
        MarkDownActivity.start(activity, title, url)
    }

    private fun downloadWechatConfig() {
        DownloadWechatDialog.show(activity)
    }

    private var generateWechatLogView: TextView? = null
    private var generateWechatLogScrollView: ScrollView? = null

    private fun generateWechatFile() {
        generateWechatLogScrollView = ScrollView(activity)
        generateWechatLogView = TextView(activity)
        generateWechatLogView?.setPadding(15, 0, 15, 0)
        generateWechatLogScrollView?.addView(generateWechatLogView)
        AlertDialog.Builder(activity)
                .setView(generateWechatLogScrollView)
                .setTitle(R.string.text_generate_wechat_config)
                .setCancelable(false)
                .setPositiveButton(R.string.text_confirm, null)
                .show()
        val outputPath = Common.APP_DIR_PATH + Common.CONFIG_WECHAT_DIR
        try {
            Main().main(activity.applicationContext, getWechatPath(), outputPath)
        } catch (e: Exception) {
            e.printStackTrace()
            ToastUtils.showShort(R.string.msg_wechat_notfound)
            generateWechatLogView?.append(getString(R.string.msg_wechat_notfound) + "\n\n")
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGerateWechatLogEvent(e: LogEvent) {
        generateWechatLogView?.append(e.msg + "\n\n")
        generateWechatLogScrollView?.fullScroll(View.FOCUS_DOWN)
    }

    private fun copyWechatConfig() {
        thread {
            FileUtils.copyAssets(activity, Common.APP_DIR_PATH, Common.CONFIG_WECHAT_DIR, true)
        }
        Toast.makeText(activity, R.string.msg_reset_ok, Toast.LENGTH_SHORT).show()
    }

    private fun deleteConfig() {
        var success = true
        if (STATIC.sharedPrefsFile.exists()) {
            success = success && STATIC.sharedPrefsFile.delete()
        }
        if (STATIC.sdSPFile.exists()) {
            success = success && STATIC.sdSPFile.delete()
        }
        if (success) {
            Toast.makeText(activity, R.string.msg_reset_mdwechat_ok, Toast.LENGTH_SHORT).show()
            thread {
                Thread.sleep(2000)
                System.exit(0)
            }
        } else {
            Toast.makeText(activity, R.string.msg_reset_failed, Toast.LENGTH_SHORT).show()
        }
    }

    private fun copyFloatBottomConfig() {
        thread {
            FileUtils.copyAssets(activity, Common.APP_DIR_PATH, Common.CONFIG_VIEW_DIR, true)
        }
        Toast.makeText(activity, R.string.msg_reset_ok, Toast.LENGTH_SHORT).show()
    }

    private fun resetIcons() {
        thread {
            FileUtils.copyAssets(activity, Common.APP_DIR_PATH, Common.ICON_DIR, true)
            val nomediaFile = File(Common.APP_DIR_PATH + Common.ICON_DIR + File.separator + ".nomedia")
            if (!nomediaFile.exists()) {
                nomediaFile.createNewFile()
            }
            val file = File(getIconPath(Common.FILE_NAME_CHAT_BG))
            if (file.isFile && file.exists()) {
                file.delete()
            }
        }
        Toast.makeText(activity, R.string.msg_reset_ok, Toast.LENGTH_SHORT).show()
    }

    private fun showHideLauncherIcon(show: Boolean) {
        val p = activity.packageManager
        val componentName = ComponentName(activity, "com.blanke.mdwechat.SettingsLauncher")
//        val componentName = ComponentName(activity, Common.MY_APPLICATION_PACKAGE + ".SettingsLauncher")
        p.setComponentEnabledSetting(componentName,
                if (show) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP)
    }

    private fun donate(payUrl: String = "https://qr.alipay.com/fkx12707x8vvnh6mjpqseb4") {
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        intent.data = Uri.parse("alipayqr://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=" + payUrl)
        if (intent.resolveActivity(activity.packageManager) != null) {
            startActivity(intent)
            return
        }
        intent.data = Uri.parse(payUrl.toLowerCase(Locale.getDefault()))
        startActivity(intent)
    }

    private fun donateWechat(wechatPayCode: String = "f2f0xQLV4IlGwE3CHY7LelHelT0Uqklc-n9W") {
        val className = "com.tencent.mm.plugin.base.stub.WXCustomSchemeEntryActivity"
        val componentName = ComponentName("com.tencent.mm", className)
        try {
            view?.context?.startActivity(Intent(Intent.ACTION_VIEW).apply {
                component = componentName
                data = Uri.parse("weixin://mdwechat/donate/$wechatPayCode")
                flags = Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
            })
            Toast.makeText(view?.context, "模块生效会自动跳转到微信捐赠页面", Toast.LENGTH_SHORT).show()
        } catch (t: Throwable) {
            Toast.makeText(view?.context, "模块未生效,捐赠失败", Toast.LENGTH_SHORT).show()
        }
    }

    private fun feedback() {
        try {
            val str = "market://details?id=" + Common.MY_APPLICATION_PACKAGE
            val intent = Intent("android.intent.action.VIEW")
            intent.data = Uri.parse(str)
            intent.`package` = "com.coolapk.market"
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } catch (e: Exception) {
            startActivity(Intent("android.intent.action.VIEW",
                    Uri.parse("http://www.coolapk.com/apk/" + Common.MY_APPLICATION_PACKAGE)))
        }
    }

    //    private fun sendEmail() {
//        try {
//            val info = "mailto:blanke.master+mdwechat@gmail.com?subject=[MDWechat] 请简明描述该问题" +
//                    "&body=请按以下步骤填写,不按此填写的邮件可能会被忽略,谢谢!%0d%0a[问题描述] 请描述遇到了什么问题%0d%0a[环境]请写明安卓版本 手机 rom xp 微信 版本%0d%0a[日志]可以传附件"
//            val uri = Uri.parse(info)
//            startActivity(Intent(Intent.ACTION_SENDTO, uri))
//        } catch (e: Exception) {
//
//        }
//    }
//
    private fun sendEmailCai() {
        try {
            val info = "mailto:joshcai_mdwechat@163.com?subject=[MDWechat] 请简明描述该问题" +
                    "&body=请按以下步骤填写,不按此填写的邮件可能会被忽略,谢谢!%0d%0a[问题描述] 请描述遇到了什么问题%0d%0a[环境]请写明安卓版本 手机 rom xposed 微信 MDWechat版本%0d%0a[日志]可以传附件\n"
            val uri = Uri.parse(info)
            startActivity(Intent(Intent.ACTION_SENDTO, uri))
        } catch (e: Exception) {

        }
    }

    private fun gotoWebsite(url: String) {
        val uri = Uri.parse(url)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun showAppInfoDialog() {
        val lastLaunchTime = preferenceManager.sharedPreferences.getLong("last_launch_time", -1)
        preferenceManager.sharedPreferences.edit().putLong("last_launch_time", System.currentTimeMillis()).apply()
        if (lastLaunchTime > 0 && TimeUtils.isToday(lastLaunchTime)) {
            return
        }
        val packageManager = activity.applicationContext.packageManager
        val packageInfo = packageManager.getPackageInfo(BuildConfig.APPLICATION_ID, 0)
        val firstInstallTime = Date(packageInfo.firstInstallTime)
        val installDateStr = TimeUtils.getFitTimeSpan(Date(), firstInstallTime, 4)
        val message = getString(R.string.text_app_desc) + "\n" + getString(R.string.text_app_donate, installDateStr)
        AlertDialog.Builder(activity)
                .setTitle(R.string.text_app_tips)
                .setCancelable(true)
                .setMessage(message)
                .setPositiveButton(R.string.text_app_know, null)
                .setNegativeButton(R.string.text_donate_wechat) { dialog, which -> donateWechat() }
                .setNeutralButton(R.string.text_donate_alipay) { dialog, which -> donate() }
                .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    //    private val weChatVersion: String
//        get() = "unKnow"
//
//    private val isSupportWechat: Boolean
//        get() = false
    fun setResolution() {
        val setManually = findPreference(getString(R.string.key_change_resolution)) as SwitchPreference
        val textPreference = findPreference(getString(R.string.key_resolution)) as EditTextPreference
        if (setManually.isChecked) {
            val resolution = textPreference.text
                    .replace(" ", "")
                    .replace("，", ",")
                    .split(",")
            try {
                if ((resolution.count() == 2) && (resolution[0].toInt() > 0) && (resolution[1].toInt() > 0)) {
                    screenWidth = resolution[0].toInt()
                    screenHeight = resolution[1].toInt()
                    return
                }
            } catch (e: java.lang.Exception) {
            }
        }

//        屏幕分辨率检测方式 (新, 包括一些占用屏幕的刘海和虚拟按键)
        val windowManager = activity.getSystemService(WINDOW_SERVICE) as WindowManager
        //获取默认视图 defaultDisplay已经弃用
        val defaultDisplay = windowManager.defaultDisplay
        //new一个Point 实际是通过对象引用的方式获取宽高
        val point = Point()
        //获取实际宽高，数据储存在point中 getRealSize已经弃用
        defaultDisplay.getRealSize(point)
        screenWidth = point.x
        screenHeight = point.y
        textPreference.text = "$screenWidth,$screenHeight"

//        屏幕分辨率检测方式 (老)
//        val dm = resources.displayMetrics
//        screenWidth = dm.widthPixels
//        screenHeight = dm.heightPixels
//        textPreference.text = "$screenWidth,$screenHeight"
    }

    //region 获取背景图片

    val TAG = SettingsFragment::class.java.name
    var invokeParam: InvokeParam? = null

    //    var takePhoto: TakePhoto=TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto
    var takePhoto: TakePhoto? = null
        get() {
            if (field == null) {
                field = TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto
            }
            return field
        }

    private fun getImage(fileName: String, width: Int, height: Int) {
        val file = File(getIconPath(fileName))
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }
        val imageUri = Uri.fromFile(file)
        takePhoto!!.onEnableCompress(null, false)
        takePhoto!!.setTakePhotoOptions(TakePhotoOptions.Builder().create())
        takePhoto!!.onPickFromGalleryWithCrop(imageUri, getCropOptions(width, height))
    }

    fun getCropOptions(width: Int, height: Int): CropOptions? {
        val builder = CropOptions.Builder()
        builder.setOutputX(width).setOutputY(height)
//        builder.setAspectY(width).setAspectY(height)
        builder.setWithOwnCrop(true)//使用自带图片裁剪器
        return builder.create()
    }

//    override fun onSaveInstanceState(outState: Bundle?) {
//        takePhoto!!.onSaveInstanceState(outState)
//        super.onSaveInstanceState(outState)
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        takePhoto!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(activity, type, invokeParam, this)
    }

    override fun takeSuccess(result: TResult) {
        Log.i(TAG, "takeSuccess：" + result.image.compressPath)
    }

    override fun takeFail(result: TResult?, msg: String) {
        Log.i(TAG, "takeFail:$msg")
    }

    override fun takeCancel() {
        Log.i(TAG, resources.getString(org.devio.takephoto.R.string.msg_operation_canceled))
    }

    override fun invoke(invokeParam: InvokeParam): TPermissionType? {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.method)
        if (TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }
    //endregion
}