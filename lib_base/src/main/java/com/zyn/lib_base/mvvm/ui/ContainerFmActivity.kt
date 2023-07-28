package com.zyn.lib_base.mvvm.ui


import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zyn.lib_base.BR
import com.zyn.lib_base.mvvm.viewmodel.CommonViewModel
import com.zfkj.lib_base.config.Constants
import com.zfkj.lib_base.route.RouteCenter
import com.zyn.lib_base.R
import com.zyn.lib_base.base.BaseActivity
import com.zyn.lib_base.databinding.CommonContainerBinding
import me.yokeyword.fragmentation.SupportFragment

/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:Fragment容器 根据路由地址加载根Fragment
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

@Route(path = Constants.Router.Base.A_CONTAINER)
class ContainerFmActivity : BaseActivity<CommonContainerBinding, CommonViewModel>() {
    companion object {
        const val FRAGMENT = "fragment"
        const val BUNDLE = "bundle"
    }

    override fun initContentView(): Int {
        return R.layout.common_container
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun isInitViewModelFactory(): Boolean {
      return false
    }

    override fun initViewModelFactory(modelClass: Class<CommonViewModel>): CommonViewModel? {
        return null
    }

    override fun useBaseLayout(): Boolean {
        return false
    }

    override fun initData() {
        val fragmentPath: String? = intent.getStringExtra(FRAGMENT)
        if (fragmentPath.isNullOrBlank()) {
            return
        }
        val args: Bundle? = intent.getBundleExtra(BUNDLE)
        val fragment: SupportFragment = RouteCenter.navigate(fragmentPath,args) as SupportFragment
        if (findFragment(fragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, fragment)
        }
    }
}