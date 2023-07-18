package com.zyn.lib_base.binding.viewAdapter.scrollview

import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
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
    @JvmStatic
    @BindingAdapter("onScrollChangeCommand")
    fun onScrollChangeCommand(
        nestedScrollView: NestedScrollView,
        onScrollChangeCommand: BindingCommand<NestScrollDataWrapper?>?
    ) {
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            onScrollChangeCommand?.execute(
                NestScrollDataWrapper(scrollX, scrollY, oldScrollX, oldScrollY)
            )
        })
    }
    @JvmStatic
    @BindingAdapter("onScrollChangeCommand")
    fun onScrollChangeCommand(
        scrollView: ScrollView,
        onScrollChangeCommand: BindingCommand<ScrollDataWrapper?>?
    ) {
        scrollView.viewTreeObserver.addOnScrollChangedListener {
            onScrollChangeCommand?.execute(
                ScrollDataWrapper(
                    scrollView.scrollX.toFloat(), scrollView.scrollY.toFloat()
                )
            )
        }
    }

    class ScrollDataWrapper(var scrollX: Float, var scrollY: Float)
    class NestScrollDataWrapper(
        var scrollX: Int,
        var scrollY: Int,
        var oldScrollX: Int,
        var oldScrollY: Int
    )
}