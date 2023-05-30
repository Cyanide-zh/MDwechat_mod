package com.blanke.mdwechat.bean

/**
 * Created by blanke on 2017/10/13.
 */

data class PicPositionConfig(
        var lastModifiedTimeOfSettings: Long = 0,
        var screenHeight: Int = -1,
        var backgroundPicPos: MutableList<PicPosition>?
)

data class PicPosition(var y: Int = 0, var height: Int = 0)