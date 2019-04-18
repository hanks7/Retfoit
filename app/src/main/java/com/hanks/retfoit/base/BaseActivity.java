package com.hanks.retfoit.base;

import android.util.Log;
import android.support.v7.app.AppCompatActivity;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.hanks.retfoit.base.BaseActivity
 * @time 2019/4/18 16:53
 * @description 请填写描述
 */
public class BaseActivity extends AppCompatActivity {
    public BaseActivity mActivity;

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ac--", "(" + getClass().getSimpleName() + ".java:0)");

    }
}
