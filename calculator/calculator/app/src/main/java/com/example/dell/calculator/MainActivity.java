package com.example.dell.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView result;
    String s1;
    double t;
    boolean start=false;
    int choose=0;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1="";
        t=0;
        start=true;
        result=(TextView)findViewById(R.id.textView);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b6=(Button)findViewById(R.id.button6);
        b7=(Button)findViewById(R.id.button7);
        b8=(Button)findViewById(R.id.button8);
        b9=(Button)findViewById(R.id.button9);
        b10=(Button)findViewById(R.id.button10);
        b11=(Button)findViewById(R.id.button11);
        b12=(Button)findViewById(R.id.button12);
        b13=(Button)findViewById(R.id.button13);
        b14=(Button)findViewById(R.id.button14);
        b15=(Button)findViewById(R.id.button15);
        b16=(Button)findViewById(R.id.button16);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==0){
                    init();
                    choose=-1;
                }
                s1=s1+"1";
                result.setText(s1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==0){
                    init();
                    choose=-1;
                }
                s1=s1+"2";
                result.setText(s1);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==0){
                    init();
                    choose=-1;
                }
                s1=s1+"3";
                result.setText(s1);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==0){
                    init();
                    choose=-1;
                }
                s1=s1+"4";
                result.setText(s1);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==0){
                    init();
                    choose=-1;
                }
                s1=s1+"5";
                result.setText(s1);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==0){
                    init();
                    choose=-1;
                }
                s1=s1+"6";
                result.setText(s1);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==0){
                    init();
                    choose=-1;
                }
                s1=s1+"7";
                result.setText(s1);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==0){
                    init();
                    choose=-1;
                }
                s1=s1+"8";
                result.setText(s1);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==0){
                    init();
                    choose=-1;
                }
                s1=s1+"9";
                result.setText(s1);
            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==0){
                    init();
                    choose=-1;
                }
                s1=s1+"0";
                result.setText(s1);
            }
        });
        //加号
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(start==true){
                    t=Double.parseDouble(s1);
                    start=false;
                }else{
                    if(choose==1){
                        t=t+Double.parseDouble(s1);
                    }
                    if(choose==2){
                        t=t-Double.parseDouble(s1);
                    }
                    if(choose==3){
                        t=t*Double.parseDouble(s1);
                    }
                    if(choose==4){
                        t=t/Double.parseDouble(s1);
                    }
                }
                s1="";
                result.setText(s1);
                choose=1;
            }
        });
        //等于号
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choose==1){
                    t=t+Double.parseDouble(s1);
                }else if(choose==2){
                     t=t-Double.parseDouble(s1);
                }else if(choose==3){
                    t=t*Double.parseDouble(s1);
                }
                else if(choose==4){
                    t=t/Double.parseDouble(s1);
                }
                else{
                    t=Double.parseDouble(s1);
                }
                result.setText(t+"");
                choose=0;
            }
        });
        //重置键
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
                result.setText("");
            }
        });
        //减号
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(start==true){
                    t=Double.parseDouble(s1);
                    start=false;
                }else{
                    if(choose==1){
                        t=t+Double.parseDouble(s1);
                    }
                    if(choose==2){
                        t=t-Double.parseDouble(s1);
                    }
                    if(choose==3){
                        t=t*Double.parseDouble(s1);
                    }
                    if(choose==4){
                        t=t/Double.parseDouble(s1);
                    }
                }
                s1="";
                result.setText(s1);
                choose=2;
            }
        });
        //乘号
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(start==true){
                    t=Double.parseDouble(s1);
                    start=false;
                }else{
                    if(choose==1){
                        t=t+Double.parseDouble(s1);
                    }
                    if(choose==2){
                        t=t-Double.parseDouble(s1);
                    }
                    if(choose==3){
                        t=t*Double.parseDouble(s1);
                    }
                    if(choose==4){
                            t = t / Double.parseDouble(s1);
                    }
                }
                s1="";
                result.setText(s1);
                choose=3;
            }
        });
        //除号
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(start==true){
                    t=Double.parseDouble(s1);
                    start=false;
                }else{
                    if(choose==1){
                        t=t+Double.parseDouble(s1);
                    }
                    if(choose==2){
                        t=t-Double.parseDouble(s1);
                    }
                    if(choose==3){
                        t=t*Double.parseDouble(s1);
                    }
                    if(choose==4){
                        t=t/Double.parseDouble(s1);
                    }
                }
                s1="";
                result.setText(s1);
                choose=4;
            }
        });
    }
    public void init(){
        s1="";
        t=0;
        start=true;
        choose=0;
    }
}
