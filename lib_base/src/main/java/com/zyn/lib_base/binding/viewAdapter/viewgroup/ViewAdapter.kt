package com.zyn.lib_base.binding.viewAdapter.viewgroup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import me.tatarka.bindingcollectionadapter2.ItemBinding

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
    @BindingAdapter("itemView", "observableList")
    fun addViews(
        viewGroup: ViewGroup,
        itemBinding: ItemBinding<*>,
        viewModelList: ObservableList<IBindingItemViewModel<ViewDataBinding>>?
    ) {
        if (viewModelList != null && !viewModelList.isEmpty()) {
            viewGroup.removeAllViews()
            for (viewModel in viewModelList) {
                val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(viewGroup.context),
                    itemBinding.layoutRes(), viewGroup, true
                )
                binding.setVariable(itemBinding.variableId(), viewModel)
                viewModel.injectDataBinding(binding)
            }
        }
    }
}