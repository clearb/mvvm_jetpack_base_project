package com.zyn.lib_base.data

import com.zyn.lib_base.base.BaseModel
import com.zyn.lib_base.data.source.HttpDataSource
import com.zyn.lib_base.data.source.LocalDataSource


/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:数据中心（本地+在线） 外部通过Koin依赖注入调用
 * 对于缓存或者在线数据的增删查改统一通过该数据仓库调用
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
class DataRepository constructor(
    private val mLocalDataSource: LocalDataSource,
    private val mHttpDataSource: HttpDataSource
) : BaseModel(), LocalDataSource, HttpDataSource {

    /**
     * 网络操作
     */






}