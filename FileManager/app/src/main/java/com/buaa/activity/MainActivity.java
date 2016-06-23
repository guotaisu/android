package com.buaa.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.buaa.adapter.MyAdapter;
import com.buaa.util.Globals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private TextView nowPath;
    private List<Map<String, Object>> values;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Globals.init(this);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        nowPath = (TextView) findViewById(R.id.nowPath);

        values = new ArrayList<>();
        File sd = Environment.getExternalStorageDirectory();
        loadFile(sd);
        adapter = new MyAdapter(this, values);
        list.setAdapter(adapter);


       // 添加list的onItemClick(…)事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = values.get(position);
                boolean isDir = (boolean) map.get("isDir");
                File file = new File(map.get("fullPath").toString());

                if (isDir) {
                    values.clear();
                    if (!Environment.getExternalStorageDirectory().getAbsolutePath().equals(file.getAbsolutePath())) {
                        Map<String, Object> parentData = new HashMap<>();
                        parentData.put("extName", "open_dir");
                        parentData.put("fileName", "返回一级");
                        parentData.put("isDir", true);//表示是否可以点击
                        parentData.put("fullPath", file.getParent());
                        parentData.put("real", "TRUE");//用于后面判断此文件夹是真的有“返回上一级”而不是一个名字叫“返回上一级”的文件夹

                        values.add(parentData);
                    }
                    loadFile(file);
                    adapter.notifyDataSetChanged();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("文件信息");
                    builder.setMessage("文件大小：" + file.length() + "\r\n" + "文件名：" + file.getName());
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();
                }
            }
        });
        //长按事件
       list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
               final Map<String, Object> map = values.get(position);
               boolean isDir = (boolean) map.get("isDir");
               AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
               builder.setTitle("提示信息");
               builder.setMessage("确定删除吗");
               builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       File file = new File(map.get("fullPath").toString());
                       file.delete();
                       values.remove(position);//上面只是删除显示数据，需删除集合里的数据
                       adapter.notifyDataSetChanged();
                   }
               });
               builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
               builder.create().show();

               return false;
           }
       });
    }
    //设置手机实体键触发事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Map<String,Object> map = values.get(0);
            if("TRUE".equals(map.get("real"))){
                list.performItemClick(list.getChildAt(0),0,list.getChildAt(0).getId());//返回上一级
            }else {
                //提示是否退出应用程序
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示信息");
                builder.setMessage("您确定要退出吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
                return false;
        }

        return super.onKeyDown(keyCode, event);
    }
    //遍历选中的文件夹并展示在页面上
    private void loadFile(File path) {
        File[] fs = path.listFiles();
        nowPath.setText(path.getAbsolutePath());
        for (int i = 0; i < fs.length; i++) {
            File f = fs[i];
            String fileName = f.getName();
            Map<String, Object> map = new HashMap<>();
            map.put("fileName", fileName);
            map.put("fullPath", f.getAbsolutePath());
            if (f.isDirectory()) {
                map.put("extName", "close_dir");
                map.put("isDir", true);
            } else {
                map.put("extName", fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase());
                map.put("isDir", false);
            }
            values.add(map);
        }
    }
}

