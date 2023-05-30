package com.blanke.mdwechat.util

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.blanke.mdwechat.Objects.Main.context
import com.blanke.mdwechat.config.AppCustomConfig
import com.blanke.mdwechat.config.HookConfig
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by blanke on 2017/10/3.
 */

object LogUtil {
    private object STATIC {
        var logged = mutableSetOf<String>()
    }

    private val dateStr
        get() = SimpleDateFormat("yyyy-MM-dd").format(Date())

    fun exportLog(log: String) {
        try {
            if (HookConfig.is_hook_log_xposed) {
                XposedBridge.log("MDWechatModule: " + log)
                return
            }
        } catch (e: Exception) {
        }
        val logFile = File(AppCustomConfig.getLogFile(dateStr))
        logFile.parentFile.mkdirs()
        val time = SimpleDateFormat("HH:mm:ss").format(Date())
        FileUtils.write(logFile.absolutePath, "$time $log\n", true)

    }

    fun clearFileLogs(isLogFile: Boolean = false) {
        val logFile = File(AppCustomConfig.getLogFile("MDWechat_log")).parentFile
        FileUtils.deleteDirectoryFiles(logFile, "MDWechat_log_")
//        val result = logFile.delete()
        logFile.mkdirs()
        if (isLogFile) {
            exportLog("------- beginning of log -------")
        }
        STATIC.logged.clear()
    }

    @JvmStatic
    fun logOnlyOnce(key: String, value: String = "Hook") {
        try {
            if (!HookConfig.is_hook_log) return
        } catch (e: RuntimeException) {
        }
        if (STATIC.logged.indexOf(key) > -1) {
            return
        }
        STATIC.logged.add(key)
        log("$key $value")
    }

    @JvmStatic
    fun log(msg: String) {
        try {
            if (HookConfig.is_hook_log) exportLog("MDwechat Log: " + msg)
        } catch (e: RuntimeException) {
        }
        Log.i("MDWechatModule", "MDWechat $msg")
    }

    @JvmStatic
    fun log(t: Throwable) {
        try {
            if (HookConfig.is_hook_log) exportLog("LogUtil: " + Log.getStackTraceString(t))
        } catch (e: RuntimeException) {
        }
        Log.e("MDWechatModule", "MDWechat " + Log.getStackTraceString(t))
    }

    @JvmStatic
    fun toast(s: String, longToast: Boolean = false) {
        context?.apply {
            try {
                Toast.makeText(this, s, if (longToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                log("toast错误")
                log(e)
            }
        }
    }

    fun bundleToString(bundle: Bundle?): String? {
        val str = bundle?.keySet()?.joinToString(", ") {
            "$it = ${bundle[it]}"
        }
        return "{$str}"
    }

    fun logSuperClasses(clazz: Class<*>) {
        log(clazz.name)
        if (clazz.superclass != null) {
            logSuperClasses(clazz.superclass)
        }
    }

    fun logStackTraces(methodCount: Int = 15, methodOffset: Int = 3) {
        val trace = Thread.currentThread().stackTrace
        var level = ""
        log("---------logStackTraces start----------")
        for (i in methodCount downTo 1) {
            val stackIndex = i + methodOffset
            if (stackIndex >= trace.size) {
                continue
            }
            val builder = StringBuilder()
            builder.append("|")
                    .append(' ')
                    .append(level)
                    .append(trace[stackIndex].className)
                    .append(".")
                    .append(trace[stackIndex].methodName)
                    .append(" ")
                    .append(" (")
                    .append(trace[stackIndex].fileName)
                    .append(":")
                    .append(trace[stackIndex].lineNumber)
                    .append(")")
            level += "   "
            log(builder.toString())
        }
        log("---------logStackTraces end----------")
    }

    fun logParentView(view: View, level: Int = 5) {
        log("---------logParentView start----------")
        var currentView = view
        for (i in 0 until level) {
            val v = currentView.parent
            if (v != null && v is View) {
                logView(v)
                currentView = v
            } else {
                break
            }
        }
        log("---------logParentView end----------")
    }

    fun logView(view: View) {
        log(getViewLogInfo(view))
    }

    fun getViewLogInfo(view: View): String {
        val sb = StringBuffer(view.toString())
        if (view is TextView) {
            sb.append("${view.text}(" + view.hint + ")")
        } else if (view.javaClass.name == "com.tencent.mm.ui.base.NoMeasuredTextView") {
            sb.append("${XposedHelpers.getObjectField(view, "mText")}")
        } else if (view is ViewGroup) {
            sb.append(" childCount = ${view.childCount}")
        }
        sb.append(" desc= ${view.contentDescription}")
        if (view.background is ColorDrawable) {
            val bg = view.background as ColorDrawable
            sb.append(" bgColor=${bg.color}")
        }
        return sb.toString()
    }

    fun logViewStackTraces(view: View, level: Int = 0) {
        val sb = StringBuffer()
        for (i in 0..level) {
            sb.append("\t")
        }
        sb.append(getViewLogInfo(view))
        log(sb.toString())
        try {
//            替换 if (view is ViewGroup)
            val viewGroup = view as ViewGroup
            for (i in 0 until viewGroup.childCount) {
                logViewStackTraces(viewGroup.getChildAt(i), level + 1)
            }
        } catch (e: ClassCastException) {
        }
    }

    fun findTextViewStackTrace(view: View, text: String): View? {
        if (view is TextView) {
            if (view.text.contains(text)) {
                return view
            }
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val res = findTextViewStackTrace(view.getChildAt(i), text)
                if (res != null) {
                    return res
                }
            }
        }
        return null
    }
}
