package com.buaa.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.buaa.activity.MainActivity;
import com.buaa.activity.QuestionActivity;
import com.buaa.activity.R;
import com.buaa.activity.SearchActivity;

/**
 * Created by Administrator on 2016/5/2.
 */
public class BaseActivity extends AppCompatActivity{
    private TextView toptitle;
    private Button[] btns = new Button[3];
    private Class[] targetActivitys = new Class[]{MainActivity.class, QuestionActivity.class, SearchActivity.class};
    private int[] bottomImgs = new int[]{R.drawable.bottom01b,R.drawable.bottom02b,R.drawable.bottom03b};
    private int[] bottomImgsSelected = new int[]{R.drawable.bottom01a,R.drawable.bottom02a,R.drawable.bottom03a};
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void init(String title,int index){
        toptitle = (TextView)findViewById(R.id.top_title);
        toptitle.setText(title);

        btns[0] = (Button) findViewById(R.id.bottom_btn_01);
        btns[1] = (Button) findViewById(R.id.bottom_btn_02);
        btns[2] = (Button) findViewById(R.id.bottom_btn_03);

        for(i=0;i<btns.length;i++){
            final int temp =  i; //i是变量，处于循环中，下面使用targetActivitys[i]会出现数组越界的错误，所以在此每次都声明一个。
            if(index == i){
                btns[i].setBackgroundResource(bottomImgsSelected[i]);
            }else{
                btns[i].setBackgroundResource(bottomImgs[i]);
                btns[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(BaseActivity.this,targetActivitys[temp]);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
