package com.zyn.lib_base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * packageName  : com.zyn.lib_base.utils
 * classDescribe:
 * author       : zanyanneng
 * email        : 2529240409@qq.com
 * createTime   : 2023/7/7 10:43
 * projectName  : MyBaseProject
 **/
public class TsenfToastUtils {
    private TsenfToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static Context context;
    // 文字显示的颜色 <color name="white">#FFFFFFFF</color>

    /**
     * 在Application中初始化ToastUtils.init(this)
     *
     * @param context
     */
    public static void init(Context context) {
        TsenfToastUtils.context = context.getApplicationContext();
    }

    /**
     * 发送Toast,默认LENGTH_SHORT
     *
     * @param resId
     */
    public static void showNormal(int resId) {
        showToast(context, context.getString(resId), Toast.LENGTH_SHORT);
    }

    public static void showNormal(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 成功的toast
     *
     * @param
     */
    public static void showSuccess(int resId) {
        showSuccess(context, context.getString(resId), Toast.LENGTH_SHORT);
    }

    public static void showSuccess(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        showSuccess(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 失败的toast
     *
     * @param
     */
    public static void showError(int resId) {
        showError(context, context.getString(resId), Toast.LENGTH_SHORT);
    }

    public static void showError(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        showError(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 失败的toast
     *
     * @param
     */
    public static void showWarning(int resId) {
        showWarning(context, context.getString(resId), Toast.LENGTH_SHORT);
    }

    public static void showWarning(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        showWarning(context, message, Toast.LENGTH_SHORT);
    }

    private static void showToast(Context context, String massage, int duration) {
        if (TextUtils.isEmpty(massage)) {
            return;
        }
        TsenfToast.normal(context, massage, duration).show();
    }

    private static void showSuccess(Context context, String massage, int duration) {
        if (TextUtils.isEmpty(massage)) {
            return;
        }
        TsenfToast.success(context, massage, duration).show();
    }

    private static void showWarning(Context context, String massage, int duration) {
        if (TextUtils.isEmpty(massage)) {
            return;
        }
        TsenfToast.warning(context, massage, duration).show();
    }

    private static void showError(Context context, String massage, int duration) {
        if (TextUtils.isEmpty(massage)) {
            return;
        }
        TsenfToast.error(context, massage, duration).show();
    }

}
