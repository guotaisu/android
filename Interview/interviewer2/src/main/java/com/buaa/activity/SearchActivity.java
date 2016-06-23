package com.buaa.activity;

import android.os.Bundle;

import com.buaa.util.BaseActivity;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        super.init("搜索问题",2);
    }
}
