package com.zfkj.lib_base.event

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.NetworkUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import com.zfkj.lib_base.base.BaseViewModel
import com.zfkj.lib_base.event.callback.UIChangeLiveData

/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:base事件分发中心管理
 * 可通过Kotlin的命名可选参数特性 一个方法即可判断 发送/接收
 * sticky : post/observeSticky
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
object LiveBusCenter {


    fun postContainerActivityEvent(uC: UIChangeLiveData, path: String, bundle: Bundle? = null, isBack: Boolean = false) {
        uC.startContainerActivityEvent.postValue(HashMap<String, Any>().apply {
            this[BaseViewModel.ParameterField.ROUTE_PATH] = path
            if (bundle != null) this[BaseViewModel.ParameterField.BUNDLE] = bundle
        })
        if (isBack)uC.finishEvent.call()
    }

    fun postNetStateEvent() {
        LiveEventBus.get(NetStateEvent::class.java).post(NetStateEvent(NetworkUtils.isConnected()))
    }


    fun postOnActivityResultEvent(requestCode: Int, resultCode: Int, data: Intent?) {
        LiveEventBus.get(OnActivityResultEvent::class.java).post(OnActivityResultEvent(requestCode, resultCode, data))
    }

    fun observeOnActivityResultEven(
        owner: LifecycleOwner,
        func: (t: OnActivityResultEvent) -> Unit
    ) {
        LiveEventBus.get(OnActivityResultEvent::class.java).observe(owner, Observer(func))
    }

    fun postOnWifiInitSuccessEvent(isSuccess: Boolean) {
        LiveEventBus.get(OnWifiInitSuccessEvent::class.java).post(OnWifiInitSuccessEvent(isSuccess))
    }

    fun observeOnWifiInitSuccessEvent(
        owner: LifecycleOwner,
        func: (t: OnWifiInitSuccessEvent) -> Unit
    ) {
        LiveEventBus.get(OnWifiInitSuccessEvent::class.java).observe(owner, Observer(func))
    }


    fun postObserverBoxConnectEventEvent(isSuccess: Boolean) {
        LiveEventBus.get(ObserverBoxConnectEvent::class.java).post(ObserverBoxConnectEvent(isSuccess))
    }

    /**
     * 监听魔盒连接状态
     */
    fun observeBoxConnectEvent(
        owner: LifecycleOwner,
        func: (t: ObserverBoxConnectEvent) -> Unit
    ) {
        LiveEventBus.get(ObserverBoxConnectEvent::class.java).observe(owner, Observer(func))
    }


    fun postJumpBoxDetailsEvent(isJump: Boolean,isCloseSocket:Boolean=false) {
        LiveEventBus.get(JumpBoxDetailsEvent::class.java).post(JumpBoxDetailsEvent(isJump))
    }

    /**
     * 跳转魔盒详情
     */
    fun observeJumpBoxDetailsEvent(
        owner: LifecycleOwner,
        func: (t: JumpBoxDetailsEvent) -> Unit
    ) {
        LiveEventBus.get(JumpBoxDetailsEvent::class.java).observe(owner, Observer(func))
    }


    fun postReConnectBoxEvent(isReStart: Boolean) {
        LiveEventBus.get(ReConnectBoxEvent::class.java).post(ReConnectBoxEvent(isReStart))
    }



}