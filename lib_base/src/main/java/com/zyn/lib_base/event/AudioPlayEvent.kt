package com.zfkj.lib_base.event

/**
 * packageName  : com.tsenf.im.entity
 * classDescribe: 音乐播放事件
 * author       : zanyanneng
 * email        : 2529240409@qq.com
 * createTime   : 2023/5/11 16:48
 * projectName  : tsenfimbox
 *
 **/
data class AudioPlayEvent(
    var duration: Int,
    var currentPosition: Int
) {
    companion object {
        const val KEY = "AUDIO_PLAY_EVENT_KEY"
    }
}