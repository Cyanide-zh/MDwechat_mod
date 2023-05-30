package com.blanke.mdwechat.util

import android.view.View
import android.view.ViewGroup

object ViewUtils {

    fun measureHeight(view: View): Int {
        val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(w, w)
        return view.measuredHeight
    }

    fun getChildView(view: View, vararg indexs: Int): View? {
        var parentView: View = view
        var childView: View? = null
        for (index in indexs) {
            childView = getChildView(parentView, index) ?: break
            parentView = childView
        }
        return childView
    }

    fun getChildView(view: View, index: Int): View? {
        if (view is ViewGroup) {
            return view.getChildAt(index)
        }
        return null
    }

    fun getChildView1(view: View, indexs: IntArray?): View? {
        if (indexs == null) return null
        var parentView: View = view
        var childView: View? = null
        for (index in indexs) {
            childView = getChildView(parentView, index) ?: break
            parentView = childView
        }
        return childView
    }

    fun getParentView(view: View, index: Int): View? {
        var currentView = view
        for (i in 0 until index) {
            val v = currentView.parent
            if (v != null && v is View) {
                currentView = v
            } else {
                return null
            }
        }
        return currentView
    }

    fun getParentViewSafe(view: View, index: Int): View {
        var currentView = view
        for (i in 0 until index) {
            val v = currentView.parent
            if (v != null && v is View) {
                currentView = v
            } else {
                break
            }
        }
        return currentView
    }

    fun findFirstChildView(rootView: ViewGroup, viewClassName: String): View? {
        val childCount = rootView.childCount
        for (i in 0 until childCount) {
            val v = rootView.getChildAt(i)
            if (v.javaClass.name == viewClassName) {
                return v
            } else if (v is ViewGroup) {
                return findFirstChildView(v, viewClassName)
            }
        }
        return null
    }

    fun findLastChildView(rootView: ViewGroup, viewClassName: String): View? {
        val childCount = rootView.childCount
        for (i in childCount - 1 downTo 0) {
            val v = rootView.getChildAt(i)
            if (v.javaClass.name == viewClassName) {
                return v
            } else if (v is ViewGroup) {
                return findLastChildView(v, viewClassName)
            }
        }
        return null
    }
}