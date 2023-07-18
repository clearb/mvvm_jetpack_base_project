package com.zyn.lib_base.binding.viewAdapter.image

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import com.zfkj.lib_base.extension.loadUrl

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
    @BindingAdapter(value = ["url", "placeholderRes", "errorRes"], requireAll = false)
    fun setImageUri(
        imageView: ImageView,
        url: String?,
        placeholderRes: Drawable,
        errorRes: Drawable? = null
    ) {
        if (!TextUtils.isEmpty(url)) {
            imageView.loadUrl(url, placeholderRes)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["src"], requireAll = false)
    fun setImageRes(imageView: ImageView, resId: ObservableInt?) {
        if (resId == null) {
            return
        }
        if (resId.get() != 0) {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(resId.get())
        } else {
            imageView.visibility = View.INVISIBLE
        }
    }

    /**
     * @param imageView ImageView
     * @param b Boolean?
     * @usage:
     * xml:
     * <ImageView
     *  app:checked="true" />
     */
    @JvmStatic
    @BindingAdapter(value = ["checked"], requireAll = false)
    fun setImageChecked(imageView: ImageView, b: Boolean?) {
        if (b == true) imageView.setImageState(intArrayOf(android.R.attr.state_checked), true)
        else {
            val intArray = imageView.drawableState.filter { it != android.R.attr.state_checked }.toIntArray()
            imageView.setImageState(intArray, false)
        }
    }
}