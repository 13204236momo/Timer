package com.mo.zhou.commom.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;


public class Helper {
    public static Context mContext;
    private static Toast mToast;

    /**
     * 全局Toast
     *
     * @param str
     */
    public static void showToast(String str) {
        if (!isMainThread(Thread.currentThread().getName())) {
            Log.e("error", "不能在异步线程调用showToast");
            return;
        }
        if (mContext == null) return;
        if (TextUtils.isEmpty(str)) return;
        if (mToast == null) {
            mToast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);

        } else {
            mToast.setText(str);
        }
        mToast.show();
    }

    /**
     * 是否在主线程
     *
     * @param aThreadName
     * @return
     */
    public static boolean isMainThread(String aThreadName) {
        return aThreadName.equals("main");
    }

//    public static void showToast(Context context,String str) {
//        //自定义Toast控件
//        View toastView = LayoutInflater.from(context).inflate(R.layout.toast_clear_layout, null);
//        LinearLayout relativeLayout = (LinearLayout) toastView.findViewById(R.id.toast_linear);
//        //动态设置toast控件的宽高度，宽高分别是130dp
//        //这里用了一个将dp转换为px的工具类PxUtil
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dip2px(900), ViewGroup.LayoutParams.WRAP_CONTENT);
//        relativeLayout.setLayoutParams(layoutParams);
//        TextView textView = toastView.findViewById(R.id.tv_toast_clear);
//        textView.setText(str);
//        Toast toast = new Toast(context);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.BOTTOM, 0, 200);
//        toast.setView(toastView);
//        toast.show();
//    }


    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight() {
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }

    /**
     * 获取底部navigationBar高度
     *
     * @return
     */
    public static int getNavigationBarHeight() {
        Resources resources = mContext.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }

    private static final String NAVIGATION = "navigationBarBackground";

    // 该方法需要在View完全被绘制出来之后调用，否则判断不了
    //在比如 onWindowFocusChanged（）方法中可以得到正确的结果
    public static boolean isNavigationBarExist(@NonNull Activity activity) {
        ViewGroup vp = (ViewGroup) activity.getWindow().getDecorView();
        if (vp != null) {
            for (int i = 0; i < vp.getChildCount(); i++) {
                vp.getChildAt(i).getContext().getPackageName();
                if (vp.getChildAt(i).getId() != View.NO_ID && NAVIGATION.equals(activity.getResources().getResourceEntryName(vp.getChildAt(i).getId()))) {
                    return true;
                }
            }
        }
        return false;
    }


    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int dip2px(float dipValue) {
        if (mContext == null) return 0;
        return dip2px(mContext, dipValue);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        if (mContext == null) return 0;
        return px2dip(mContext, pxValue);
    }


    /*对字符串进行ASCII码升序排列*/
    private static String sortByChar(String str) {
        char[] cArray = str.toCharArray();
        for (int i = 0; i < cArray.length - 1; i++) {
            for (int k = 0; k < cArray.length - 1 - i; k++) {
                if ((int) cArray[k] > (int) cArray[k + 1]) {
                    char temp = cArray[k];
                    cArray[k] = cArray[k + 1];
                    cArray[k + 1] = temp;
                }
            }
        }
        return String.valueOf(cArray);
    }


    /*
      去空格换行
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            dest = str.replace("", "");
        }
        return dest.trim();
    }


}
