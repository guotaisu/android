package com.buaa.util;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/2.
 */
public class BaseActivity extends AppCompatActivity{

    private static List<Activity> allActivitys = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        allActivitys.add(this);
        super.onCreate(savedInstanceState);
    }
/*    @Override
    protected void onDestroy() {
        allActivitys.remove(this);
        super.onDestroy();
    }*/
}
