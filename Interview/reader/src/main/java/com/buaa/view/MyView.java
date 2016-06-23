package com.buaa.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.buaa.activity.MainActivity;
import com.buaa.util.Globals;

/**
 * Created by Administrator on 2016/5/20.
 */
public class MyView extends View {
    /*private String content = "小时候为了考个好中学把童年丢了；上中学为了考个好大学把少年丢了；上完大学为了找个好工作把爱好和追求丢了；" +
            "好不容易上班了为了多赚钱把生活丢了；可谁曾想一不留神把工作丢了；现在是一无所有才明白自己把自己丢了。";*/
    private String content;
    private MainActivity a;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        a = (MainActivity)context;
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void chageData(){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(Globals.CHAR_SIZE);
       if(content != null){
           for(int i=0;i< Globals.LINE_COUNT;i++){
               for(int j=0;j<Globals.LINE_CHAR_COUNT;j++){
                   if(j + i * Globals.LINE_CHAR_COUNT < content.length()){
                       canvas.drawText(String.valueOf(content.charAt(j + i * Globals.LINE_CHAR_COUNT)),
                               j * (Globals.CHAR_SIZE + Globals.CHAR_SEP) + Globals.PAGE_SEP,
                               (i+1) * (Globals.CHAR_SIZE + Globals.LINE_SEP),paint);
                   }
               }
           }
       }
    }
}
