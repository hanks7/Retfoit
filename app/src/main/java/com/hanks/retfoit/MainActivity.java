package com.hanks.retfoit;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.hanks.retfoit.bean.BaseBean;
import com.hanks.retfoit.bean.Test1Bean;
import com.hanks.retfoit.utils.UToast;
import com.hanks.retfoit.utils.Ugson;
import com.hanks.retfoit.utils.http.HttpAdapter;
import com.hanks.retfoit.utils.http.OnResponseListener;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static com.hanks.retfoit.utils.http.HttpAdapter.getApis;
import static com.hanks.retfoit.utils.http.HttpAdapter.initApis;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 同步请求
     *
     * @param view
     */
    public void btnClick1(View view) {
        new AsyncTask<Void, Void, BaseBean>() {

            @Override
            protected BaseBean doInBackground(Void... params) {
                Call<BaseBean> call = initApis().test2();
                try {
                    Response<BaseBean> d = call.execute();
                    return d.body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(BaseBean bean) {
                if (bean != null) {
                    UToast.showText("json:"+ Ugson.toJson(bean));
                }

            }
        }.execute();
    }

    /**
     * 通过注解静态修改baseUrl
     *
     * @param view
     */
    public void btnClick2(View view) {
        getApis().test1().enqueue(new OnResponseListener<Test1Bean>(null) {
            @Override
            public void onSuccess(Test1Bean bean) {
                UToast.showText("json:"+ Ugson.toJson(bean));
            }
        });
    }

    /**
     * 使用默认的baseUrl
     *
     * @param view
     */
    public void btnClick3(View view) {
        getApis().test2().enqueue(new OnResponseListener<BaseBean>(null) {
            @Override
            public void onSuccess(BaseBean bean) {
                UToast.showText("json:"+ Ugson.toJson(bean));
            }
        });
    }

    /**
     * 通过Header静态修改baseUrl
     *
     * @param view
     */
    public void btnClick4(View view) {
        getApis().test3("http://easyway.com.cn:8088").enqueue(new OnResponseListener<Test1Bean>(null) {
            @Override
            public void onSuccess(Test1Bean bean) {
                UToast.showText("json:"+ Ugson.toJson(bean));
            }
        });
    }

    /**
     * new Retrofit()发网络请求
     *
     * @param view
     */
    public void btnClick5(View view) {
        HttpAdapter.initApis().test3("http://easyway.com.cn:8088").enqueue(new OnResponseListener<Test1Bean>(null) {
            @Override
            public void onSuccess(Test1Bean bean) {
                UToast.showText("json:"+ Ugson.toJson(bean));
            }
        });
    }


}
