package com.zyn.lib_base.binding.viewAdapter.mswitch

import android.widget.CompoundButton
import androidx.appcompat.widget.SwitchCompat
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
     * 设置开关状态
     *
     * @param mSwitch Switch控件
     */
    @JvmStatic
    @BindingAdapter("switchState")
    fun setSwitchState(mSwitch: SwitchCompat, isChecked: Boolean) {
        mSwitch.isChecked = isChecked
    }

    /**
     * Switch的状态改变监听
     *
     * @param mSwitch        Switch控件
     * @param changeListener 事件绑定命令
     */
    @JvmStatic
    @BindingAdapter("onSwitchCheckedCommand")
    fun onCheckedChangeCommand(mSwitch: SwitchCompat, changeListener: BindingCommand<Boolean>?) {
        if (changeListener != null) {
            mSwitch.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
                changeListener.execute(
                    isChecked
                )
            }
        }
    }
}