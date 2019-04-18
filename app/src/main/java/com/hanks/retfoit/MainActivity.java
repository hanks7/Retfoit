package com.hanks.retfoit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hanks.retfoit.bean.BaseBean;
import com.hanks.retfoit.utils.http.HttpAdapter;
import com.hanks.retfoit.utils.http.OnResponseListener;

public class MainActivity extends Activity {
    private String url1 = "http://172.16.0.81:6666/api/Scanner?type=1&code=623&msg=&verify=";
    private String url2 = "http://api.k780.com:88/?APP=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void test() {
        HttpAdapter.getApis().test1().enqueue(new OnResponseListener<BaseBean>(null){
            @Override
            public void onSuccess(BaseBean baseBean) {
                super.onSuccess(baseBean);
            }
        });
        HttpAdapter.getApis().test2().enqueue(new OnResponseListener<BaseBean>(null){
            @Override
            public void onSuccess(BaseBean baseBean) {
                super.onSuccess(baseBean);

            }
        });
    }

    public void btnClick(View view) {
        test();
    }
}
