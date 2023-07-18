package com.zyn.lib_base.binding.viewAdapter.edittext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.KeyboardUtils
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
     * EditText重新获取焦点的事件绑定
     */
    @JvmStatic
    @BindingAdapter(value = ["requestFocus"], requireAll = false)
    fun requestFocusCommand(editText: EditText, needRequestFocus: Boolean) {
        if (needRequestFocus) {
            editText.setSelection(editText.text.length)
            editText.requestFocus()
            editText.isFocusableInTouchMode = needRequestFocus
            KeyboardUtils.showSoftInput(editText)
        }else{
            KeyboardUtils.hideSoftInput(editText)
            editText.clearFocus()
        }

    }

    /**
     * EditText输入文字改变的监听
     */
    @JvmStatic
    @BindingAdapter(value = ["textChanged"], requireAll = false)
    fun addTextChangedListener(editText: EditText, textChanged: BindingCommand<String?>?) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(text: CharSequence, i: Int, i1: Int, i2: Int) {
                textChanged?.execute(text.toString())
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }
}