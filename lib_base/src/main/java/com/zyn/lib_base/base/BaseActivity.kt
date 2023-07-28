package com.zyn.lib_base.base

import android.app.Dialog
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.TimeUtils
import com.gyf.immersionbar.ImmersionBar
import com.lxj.xpopup.core.BasePopupView
import com.zfkj.lib_base.event.LiveBusCenter
import com.zfkj.lib_base.route.RouteCenter
import com.zyn.lib_base.R
import com.zyn.lib_base.broadcast.ScreenStatusReceiver
import com.zyn.lib_base.data.DataRepository
import com.zyn.lib_base.databinding.ActivityBaseBinding
import com.zyn.lib_base.dialog.DialogHelper
import com.zyn.lib_base.mvvm.ui.ContainerFmActivity
import com.zyn.lib_base.utils.DayModeUtil
import com.zyn.lib_base.utils.ToastHelper
import me.yokeyword.fragmentation.SupportFragment
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import java.lang.reflect.ParameterizedType
import java.util.concurrent.ConcurrentHashMap

/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:一个拥有DataBinding框架的基Activity
 * 这里根据项目业务可以换成你自己熟悉的BaseActivity, 但是需要继承RxAppCompatActivity,方便LifecycleProvider管理生命周期
 * 创建者 @author:YangJiangLin[PHONE：181****4380]
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/2/17 11:30
 * 项目名称:TsenfBase
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<*>> :
    BaseRxActivity(), IBaseView {
    protected lateinit var binding: V
    lateinit var viewModel: VM
    private var viewModelId = 0
    private var rootBinding: ActivityBaseBinding? = null
    private var dialog: BasePopupView? = null
    private var rightTitleImgView: View? = null

    val dataRepository: DataRepository by inject()

    /**维护的未登录或登录过期后的登录弹窗组 避免并发请求多次弹出*/
    val loginPopMap: ConcurrentHashMap<Int, BasePopupView> by inject(named("login_map"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**禁止应用截图*/
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        //方法注入，便于ARouter接收参数
        ARouter.getInstance().inject(this)
        //注册广播
        registerScreenStatusReceiver()
        //页面接受的参数方法
        initParam()
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding()
        if (isImmersionBarEnabled())
            initStatusBar()
        //私有的ViewModel与View的契约事件回调逻辑
        registerUIChangeLiveDataCallBack()
        //页面数据初始化方法
        initView(savedInstanceState)
        initData()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        onBaseViewObservable()
   }

    open fun initStatusBar() {
        ImmersionBar.with(this).statusBarDarkFont(!DayModeUtil.isNightMode(this), 0.2f)
            .transparentStatusBar().init()
    }

    /**
     * 初始化View
     */
    override fun initView(savedInstanceState: Bundle?) {}

    /**
     * 绑定ViewModel
     */
    open protected fun bindViewModel() {}


    /**
     * 调用设置状态栏字体为亮色
     */
    open fun setStatusFont2Light() {
        ImmersionBar.with(this).statusBarDarkFont(false).init()
    }

    /**
     * 调用设置状态栏字体为暗色
     */
    open fun setStatusFont2Dark() {
        ImmersionBar.with(this).statusBarDarkFont(true).init()
    }

    open fun isImmersionBarEnabled(): Boolean {
        return true
    }

    private var screenStatusReceiver: ScreenStatusReceiver? = null

    /**
     * 注册息屏广播
     */
    private fun registerScreenStatusReceiver() {
        screenStatusReceiver = ScreenStatusReceiver()
        val screenStatusIF = IntentFilter()
        screenStatusIF.addAction(Intent.ACTION_SCREEN_ON)
        screenStatusIF.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(screenStatusReceiver, screenStatusIF)
    }


    override fun onDestroy() {
        binding.unbind()
        rootBinding?.unbind()
        unregisterReceiver(screenStatusReceiver)
        super.onDestroy()
    }

    /**
     * Fragment入场动画
     */
    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultVerticalAnimator()
    }

    /**
     * 注入绑定
     */
    private fun initViewDataBinding() {
        when {
            useBaseLayout() -> {
                setContentView(R.layout.activity_base)
                val mActivityRoot = findViewById<ViewGroup>(R.id.activity_root)
                rightTitleImgView = mActivityRoot.findViewById(R.id.toolbar_right_img)
                // 绑定根布局
                rootBinding = DataBindingUtil.bind(mActivityRoot)
                rootBinding?.setVariable(initVariableId(), initViewModel())
                rootBinding?.lifecycleOwner = this
                // 在根布局添加公共布局 目前只添加了标题栏
                if (addParentContentView() != 0) {
                    val parentContent = LayoutInflater.from(this).inflate(addParentContentView(), mActivityRoot,false)
                    mActivityRoot.addView(parentContent)
                }
                //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
                binding = DataBindingUtil.inflate(
                    layoutInflater,
                    initContentView(),
                    mActivityRoot,
                    true
                )
            }
            else -> {
                binding = DataBindingUtil.setContentView(this, initContentView())
            }
        }
        viewModelId = initVariableId()
        viewModel = initViewModel()
        //关联ViewModel
        binding.setVariable(viewModelId, viewModel)
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.lifecycleOwner = this
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel)
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this)
    }


    fun getTitleRightImgView(): View? {
        return rightTitleImgView
    }

    /**
     * 刷新布局
     */

    fun refreshLayout() {
        binding.setVariable(viewModelId, viewModel)
    }

    /**
     * 注册ViewModel与View的契约UI回调事件
     */
    private fun registerUIChangeLiveDataCallBack() {
        //加载对话框显示
        viewModel.uC.getShowLoadingEvent()
            .observe(this) { title: String? -> showLoading(title) }
        //加载对话框消失
        viewModel.uC.getDismissDialogEvent()
            .observe(this) { disMissLoading() }
        //跳入新页面
        viewModel.uC.getStartActivityEvent().observe(
            this
        ) { map ->
            val routePath: String = map[BaseViewModel.ParameterField.ROUTE_PATH] as String
            val bundle = map[BaseViewModel.ParameterField.BUNDLE] as Bundle?
            RouteCenter.navigate(routePath, bundle)
        }
        viewModel.uC.getStartFragmentEvent().observe(this) { map ->
            val routePath: String = map[BaseViewModel.ParameterField.ROUTE_PATH] as String
            val bundle: Bundle? = map[BaseViewModel.ParameterField.BUNDLE] as Bundle?
            start(RouteCenter.navigate(routePath, bundle) as SupportFragment)
        }
        //跳入ContainerActivity
        viewModel.uC.getStartContainerActivityEvent().observe(
            this
        ) { params: Map<String?, Any?> ->
            val canonicalName = params[BaseViewModel.ParameterField.ROUTE_PATH] as String?
            val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle?
            startContainerActivity(canonicalName, bundle)
        }
        //关闭界面
        viewModel.uC.getFinishEvent().observe(this) {
            finish()
        }
        //关闭上一层
        viewModel.uC.getOnBackPressedEvent().observe(
            this
        ) { onBackPressedSupport() }

    }

    fun showLoading(title: String?) {
        dialog = DialogHelper.showLoadingDialog(this, title)
    }

    fun disMissLoading() {
        dialog?.smartDismiss()
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
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    fun startActivity(clz: Class<*>?) {
        startActivity(Intent(this, clz))
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     * @param isFinished 是否关闭当前页面
     */
    fun startActivity(clz: Class<*>?, isFinished: Boolean) {
        startActivity(Intent(this, clz))
        if (isFinished) finish()
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 跳转容器页面
     * @param routePath Fragment路由地址
     * @param bundle    跳转所携带的信息
     */
    fun startContainerActivity(
        routePath: String?,
        bundle: Bundle? = null,
        isFinished: Boolean = false
    ) {
        val intent = Intent(this, ContainerFmActivity::class.java)
        intent.putExtra(ContainerFmActivity.FRAGMENT, routePath)
        if (bundle != null) {
            intent.putExtra(ContainerFmActivity.BUNDLE, bundle)
        }
        startActivity(intent)
        if (isFinished) finish()
    }

    /**
     * 相同类型提示弹窗
     * @param title  标题
     * @param message 提示内容
     */
    fun showAlertDialog(title: String, message: String, onGotIt: () -> Unit = {}) {
        val mDialog: Dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.App_hintGotIt) { _, _ -> onGotIt.invoke() }
            .create()
        mDialog.window!!.setGravity(17)
        mDialog.setCancelable(false)
        mDialog.show()
    }

    override fun initParam() {}

    /**
     * @return 是否需要标题栏
     */
    protected open fun useBaseLayout(): Boolean {
        return true
    }

    /**
     * 添加根内容布局id（目前在xml内加了标题栏）
     *
     * @return
     */
    protected open fun addParentContentView(): Int {
        return 0
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(): Int

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    /**
     * 根据继承的泛型动态初始化ViewModel
     * 子类无须重写该方法 只需指定泛型即可
     * @return 继承BaseViewModel的ViewModel
     */
    private fun initViewModel(): VM {
        val type = javaClass.genericSuperclass
        val modelClass = (type as ParameterizedType).actualTypeArguments[1] as Class<VM>

        return when {
            isInitViewModelFactory() -> {
                initViewModelFactory(modelClass)!!
            }
            else -> {
                ViewModelProvider(this, get<BaseViewModelFactory>()).get(modelClass)
            }
        }
    }

    abstract fun isInitViewModelFactory(): Boolean

    abstract fun initViewModelFactory(modelClass: Class<VM>): VM?

    override fun initData() {}

    /**app 是否登录*/
    var isLogin = false
    override fun initViewObservable() {

    }

    private fun onBaseViewObservable() {
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        LiveBusCenter.postOnActivityResultEvent(requestCode, resultCode, data)
    }
}