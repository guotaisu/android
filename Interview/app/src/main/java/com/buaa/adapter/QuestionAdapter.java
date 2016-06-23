package com.buaa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buaa.activity.
R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/11.
 */
public class QuestionAdapter extends BaseAdapter {
    private Context ctx;
    private List<Map<String,Object>> values  = new ArrayList<>();
    public QuestionAdapter(Context ctx,List<Map<String,Object>> values){
        this.ctx = ctx;
        this.values = values;
    }
    @Override
    public int getCount() {
        return values.size() ;
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.question_list,null);
        }else{
            LinearLayout linear = (LinearLayout) convertView;
            if(linear.getChildCount()==2){
                linear.removeViewAt(1);
            }
        }
        Map<String,Object> map = values.get(position);
        TextView question = (TextView) convertView.findViewById(R.id.list_question);
        question.setText(map.get("question").toString());

        boolean showFlag = (boolean) map.get("showFlag");
        if(showFlag){
            LinearLayout linear = (LinearLayout) convertView;
            TextView answerView = new TextView(ctx);
            answerView.setText(map.get("answer").toString());
            answerView.setTextSize(14);
            answerView.setTextColor(Color.RED);
            linear.addView(answerView);
        }
        return convertView;
    }
}
