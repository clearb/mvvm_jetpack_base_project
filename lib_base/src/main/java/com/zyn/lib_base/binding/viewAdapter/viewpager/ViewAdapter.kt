package com.zyn.lib_base.binding.viewAdapter.viewpager

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
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
    @BindingAdapter(
        value = ["onPageScrolledCommand", "onPageSelectedCommand", "onPageScrollStateChangedCommand"],
        requireAll = false
    )
    fun onScrollChangeCommand(
        viewGroup: ViewGroup?,
        onPageScrolledCommand: BindingCommand<ViewPagerDataWrapper?>?,
        onPageSelectedCommand: BindingCommand<Int?>?,
        onPageScrollStateChangedCommand: BindingCommand<Int?>?
    ) {
        if (viewGroup is ViewPager2) {
            viewGroup.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    onPageSelectedCommand?.execute(position)
                }
            })
        }
        if (viewGroup is ViewPager) {
            viewGroup.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                private var state = 0
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    onPageScrolledCommand?.execute(
                        ViewPagerDataWrapper(
                            position.toFloat(),
                            positionOffset,
                            positionOffsetPixels,
                            state
                        )
                    )
                }

                override fun onPageSelected(position: Int) {
                    onPageSelectedCommand?.execute(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    this.state = state
                    onPageScrollStateChangedCommand?.execute(state)
                }
            })
        }
    }

    class ViewPagerDataWrapper(
        var position: Float,
        var positionOffset: Float,
        var positionOffsetPixels: Int,
        var state: Int
    )
}