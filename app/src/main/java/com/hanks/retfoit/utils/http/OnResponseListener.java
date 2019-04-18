package com.hanks.retfoit.utils.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;

import com.hanks.retfoit.bean.BaseBean;
import com.hanks.retfoit.utils.UToast;
import com.hanks.retfoit.utils.Ulog;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @author 侯建军 47466436@qq.com
 * @date 2018/6/4
 * @description 请填写描述
 */
public class OnResponseListener<T extends BaseBean> implements Callback<T> {
    /**
     * 网络请求等待的dialog
     */
    ProgressDialog dialog;
    Activity activity;

    /**
     * 传入为null就不会显示dialog
     *
     * @param activity
     */
    public OnResponseListener(Activity activity) {
        this.activity = activity;
        if (activity != null && !activity.isFinishing()) {
            dialog = new ProgressDialog(activity);
            dialog.show();
        }
    }

    /**
     * dialog隐藏
     */
    public void dismiss() {
        if (dialog == null || activity.isFinishing()) return;
        dialog.hide();
    }

    /**
     * dialog显示
     */
    public void show() {
        if (dialog == null || activity.isFinishing()) return;
        dialog.show();
    }


    @Override
    public void onResponse(Call<T> call, Response<T> bean) {
        dismiss();
        Ulog.i("http-Adapter-onResponse.code()", bean.code());
        if (bean.code() == 200) {
            onSuccess(bean.body());

        } else {
            String strError = "";
            try {
                strError += bean.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            strError += bean.code();
            onFailureDeal(bean.code(), strError);
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        dismiss();
        String tag = "error";
        String msg = t.getMessage();
        if (TextUtils.isEmpty(msg)) {
            tag = tag + ":" + msg;
        }
        onFailureDeal(444, tag);
    }

    /**
     * 请求成功时返回，必须重写此方法
     *
     * @param t
     */
    public void onSuccess(T t) {

    }

    /**
     * 请求失败时返回，需要时重写此方法(默认是弹出吐司)
     */
    private void onFailureDeal(int code, String strToast) {
        onFailure(code, strToast);
        Ulog.i("http-Adapter-onFailureDeal", code + strToast);
    }

    /**
     * 请求失败时返回，需要时重写此方法(默认是弹出吐司)
     */
    public void onFailure(int code, String strToast) {
        UToast.showText("code:" + code + "  " + strToast);
    }


}