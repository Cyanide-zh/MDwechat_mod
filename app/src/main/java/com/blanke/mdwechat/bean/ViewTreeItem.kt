package com.blanke.mdwechat.bean

class ViewTree(
        val treeStacks: Map<String, IntArray> = mapOf(),
        val item: ViewTreeItem
)
class ViewTreeItem(
        val clazz: String,
        val children: Array<ViewTreeItem?> = arrayOf()
)