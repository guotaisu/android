package com.buaa.util;

import android.database.Cursor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/29.
 */
public class TxtDao {
    public static void insertTxtData(String fullPath){
        //在插入数据前先判断数据库中是否存在记录，如果不存在才插入数据
        String sql = "select id from txt where full_path = ?";
        Cursor cs  = Globals.util.getWritableDatabase().rawQuery(sql,new String[]{fullPath});
        if(!cs.moveToFirst()){
            sql = "insert into txt(full_path,now_page,over_flag) values(?,1,0)";
            Globals.util.getWritableDatabase().execSQL(sql,new Object[]{fullPath});
        }
        cs.close();
    }
    public static Map<String,Object> findTxtByFullPath(String fullPath){
        Map<String,Object> map = new HashMap<>();
        String sql = "select id, now_page, over_flag from txt where full_path = ?";
        Cursor cs  = Globals.util.getWritableDatabase().rawQuery(sql,new String[]{fullPath});
        cs.moveToFirst();
        map.put("txtId", cs.getInt(0));
        map.put("nowPage",cs.getInt(1));
        map.put("overFlag",cs.getInt(2));
        cs.close();
        return map;
    }
    public static void updateTxtOverFlag(String fullPath){
        String sql = "update txt set over_flag where fullPath = ?";
        Globals.util.getWritableDatabase().execSQL(sql,new Object[]{fullPath});
    }
}
