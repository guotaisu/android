package com.buaa.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.buaa.util.Globals;
import com.buaa.util.QuestionDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Globals.init(this);
        setContentView(R.layout.activity_index);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    //根据SharePreferences的值来判断是否是第一次访问应用，如果是第一次访问则读取文件信息加入到数据库，否则睡眠2000ms
                    SharedPreferences s =  getSharedPreferences("questionflag",MODE_PRIVATE);
                    boolean saveFlag = s.getBoolean("flag",false);
                    if(saveFlag = true){
                        sleep(2000);
                    }else {
                        QuestionDao.deleteAllData();
                        //读取assets文件夹下的question.txt
                        InputStream is = getAssets().open("question.txt");
                        loadInputStream(is);
                        SharedPreferences.Editor e = s.edit();
                        e.putBoolean("flag",true);
                        e.commit();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent in = new Intent(IndexActivity.this, MainActivity.class);
                startActivity(in);
                finish();
            }
        };
        t.start();
    }

    public void loadInputStream(InputStream is) throws IOException {
        List<Map<String, Object>> values = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
        String line = null;
        StringBuffer question = new StringBuffer();
        StringBuffer answer = new StringBuffer();
        boolean questionFlag = false;
        while((line=br.readLine())!=null) {
            if(line.equals("Start_Question_Flag")) {
                //问题开始
                questionFlag = true;
            }else if(line.equals("Start_Answer_Flag")){
                //答案开始
                questionFlag = false;
            }else if(line.equals("End_Flag")) {
                //问题和答案结束，整合数据加入到集合中去
                Map<String, Object> map = new HashMap<>();
                map.put("question", question.toString());
                map.put("answer", answer.toString());
                QuestionDao.insertData(map);

                question = new StringBuffer();
                answer = new StringBuffer();
            }else {
                //读取内容
                if(questionFlag) {
                    question.append(line);
                }else {
                    answer.append(line);
                }
            }
        }
    }
}
