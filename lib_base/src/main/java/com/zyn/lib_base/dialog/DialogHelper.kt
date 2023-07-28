package com.zyn.lib_base.dialog

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.Utils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.impl.ConfirmPopupView
import com.lxj.xpopup.impl.LoadingPopupView
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.zfkj.lib_base.R
import com.zyn.lib_base.R


/**
 ************************************************
 * 包路径:com.zfkj.lib_base.dialog
 * 类描述:基础弹窗辅助
 * 创建者 @author:YangJiangLin[PHONE：181****4380]
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/3/13 10:34
 * 项目名称:BaseTrenf20220220
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */
object DialogHelper {
    var mConfirmPopupView: ConfirmPopupView? = null

    //private val dialogPool = mutableMapOf<Int, BasePopupView>()
    private val dialogList = DialogList()


    fun showBaseDialog(
        context: Context, title: String, content: String, func: (() -> Unit)? = null
    ) {
        mConfirmPopupView?.apply {
            if (isShow) {
                return
            }
        }
        mConfirmPopupView = XPopup.Builder(context).asConfirm(title, content, func?.let {
            mConfirmPopupView?.dismiss()
            OnConfirmListener(it)
        })
        mConfirmPopupView?.show()
    }

    /**
     * app版本升级
     */
    fun showVersionDialog(
        context: Context, title: String, content: String, func: () -> Unit
    ): BasePopupView {
        return XPopup.Builder(context).asConfirm(
            title,
            content,
            Utils.getApp().getString(R.string.app_hintCancel),
            Utils.getApp().getString(R.string.app_dialog_button_text_1),
            OnConfirmListener(func),
            null,
            false
        ).show()
    }

    /**
     * 显示LoadingDialog
     * @param dialogId Int : 对话框id
     * @param title String? : 标题
     * @param delayDismiss Long: 延迟消失对话框，-1：不延迟消失。
     * @return Int
     */
    fun showLoadingDialog(
        dialogId: Int = -1,
        title: String? = Utils.getApp().getString(R.string.app_dialog_title_1),
        delayDismiss: Long = -1
    ): Int {
        val dialogInfo = dialogList.getById(dialogId)
        if (dialogInfo != null) {
            val dialog = dialogInfo.dlg as LoadingPopupView
            dialog.setTitle(title)
            return dialogId
        }
        val context = ActivityUtils.getTopActivity()
        val loadPopupView: BasePopupView = XPopup.Builder(context).asLoading(title, R.layout.common_loading_dialog)
        val dialogId = loadPopupView.hashCode()
        val dialgInfo = DialogInfo(dialogId, loadPopupView)
        dialogList.add(dialgInfo)
        loadPopupView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                //LogUtils.d("showLoadingDialog add dialog:${dialogId},dialogList.size:${dialogList.size}")
            }

            override fun onViewDetachedFromWindow(v: View) {
                dialogList.remove(dialogId)
                //LogUtils.d("showLoadingDialog remove dialog:${dialogId},dialogList.size:${dialogList.size}")
            }
        })
        if (delayDismiss != -1L) loadPopupView.delayDismiss(delayDismiss)
        loadPopupView.show()
        return dialogId
    }

    /**
     * 销毁Dialog
     * @param pDialogId Int
     * @return Boolean
     */
    fun dismissDialog(pDialogId: Int): Boolean {
        val loadPopupView: BasePopupView? = dialogList.getById(pDialogId)?.dlg
        if (loadPopupView != null) {
            loadPopupView.dismiss()
            return true
        }
        return false
    }

    /**
     * 销毁正在显示的Dialog
     * @return Boolean
     */
    fun dismissDialog(): Boolean {
        val dlg = dialogList[dialogList.size - 1]
        if (dlg != null) {
            dlg.dlg.dismiss()
            return true
        }
        return false
    }

    /**
     * @param isDelayMillis  是否开启倒计时
     */
    fun showLoadingDialog(
        context: Context,
        title: String? = Utils.getApp().getString(R.string.app_dialog_title_1),
        isDelayMillis: Boolean = false,
        mHandler: Handler? = null,
        delayMillis: Long = 0,
        func: (() -> Unit?)? = null
    ): BasePopupView {
        val loadPopupView: BasePopupView = XPopup.Builder(context).asLoading(title, R.layout.common_loading_dialog)
        if (loadPopupView.isShow) {
            return loadPopupView
        }
        loadPopupView.show()
        if (isDelayMillis) {
            mHandler?.postDelayed({
                if (loadPopupView.isShow) {
                    func?.let { func.invoke() }
                    loadPopupView.dismiss()
                }
            }, delayMillis)
        }
        return loadPopupView
    }

    /**
     *没有取消弹窗
     */
    fun showNoCancelDialog(
        context: Context, title: String, content: String, cancelBtnText: String, confirmBtnText: String, func: () -> Unit
    ): BasePopupView {
        return XPopup.Builder(context).asConfirm(title, content, cancelBtnText, confirmBtnText, OnConfirmListener(func), null, true).show()
    }

    /**
     * 相同类型提示弹窗
     * @param title  标题
     * @param message 提示内容
     */
    fun showAlertDialog(context: Context, title: String, message: String, onGotIt: () -> Unit = {}) {
        val mDialog: Dialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.App_hintGotIt) { _, _ ->
                onGotIt.invoke()
            }
            .create()
        mDialog.window!!.setGravity(17)
        mDialog.setCancelable(false)
        mDialog.show()
    }

    /**
     * @param content 弹窗文本内容
     * @param btnString 弹窗按键显示文本
     * @param onGotIt 点击事件
     * */
    fun showNoticeAlertDialog(context: Context, title: String, content: String, btnString: String, onGotIt: () -> Unit = {}) {
        val mDialog: Dialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(content)
            .setPositiveButton(btnString) { _, _ ->
                onGotIt.invoke()
            }
            .create()
        mDialog.window!!.setGravity(17)
        mDialog.setCancelable(false)
        mDialog.show()
    }

    /**
     * 底部列表弹窗
     */
    fun showBottomDialog(
        context: Context,
        title: String,
        entry: Array<String>?,
        bindLayoutId: Int,
        bindItemLayoutId: Int,
        isShowCancel: Boolean,
        iconIds: IntArray? = null,
        func: (Int, String) -> Unit
    ): BasePopupView {
        val basePopupView = XPopup.Builder(context).isDestroyOnDismiss(true)
            //对于只使用一次的弹窗，推荐设置这个
            .asBottomList(
                title, entry, iconIds, -1, true, { position, text -> func(position, text) }, bindLayoutId, bindItemLayoutId
            )
        if (isShowCancel) {
            basePopupView.findViewById<AppCompatTextView>(R.id.selectCancel).setOnClickListener {
                basePopupView.dismiss()
            }
        }
        return basePopupView.show()
    }
}

data class DialogInfo(val id: Int, val dlg: BasePopupView)
private class DialogList {
    private val list = mutableListOf<DialogInfo>()

    fun add(info: DialogInfo) {
        list.add(info)
    }

    fun remove(id: Int) {
        list.removeIf { it.id == id }
    }

    operator fun get(index: Int): DialogInfo? {
        if (index < 0 || index >= list.size) return null
        return list[index]
    }

    fun getById(id: Int): DialogInfo? {
        return list.find { it.id == id }
    }

    val size get() = list.size
}