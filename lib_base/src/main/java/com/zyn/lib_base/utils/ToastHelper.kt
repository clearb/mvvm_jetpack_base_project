package com.zyn.lib_base.utils

import android.text.TextUtils
import com.blankj.utilcode.util.ThreadUtils.runOnUiThread
import com.blankj.utilcode.util.Utils
import es.dmoral.toasty.Toasty

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
object ToastHelper {
    fun showWarnToast(msg: String?) {
        if (!TextUtils.isEmpty(msg))
            runOnUiThread {
                Toasty.warning(Utils.getApp(), msg!!).show()
            }
    }

    fun showInfoToast(msg: String?) {
        if (!TextUtils.isEmpty(msg))
            runOnUiThread { TsenfToastUtils.showWarning(msg) }
    }

    fun showLongInfoToast(msg: String?) {
        if (!TextUtils.isEmpty(msg))
            runOnUiThread { TsenfToastUtils.showWarning(msg) }
    }

    fun showNormalToast(msg: String?) {
        if (!TextUtils.isEmpty(msg))
            runOnUiThread {
                TsenfToastUtils.showNormal(msg)
            }
    }

    fun showErrorToast(msg: String?) {
        if (!TextUtils.isEmpty(msg))
            runOnUiThread { TsenfToastUtils.showError(msg) }
    }

    fun showErrorLongToast(msg: String?) {
        if (!TextUtils.isEmpty(msg))
            runOnUiThread { TsenfToastUtils.showError(msg) }
    }

    fun showSuccessToast(msg: String?) {
        if (!TextUtils.isEmpty(msg))
            runOnUiThread {
                if (!TextUtils.isEmpty(msg))
                    TsenfToastUtils.showSuccess(msg)
            }
    }

    fun showSuccessLongToast(msg: String?) {
        if (!TextUtils.isEmpty(msg))
            runOnUiThread {
                if (!TextUtils.isEmpty(msg))
                    TsenfToastUtils.showSuccess(msg)
            }
    }

    fun showSuccessLongCenterToast(msg: String?) {
        if (!TextUtils.isEmpty(msg))
            runOnUiThread {
                if (!TextUtils.isEmpty(msg))
                    TsenfToastUtils.showSuccess(msg)
            }
    }

    fun showWarnToast(msg: Int) {
        if (!TextUtils.isEmpty(Utils.getApp().getString(msg)))
            runOnUiThread {
                TsenfToastUtils.showWarning(msg)
            }
    }

    fun showInfoToast(msg: Int) {
        if (!TextUtils.isEmpty(Utils.getApp().getString(msg)))
            runOnUiThread {
                TsenfToastUtils.showWarning(msg)
            }
    }

    fun showNormalToast(msg: Int) {
        if (!TextUtils.isEmpty(Utils.getApp().getString(msg)))
            runOnUiThread {
                TsenfToastUtils.showNormal(msg)
            }
    }

    fun showLongNormalToast(msg: Int) {
        if (!TextUtils.isEmpty(Utils.getApp().getString(msg)))
            runOnUiThread {
                TsenfToastUtils.showNormal(msg)
                // Toasty.normal(Utils.getApp(), msg, Toast.LENGTH_LONG).show()
            }
    }

    fun showErrorToast(msg: Int) {
        if (!TextUtils.isEmpty(Utils.getApp().getString(msg)))
            runOnUiThread {
                TsenfToastUtils.showError(msg)

            }
    }

    fun showSuccessToast(msg: Int) {
        if (!TextUtils.isEmpty(Utils.getApp().getString(msg)))
            runOnUiThread {
                TsenfToastUtils.showSuccess(msg)
            }
    }
}