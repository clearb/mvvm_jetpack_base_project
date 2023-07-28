package com.zyn.http.interceptor.log

/**
 * packageName  : com.zyn.http.interceptor.log
 * classDescribe:
 * author       : zanyanneng
 * email        : 2529240409@qq.com
 * createTime   : 2023/7/25 17:18
 * projectName  : MyBaseProject
 *
 **/
interface LogProxy {

    fun e(tag:String , msg:String)

    fun w(tag:String , msg:String)

    fun i(tag:String , msg:String)

    fun d(tag:String , msg:String)
}