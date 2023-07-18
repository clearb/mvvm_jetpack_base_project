package com.zyn.lib_base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.zyn.lib_base.R;

/**
 * packageName  : com.zyn.lib_base.utils
 * classDescribe:
 * author       : zanyanneng
 * email        : 2529240409@qq.com
 * createTime   : 2023/7/7 10:44
 * projectName  : MyBaseProject
 **/
public class TsenfToast {
    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;
    private static int textSize = 16; // in SP

    private static boolean tintIcon = true;
    private static boolean allowQueue = true;

    private static Toast lastToast = null;

    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;

    @CheckResult
    public static Toast success(@NonNull Context context, @StringRes int message) {
        return success(context, context.getString(message), Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull CharSequence message) {
        return success(context, message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @StringRes int message, int duration) {
        return success(context, context.getString(message), duration, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return success(context, message, duration, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @StringRes int message, int duration, boolean withIcon) {
        return custom(context, context.getString(message), ToastyUtils.getDrawable(context, R.drawable.icon_svg_check_white), ToastyUtils.getColor(context, R.color.white), ToastyUtils.getColor(context, R.color.white), duration, withIcon, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.icon_svg_check_white), ToastyUtils.getColor(context, R.color.white), ToastyUtils.getColor(context, R.color.white), duration, withIcon, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @StringRes int message) {
        return error(context, context.getString(message), Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull CharSequence message) {
        return error(context, message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @StringRes int message, int duration) {
        return error(context, context.getString(message), duration, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return error(context, message, duration, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @StringRes int message, int duration, boolean withIcon) {
        return custom(context, context.getString(message), ToastyUtils.getDrawable(context, R.drawable.icon_svg_close_white), ToastyUtils.getColor(context, R.color.white), ToastyUtils.getColor(context, R.color.white), duration, withIcon, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.icon_svg_close_white), ToastyUtils.getColor(context, R.color.white), ToastyUtils.getColor(context, R.color.white), duration, withIcon, true);
    }


    @CheckResult
    public static Toast warning(@NonNull Context context, @StringRes int message) {
        return warning(context, context.getString(message), Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull CharSequence message) {
        return warning(context, message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @StringRes int message, int duration) {
        return warning(context, context.getString(message), duration, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return warning(context, message, duration, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @StringRes int message, int duration, boolean withIcon) {
        return custom(context, context.getString(message), ToastyUtils.getDrawable(context, R.drawable.ic_tips), ToastyUtils.getColor(context, R.color.white), ToastyUtils.getColor(context, R.color.white), duration, withIcon, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.ic_tips), ToastyUtils.getColor(context, R.color.white), ToastyUtils.getColor(context, R.color.white), duration, withIcon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence message) {
        return normal(context, message, Toast.LENGTH_SHORT, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @StringRes int message, Drawable icon) {
        return normal(context, context.getString(message), Toast.LENGTH_SHORT, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence message, Drawable icon) {
        return normal(context, message, Toast.LENGTH_SHORT, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @StringRes int message, int duration) {
        return normal(context, context.getString(message), duration, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence message, int duration) {
        return normal(context, message, duration, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @StringRes int message, int duration, Drawable icon) {
        return normal(context, context.getString(message), duration, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence message, int duration, Drawable icon) {
        return normal(context, message, duration, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @StringRes int message) {
        return normal(context, context.getString(message), Toast.LENGTH_SHORT, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @StringRes int message, int duration, Drawable icon, boolean withIcon) {
        return custom(context, context.getString(message), icon, ToastyUtils.getColor(context, R.color.transparent), ToastyUtils.getColor(context, R.color.white), duration, withIcon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence message, int duration, Drawable icon, boolean withIcon) {
        return custom(context, message, icon, ToastyUtils.getColor(context, R.color.transparent), ToastyUtils.getColor(context, R.color.white), duration, withIcon, true);
    }

    @CheckResult
    public static Toast loading(@NonNull Context context, @NonNull CharSequence message) {

        return customLoading(context, message, Toast.LENGTH_SHORT);
    }

    @CheckResult
    public static Toast loading(@NonNull Context context, @NonNull CharSequence message, int duration) {

        return customLoading(context, message, duration);
    }

    @SuppressLint("ShowToast")
    @CheckResult
    public static Toast customLoading(@NonNull Context context, @NonNull CharSequence message, int duration) {
        final Toast currentToast = Toast.makeText(context, "", duration);
        View toastLayout = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        final TextView toastTextView = toastLayout.findViewById(R.id.toast_text);
        ProgressBar progress = toastLayout.findViewById(R.id.progress_circular);
        toastTextView.setText(message);
        toastTextView.setTypeface(currentTypeface);
        toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        progress.setVisibility(View.VISIBLE);
        currentToast.setView(toastLayout);
        if (!allowQueue) {
            if (lastToast != null) {
                lastToast.cancel();
            }
            lastToast = currentToast;
        }
        currentToast.setGravity(Gravity.CENTER, 0, 0);
        return currentToast;
    }

    @SuppressLint("ShowToast")
    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull CharSequence message, Drawable icon, @ColorInt int tintColor, @ColorInt int textColor, int duration, boolean withIcon, boolean shouldTint) {
        final Toast currentToast = Toast.makeText(context, "", duration);
        View toastLayout = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        final ImageView toastIcon = toastLayout.findViewById(R.id.toast_icon);

        final TextView toastTextView = toastLayout.findViewById(R.id.toast_text);
        if (!withIcon) {
            toastIcon.setVisibility(View.GONE);
        }
        if (null != icon) {
            toastIcon.setImageDrawable(icon);
            toastIcon.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(message)) {
            toastTextView.setText(message);
        }
        toastTextView.setTextColor(textColor);
        toastTextView.setTypeface(currentTypeface);
        toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        currentToast.setView(toastLayout);

        if (!allowQueue) {
            if (lastToast != null) {
                lastToast.cancel();
            }
            lastToast = currentToast;
        }
        currentToast.setGravity(Gravity.CENTER, 0, 0);
        return currentToast;
    }

    public static class Config {
        private Typeface typeface = TsenfToast.currentTypeface;
        private int textSize = TsenfToast.textSize;

        private boolean tintIcon = TsenfToast.tintIcon;
        private boolean allowQueue = true;

        private Config() {
            // avoiding instantiation
        }

        @CheckResult
        public static Config getInstance() {
            return new Config();
        }

        public static void reset() {
            TsenfToast.currentTypeface = LOADED_TOAST_TYPEFACE;
            TsenfToast.textSize = 16;
            TsenfToast.tintIcon = true;
            TsenfToast.allowQueue = true;
        }

        @CheckResult
        public Config setToastTypeface(@NonNull Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        @CheckResult
        public Config setTextSize(int sizeInSp) {
            this.textSize = sizeInSp;
            return this;
        }

        @CheckResult
        public Config tintIcon(boolean tintIcon) {
            this.tintIcon = tintIcon;
            return this;
        }

        @CheckResult
        public Config allowQueue(boolean allowQueue) {
            this.allowQueue = allowQueue;
            return this;
        }

        public void apply() {
            TsenfToast.currentTypeface = typeface;
            TsenfToast.textSize = textSize;
            TsenfToast.tintIcon = tintIcon;
            TsenfToast.allowQueue = allowQueue;
        }
    }
}
