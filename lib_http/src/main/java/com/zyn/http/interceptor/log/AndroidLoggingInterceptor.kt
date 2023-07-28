package com.zyn.http.interceptor.log

/**
 * packageName  : com.zyn.http.interceptor.log
 * classDescribe: 日志拦截器实现类
 * author       : zanyanneng
 * email        : 2529240409@qq.com
 * createTime   : 2023/7/27 17:18
 * projectName  : MyBaseProject
 *
 **/
object AndroidLoggingInterceptor {

    @JvmOverloads
    @JvmStatic
    fun build(isDebug:Boolean = true, hideVerticalLine:Boolean = false, requestTag:String = "Request", responseTag:String = "Response"): LoggingInterceptor {
        return if (hideVerticalLine) {
            LoggingInterceptor.Builder()
                .loggable(isDebug) // TODO: 发布到生产环境需要改成false
                .androidPlatform()
                .request()
                .requestTag(requestTag)
                .response()
                .responseTag(responseTag)
                .hideVerticalLine()// 隐藏竖线边框
                .build()
        } else {
            LoggingInterceptor.Builder()
                .loggable(isDebug) // TODO: 发布到生产环境需要改成false
                .androidPlatform()
                .request()
                .requestTag(requestTag)
                .response()
                .responseTag(responseTag)
//                    .hideVerticalLine()// 隐藏竖线边框
                .build()
        }
    }
}