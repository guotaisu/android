package com.buaa.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.buaa.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private List<View> allViews;
    private MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        allViews = new ArrayList<>();

        View v1 = LayoutInflater.from(this).inflate(R.layout.page_01,null);
        View v2 = LayoutInflater.from(this).inflate(R.layout.page_02,null);
        View v3 = LayoutInflater.from(this).inflate(R.layout.page_03,null);
        allViews.add(v1);
        allViews.add(v2);
        allViews.add(v3);
        pager.setOffscreenPageLimit(3);

        pagerAdapter = new MyPagerAdapter(allViews);
        pager.setAdapter(pagerAdapter);

    }
}
