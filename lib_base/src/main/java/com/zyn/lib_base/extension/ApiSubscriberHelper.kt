package com.zyn.lib_base.extension

import android.net.ParseException
import com.blankj.utilcode.util.Utils
import com.tsenf.lib_okhttp.base.BaseBean
import com.zfkj.lib_base.util.ToastHelper.showErrorToast
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.zfkj.lib_base.R
import io.reactivex.observers.DisposableObserver
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:RxJava 处理Api异常
 * 不自动处理状态页的不传构造即可
 * 创建者 @author:zanyanneng
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/2/17
 * 项目名称:TsenfBase
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */
abstract class ApiSubscriberHelper<T> : DisposableObserver<T>() {

    override fun onNext(t: T) {
        if (t is BaseBean<*> && t.code != 0) {
            showErrorToast(t.msg)
        }
        onResult(t)
    }

    override fun onComplete() {}

    override fun onError(throwable: Throwable) {

        when (throwable) {
            is ConnectException, is ConnectTimeoutException, is UnknownHostException -> {
                onFailed(Utils.getApp().getString(R.string.app_http_connect_failed_exception))
            }
            is RuntimeException -> {
                onFailed(throwable.message)
            }
            is SocketTimeoutException -> {
                onFailed(Utils.getApp().getString(R.string.app_http_connect_timeout_exception))
            }
            is IllegalStateException -> {
                onFailed(throwable.message)
            }
            is HttpException -> {
                onFailed(Utils.getApp().getString(R.string.app_http_inter_exception))
            }
            is JsonParseException, is JSONException, is JsonSyntaxException, is ParseException -> {
                onFailed(Utils.getApp().getString(R.string.app_gson_data_exception))
            }
            else -> {
                onFailed(throwable.message)
            }
        }
    }

    protected abstract fun onResult(t: T)
    protected abstract fun onFailed(msg: String?)
}