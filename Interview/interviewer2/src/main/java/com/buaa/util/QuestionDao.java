package com.buaa.util;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/3.
 */
public class QuestionDao {
    //插入数据
    public static  void insertData(Map<String,Object> map){
        String sql = "insert into ques_ans(question,answer) values(?,?)";
        Globals.db.getWritableDatabase().execSQL(sql, new Object[]{map.get("question"), map.get("answer")});
    }
    //分页读取数据
    public static List<Map<String,Object>> loadDataPage(int pageNumber,int pageSize){
        List<Map<String,Object>> values = new ArrayList<>();
        String sql = "select id,question,answer from ques_ans limit ?, ?";
        Cursor cs = Globals.db.getWritableDatabase().rawQuery(sql, new String[]{pageNumber*pageSize-pageSize + " ", pageSize + " "});
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            Map<String ,Object> map = new HashMap<>();
            map.put("question",cs.getInt(0) +" " +cs.getString(1));
            map.put("answer",cs.getString(2));
            values.add(map);
            cs.moveToNext();
        }
        cs.close();
        return values;
    }

    //查询总记录数
    public static int getCount(){
        int recordCount = 0;
        String sql = "select count(*) from ques_ans";
        Globals.db.getWritableDatabase().rawQuery(sql,null);
        Cursor cs = Globals.db.getWritableDatabase().rawQuery(sql,null);
        recordCount = cs.getInt(0);
        cs.close();
        return recordCount;


    }
    //删除数据
    public static void deleteAllData(){
        String sql = "delete from ques_ans";
        Globals.db.getWritableDatabase().execSQL(sql);
    }
}
