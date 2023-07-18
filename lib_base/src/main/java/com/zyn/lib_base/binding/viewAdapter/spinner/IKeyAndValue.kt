package com.zyn.lib_base.binding.viewAdapter.spinner

/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:下拉Spinner控件的键值对, 实现该接口,返回key,value值, 在xml绑定List<IKeyAndValue>
 * 创建者 @author:YangJiangLin[PHONE：181****4380]
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/2/17
 * 项目名称:TsenfBase
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */
interface IKeyAndValue {
    val key: String?
    val value: String?
}