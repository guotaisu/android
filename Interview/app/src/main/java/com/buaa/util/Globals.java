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
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static void init(Activity a) {
        db = new DButil(a);
        SCREEN_WIDTH = a.getWindowManager().getDefaultDisplay().getWidth();
        SCREEN_HEIGHT = a.getWindowManager().getDefaultDisplay().getHeight();
    }
}
