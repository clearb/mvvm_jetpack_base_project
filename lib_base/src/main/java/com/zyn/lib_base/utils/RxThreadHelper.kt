package com.zyn.lib_base.utils

import android.view.View
import com.trello.rxlifecycle3.android.RxLifecycleAndroid
import com.zyn.lib_base.base.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
object RxThreadHelper {
    fun <T> rxSchedulerHelper(viewModel: BaseViewModel<*>?): ObservableTransformer<T, T> {    //compose简化线程
        return ObservableTransformer { observable: Observable<T> ->
            observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //                .compose(RxUtils.bindToLifecycle(viewModel.getLifecycleProvider()))
                .doOnSubscribe(viewModel)
        } //请求与ViewModel周期同步;
    }

    fun <T> rxSchedulerHelper(view: View?): ObservableTransformer<T, T> {    //compose简化线程
        return ObservableTransformer { observable: Observable<T> ->
            observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .compose(RxLifecycleAndroid.bindView(view!!))
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> rxFlowSchedulerHelper(): FlowableTransformer<T, T> {    //compose简化线程
        return FlowableTransformer { observable: Flowable<T> ->
            observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {    //compose简化线程
        return ObservableTransformer { observable: Observable<T> ->
            observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}