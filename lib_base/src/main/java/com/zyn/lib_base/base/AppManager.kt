package com.zyn.lib_base.base

import android.app.Activity
import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils.getTopActivity
import com.blankj.utilcode.util.Utils
import java.util.*

/**
 * packageName  : com.zyn.lib_base.base
 * classDescribe: 整个APP 的activity 管理和 app 管理类
 * author       : zanyanneng
 * email        : 2529240409@qq.com
 * createTime   : 2023/7/5 16:32
 * projectName  : MyBaseProject
 *
 **/
class AppManager private constructor() {

    /**
     * 最后可见Activity
     */
    var mTopActivity: Activity? = null

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack?.add(activity)
    }

    /**
     * 移除指定的Activity
     */
    fun removeActivity(activity: Activity?) {
        if (activity != null) {
            activityStack?.remove(activity)
        }
    }

    /**
     * 是否有activity
     */
    val isActivity: Boolean
        get() = if (activityStack != null) {
            !activityStack!!.isEmpty()
        } else false

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        if (activityStack == null || activityStack?.size == 0) {
            return null
        }
        return activityStack?.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        activityStack?.lastElement()?.apply {
            finishActivity(this)
        }

    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        activity?.apply {
            if (!isFinishing) {
                finish()
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        activityStack?.apply {
            for (activity in this) {
                if (activity.javaClass == cls) {
                    finishActivity(activity)
                    break
                }
            }
        }

    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        activityStack?.apply {
            for (activity in this) {
                finishActivity(activity)
            }
        }
    }


    /**
     * 关闭所有 [Activity],排除指定的 [Activity]
     *
     * @param excludeActivityClasses activity class
     */
    fun killAll(vararg excludeActivityClasses: Class<*>?) {
        val excludeList = listOf(*excludeActivityClasses)
        synchronized(AppManager::class.java) {
            activityStack?.apply {
                val iterator: MutableIterator<Activity> = iterator()
                while (iterator.hasNext()) {
                    val next = iterator.next()
                    if (excludeList.contains(next.javaClass)) continue
                    iterator.remove()
                    next.finish()
                }
            }
        }
    }

    /**
     * 获取指定的Activity
     *
     * @author
     */
    fun getActivity(cls: Class<*>): Activity? {
        activityStack?.apply {
            for (activity in this) {
                if (activity.javaClass == cls) {
                    return activity
                }
            }
        }

        return null
    }


    /**
     * 让在栈顶的 [Activity] ,打开指定的 [Activity]
     *
     * @param intent
     */
    fun startActivity(intent: Intent) {
        getTopActivity()?.apply {
            startActivity(intent)
            return
        }
        //如果没有前台的activity就使用new_task模式启动activity
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Utils.getApp().startActivity(intent)

    }


    /**
     * 退出应用程序
     */
    fun appExit() {
        try {
            finishAllActivity()
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid())
//            调用 System.exit(n) 实际上等效于调用：
//            Runtime.getRuntime().exit(n)
//            finish()是Activity的类方法，仅仅针对Activity，当调用finish()时，只是将活动推向后台，并没有立即释放内存，活动的资源并没有被清理；当调用System.exit(0)时，退出当前Activity并释放资源（内存），但是该方法不可以结束整个App如有多个Activty或者有其他组件service等不会结束。
//            其实android的机制决定了用户无法完全退出应用，当你的application最长时间没有被用过的时候，android自身会决定将application关闭了。
            //System.exit(0);
        } catch (e: Exception) {
            activityStack!!.clear()
            e.printStackTrace()
        }
    }


    companion object {
        var activityStack: Stack<Activity>? = null
            private set

        /**
         * 单例模式
         *
         * @return AppManager
         */
        val instance: AppManager by lazy { AppManager() }
    }
}