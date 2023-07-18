package com.zyn.lib_base.binding.viewAdapter.checkbox

import android.widget.CheckBox
import androidx.databinding.BindingAdapter
import com.zyn.lib_base.binding.command.BindingCommand

/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:
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
object ViewAdapter {
    /**
     * @param bindingCommand //绑定监听
     */
    @JvmStatic
    @BindingAdapter(value = ["onCheckedChangedCommand"], requireAll = false)
    fun setCheckedChanged(checkBox: CheckBox, bindingCommand: BindingCommand<Boolean?>) {
        checkBox.setOnCheckedChangeListener { compoundButton, b -> bindingCommand.execute(b) }
    }
}