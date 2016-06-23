package com.buaa.activity;

import android.os.Bundle;
import android.widget.Button;

import com.buaa.util.BaseActivity;

public class MainActivity extends BaseActivity {
private Button buttonBtn02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.init("程序员面试宝典",0);

/*        buttonBtn02 = (Button) findViewById(R.id.bottom_btn_02);
        buttonBtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
                startActivity(intent);
            }
        });*/
    }
}
