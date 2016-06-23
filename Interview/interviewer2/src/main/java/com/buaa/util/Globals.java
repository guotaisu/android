package com.buaa.util;

import android.app.Activity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/16.
 */
public class Globals {
    public static DButil db;
    public static List<Map<String, Object>> values;

    public static void init(Activity a){
        db = new DButil(a);
    }

}
