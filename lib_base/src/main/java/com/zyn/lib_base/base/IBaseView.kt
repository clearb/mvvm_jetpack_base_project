package com.zyn.lib_base.base

import android.os.Bundle

/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:
 * 创建者 @author:YangJiangLin[PHONE：181****4380]
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/2/17 11:37
 * 项目名称:TsenfBase
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */
interface IBaseView {
    /**
     * 初始化界面传递参数
     */
    fun initParam()

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 初始化界面观察者的监听
     */
    fun initViewObservable()

    /**
     * 初始化View
     */
    fun initView(savedInstanceState: Bundle?)

    /**
     * 权限检查回调
     */
    fun checkPermissionsCallback(var1: Boolean) {

    }


    /**
     * 魔盒蓝牙连接
     */
    fun onConnectBoxBlue() {

    }
}