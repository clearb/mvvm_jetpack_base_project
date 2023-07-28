package com.zyn.lib_base.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.LogUtils

/**
 * packageName  : com.zfkj.lib_base.broadcast
 * classDescribe:
 * author       : wangJiaWei
 * email        : w1119697288@163.com
 * createTime   : 2023/5/24 17:25
 * projectName  : tsenfimbox
 */
class ScreenStatusReceiver : BroadcastReceiver() {

    //屏幕唤醒
    private var SCREEN_ON = "android.intent.action.SCREEN_ON"

    //屏幕息屏
    private var SCREEN_OFF = "android.intent.action.SCREEN_OFF"

    private var lastTime = System.currentTimeMillis()

    override fun onReceive(context: Context, intent: Intent) {
        if (SCREEN_ON == intent.action) {
            //屏幕唤醒操作
            LogUtils.w("ScreenStatusReceiver===》当前屏幕唤醒被触发")
            //判断im是否登录

        } else if (SCREEN_OFF == intent.action) {
            //息屏操作
            LogUtils.w("ScreenStatusReceiver===》息屏操作")
        }
    }
}