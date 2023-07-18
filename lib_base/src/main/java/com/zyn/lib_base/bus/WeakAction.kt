package com.zfkj.lib_base.bus

import com.zyn.lib_base.binding.command.BindingAction
import com.zyn.lib_base.binding.command.BindingConsumer
import java.lang.ref.WeakReference

/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述: About : kelin的WeakBindingAction
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
class WeakAction<T> {
    var bindingAction: BindingAction? = null
        private set
    private var consumer: BindingConsumer<T>? = null
    val isLive: Boolean
        get() {
            if (reference == null) {
                return false
            }
            return reference!!.get() != null
        }
    val target: Any?
        get() = if (reference != null) {
            reference!!.get()
        } else null
    private var reference: WeakReference<*>?

    constructor(target: Any?, action: BindingAction?) {
        reference = WeakReference(target)
        bindingAction = action
    }

    constructor(target: Any?, consumer: BindingConsumer<T>?) {
        reference = WeakReference(target)
        this.consumer = consumer
    }

    fun execute() {
        if (bindingAction != null && isLive) {
            bindingAction!!.call()
        }
    }

    fun execute(parameter: T) {
        if (consumer != null && isLive) {
            consumer!!.call(parameter)
        }
    }

    fun markForDeletion() {
        reference!!.clear()
        reference = null
        bindingAction = null
        consumer = null
    }

    val bindingConsumer: BindingConsumer<*>?
        get() = consumer
}