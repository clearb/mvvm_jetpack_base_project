package com.zyn.lib_base.base

import android.app.Application
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.trello.rxlifecycle3.LifecycleProvider
import com.zyn.lib_base.R
import com.zyn.lib_base.binding.command.BindingAction
import com.zyn.lib_base.binding.command.BindingCommand
import com.zyn.lib_base.event.callback.UIChangeLiveData
import com.zyn.lib_base.utils.ToastHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.lang.ref.WeakReference

/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:  由于ViewModel工厂通过反射动态实例化，无需再手动构造实例
 * 子类继承构造指定为 application: MyApplication, model: DataRepository 泛型为<DataRepository>。
 * 创建者 @author:YangJiangLin[PHONE：181****4380]
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/2/17 11:36
 * 项目名称:TsenfBase
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */
open class BaseViewModel<M : BaseModel>(application: MyApplication, val model: M) :
    AndroidViewModel(application), IBaseViewModel, Consumer<Disposable?> {

    val uC: UIChangeLiveData = UIChangeLiveData()

    // 标题栏标题
    var tvTitle = ObservableField("")

    // 标题颜色
    var tvColor = ObservableInt(R.color.black)

    // 标题栏右文字
    val toolbarRightText = ObservableField("")

    // 标题栏右图标id
    val ivToolbarIconRes = ObservableInt(0)

    // 标题文本右边的图标id
    var ivTitleImg = ObservableInt()

    var btnBackImg = ObservableInt(R.drawable.ic_back_black_24dp)

    // 标题栏返回箭头的显示隐藏
    val btnBackVisibility = ObservableBoolean(false)

    // 标题左边边icon显示隐藏
    val ivWidgetisibility = ObservableBoolean(false)

    //标题栏背景
    val ivWidgetImg = ObservableInt(0)

    // 标题右边icon显示隐藏
    val ivToolbarIconVisibility = ObservableBoolean(false)

    // 标题文本右边的图标是否显示
    val ivTitleVisibility = ObservableBoolean(false)

    //标题栏背景
    val toolbarBackground = ObservableInt(R.color.color_toolbar)

    /**
     * 标题栏右图标点击事件 VM层重写setToolbarRightClick()
     */
    var ivToolbarIconOnClick = BindingCommand<Void>(BindingAction {
        if (ivToolbarIconRes.get() != 0 || !toolbarRightText.get().isNullOrBlank())
            setToolbarRightClick()
    })

    var ivTitleBarImgOnClick = BindingCommand<Void>(BindingAction {
        if (ivTitleImg.get() != 0) {
            setIvTitleClick()
        }
    })

    val toolbarRightTextOnClick = BindingCommand<Void>(BindingAction {
        if (toolbarRightText.get() != null) {
            setToolbarRightTxtClick()
        }
    })

    //弱引用持有
    private lateinit var lifecycle: WeakReference<LifecycleProvider<*>>

    //管理RxJava，主要针对RxJava异步操作造成的内存泄漏
    var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    open fun addSubscribe(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    // 子类重写
    open fun setToolbarRightClick() {

    }

    /*
     * 设置标题右边图标点击事件
     *  子类重写点击事件
     */
    open fun setIvTitleClick() {

    }

    /*
     *  设置标题栏右边文字点击事件
     */
    open fun setToolbarRightTxtClick() {

    }

    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    fun injectLifecycleProvider(lifecycle: LifecycleProvider<*>) {
        this.lifecycle = WeakReference(lifecycle)
    }

    val lifecycleProvider: LifecycleProvider<*>?
        get() = lifecycle.get()


    fun showLoading(title: String? = "加载中") {
        uC.showLoadingEvent.postValue(title)
    }

    fun dismissLoading() {
        uC.dismissDialogEvent.call()
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(routePath: String, bundle: Bundle? = null) {
        val params: HashMap<String, Any> = HashMap()
        params[ParameterField.ROUTE_PATH] = routePath
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        uC.startActivityEvent.postValue(params)
    }

    private fun startFragment(routePath: String, bundle: Bundle? = null) {
        val params: HashMap<String, Any> = HashMap()
        params[ParameterField.ROUTE_PATH] = routePath
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        uC.startFragmentEvent.postValue(params)
    }

    /**
     * 跳转容器页面
     * @param routePath Fragment路由地址
     * @param bundle    跳转所携带的信息
     */
    fun startContainerActivity(
        routePath: String,
        bundle: Bundle? = null, requestCode: Int? = null
    ) {
        val params: MutableMap<String, Any> = HashMap()
        params[ParameterField.ROUTE_PATH] = routePath
        if (bundle != null) params[ParameterField.BUNDLE] = bundle
        if (requestCode != null) params[ParameterField.REQUEST_CODE] = requestCode
        uC.startContainerActivityEvent.postValue(params)
    }

    /**
     * 关闭界面
     */
    fun finish() {
        uC.finishEvent.call()
    }

    /**
     * 返回上一层
     */
    fun onBackPressed() {
        uC.onBackPressedEvent.call()
    }

    fun showErrorToast(msg: String?) {
        ToastHelper.showErrorToast(msg)
    }

    fun showNormalToast(msg: String?) {
        ToastHelper.showNormalToast(msg)
    }

    fun showSuccessToast(msg: String?) {
        ToastHelper.showSuccessToast(msg)
    }

    /**
     * 获取字符串
     */
    fun getString(@StringRes id: Int, vararg args: Any?): String {
        return getApplication<Application>().getString(id, *args)
    }

    /**
     * 获取颜色
     */
    fun getColor(@ColorRes id: Int): Int {
        return getApplication<Application>().getColor(id)
    }

    override fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {}
    override fun onCreate() {}
    override fun onDestroy() {}
    override fun onStart() {}
    override fun onStop() {}
    override fun onResume() {}
    override fun onPause() {}

    override fun onCleared() {
        super.onCleared()
        model.onCleared()
        //ViewModel销毁时会执行，同时取消所有异步任务
        mCompositeDisposable.clear()
    }

    override fun accept(disposable: Disposable?) {
        disposable?.let { addSubscribe(it) }
    }

    object ParameterField {
        const val ROUTE_PATH = "ROUTE_PATH"
        const val BUNDLE = "BUNDLE"
        const val REQUEST_CODE = "REQUEST_CODE"
    }

}