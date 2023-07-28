package com.zyn.http.interceptor.log

/**
 * packageName  : com.zyn.http.interceptor.log
 * classDescribe: 日志Manager
 * author       : zanyanneng
 * email        : 2529240409@qq.com
 * createTime   : 2023/7/25 17:17
 * projectName  : MyBaseProject
 *
 **/
object LogManager {

    private var logProxy: LogProxy? = null

    fun logProxy(logProxy: LogProxy) {
        LogManager.logProxy = logProxy
    }

    fun e(tag:String , msg:String) {
        logProxy?.e(tag,msg)
    }

    fun w(tag:String , msg:String) {
        logProxy?.w(tag,msg)
    }

    fun i(tag:String , msg:String) {
        logProxy?.i(tag,msg)
    }

    fun d(tag:String , msg:String) {
        logProxy?.d(tag,msg)
    }
}