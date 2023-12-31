package com.zyn.lib_base.binding.viewAdapter.listview

import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ListView
import androidx.databinding.BindingAdapter
import com.zyn.lib_base.binding.command.BindingCommand
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

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
        value = ["onScrollChangeCommand", "onScrollStateChangedCommand"],
        requireAll = false
    )
    fun onScrollChangeCommand(
        listView: ListView,
        onScrollChangeCommand: BindingCommand<ListViewScrollDataWrapper?>?,
        onScrollStateChangedCommand: BindingCommand<Int?>?
    ) {
        listView.setOnScrollListener(object : AbsListView.OnScrollListener {
            private var scrollState = 0
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
                this.scrollState = scrollState
                onScrollStateChangedCommand?.execute(scrollState)
            }

            override fun onScroll(
                view: AbsListView,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                onScrollChangeCommand?.execute(
                    ListViewScrollDataWrapper(
                        scrollState,
                        firstVisibleItem,
                        visibleItemCount,
                        totalItemCount
                    )
                )
            }
        })
    }

    @JvmStatic
    @BindingAdapter(value = ["onItemClickCommand"], requireAll = false)
    fun onItemClickCommand(listView: ListView, onItemClickCommand: BindingCommand<Int?>?) {
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                onItemClickCommand?.execute(position)
            }
    }

    @JvmStatic
    @BindingAdapter("onLoadMoreCommand")
    fun onLoadMoreCommand(listView: ListView, onLoadMoreCommand: BindingCommand<Int?>) {
        listView.setOnScrollListener(OnScrollListener(listView, onLoadMoreCommand))
    }

    class OnScrollListener(listView: ListView, onLoadMoreCommand: BindingCommand<Int?>) :
        AbsListView.OnScrollListener {
        private val methodInvoke = PublishSubject.create<Int>()
        private val onLoadMoreCommand: BindingCommand<Int?>?
        private val listView: ListView
        override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}
        override fun onScroll(
            view: AbsListView,
            firstVisibleItem: Int,
            visibleItemCount: Int,
            totalItemCount: Int
        ) {
            if (firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount != 0 && (totalItemCount != listView.headerViewsCount
                        + listView.footerViewsCount)
            ) {
                if (onLoadMoreCommand != null) {
                    methodInvoke.onNext(totalItemCount)
                }
            }
        }

        init {
            this.onLoadMoreCommand = onLoadMoreCommand
            this.listView = listView
            methodInvoke.throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { integer -> onLoadMoreCommand.execute(integer) }
        }
    }

    class ListViewScrollDataWrapper(
        var scrollState: Int,
        var firstVisibleItem: Int,
        var visibleItemCount: Int,
        var totalItemCount: Int
    )
}