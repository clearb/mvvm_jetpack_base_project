package com.zyn.lib_base.binding.viewAdapter.spinner

import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
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
     * 双向的SpinnerViewAdapter, 可以监听选中的条目,也可以回显选中的值
     *
     * @param spinner        控件本身
     * @param itemDatas      下拉条目的集合
     * @param valueReply     回显的value
     * @param bindingCommand 条目点击的监听
     */
    @JvmStatic
    @BindingAdapter(
        value = ["itemDatas", "valueReply", "resource", "dropDownResource", "onItemSelectedCommand"],
        requireAll = false
    )
    fun onItemSelectedCommand(
        spinner: Spinner,
        itemDatas: List<IKeyAndValue>?,
        valueReply: String,
        resource: Int,
        dropDownResource: Int,
        bindingCommand: BindingCommand<IKeyAndValue?>
    ) {
        var resource = resource
        var dropDownResource = dropDownResource
        if (itemDatas == null) {
            throw NullPointerException("this itemDatas parameter is null")
        }
        val lists: MutableList<String?> = ArrayList()
        for (iKeyAndValue in itemDatas) {
            lists.add(iKeyAndValue.key)
        }
        if (resource == 0) {
            resource = android.R.layout.simple_spinner_item
        }
        if (dropDownResource == 0) {
            dropDownResource = android.R.layout.simple_spinner_dropdown_item
        }
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(spinner.context, resource, lists)
        adapter.setDropDownViewResource(dropDownResource)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val iKeyAndValue = itemDatas[position]
                //将IKeyAndValue对象交给ViewModel
                bindingCommand.execute(iKeyAndValue)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        //回显选中的值
        if (!TextUtils.isEmpty(valueReply)) {
            for (i in itemDatas.indices) {
                val iKeyAndValue = itemDatas[i]
                if (valueReply == iKeyAndValue.value) {
                    spinner.setSelection(i)
                    return
                }
            }
        }
    }
}