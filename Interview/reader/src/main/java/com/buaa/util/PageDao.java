package com.buaa.util;

import java.util.Map;

/**
 * Created by Administrator on 2016/6/1.
 */
public class PageDao {
    public static void  insertPageData(Map<String,Object> map){
        String sql = "insert into page(tet_id,page_num,content) values(?,?,?)";
        Globals.util.getWritableDatabase().execSQL(sql,new Object[]{map.get("txtid"),map.get("pagenum"),map.get("content")});
    }

}
