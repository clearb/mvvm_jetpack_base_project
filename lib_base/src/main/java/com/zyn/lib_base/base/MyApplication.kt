package com.zyn.lib_base.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.zyn.lib_base.BuildConfig

/**
 * packageName  : com.zyn.lib_base.base
 * classDescribe: 整个项目的Application
 * author       : zanyanneng
 * email        : 2529240409@qq.com
 * createTime   : 2023/7/4 17:14
 * projectName  : MyBaseProject
 *
 **/
open class MyApplication : Application()  {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        //  初始化ARouter 路由
        ARouter.init(this)
    }
}