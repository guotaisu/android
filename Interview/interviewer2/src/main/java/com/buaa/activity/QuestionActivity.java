package com.buaa.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.buaa.util.BaseActivity;
import com.buaa.util.QuestionDao;

import java.util.List;
import java.util.Map;

public class QuestionActivity extends BaseActivity {
    //显示List使用
    private ListView list;
    private List<Map<String,Object>> values;
    private SimpleAdapter adapter;

    //分页使用
    private int pageNumber = 1;
    private int pageSize = 15;
    private  int recordCount = 0;

    private int first;
    private int visible;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        super.init("全部问题", 1);

        list = (ListView) findViewById(R.id.list);
        //分页查询，从数据库中获取数据
        values = QuestionDao.loadDataPage(pageNumber,pageSize);
        recordCount = QuestionDao.getCount();
        adapter = new SimpleAdapter(this,values,R.layout.question_list,new String[]{"question"},new int[]{R.id.list_question});
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = values.get(position);
                LinearLayout linearLayout = (LinearLayout) view;
                // linearLayout.getChildAt(1).setVisibility(View.VISIBLE);

                if (linearLayout.getChildCount() == 1) {
                    //如果当前只有一行，加入答案
                    TextView answerView = new TextView(QuestionActivity.this);
                    answerView.setText(map.get("answer").toString());
                    answerView.setTextSize(14);
                    answerView.setTextColor(Color.RED);
                    linearLayout.addView(answerView);
                } else {
                    //否则删除答案
                    linearLayout.removeViewAt(1);
                }
            }
        });

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState==SCROLL_STATE_IDLE && first+visible==total && total !=0){
                    pageNumber++;
                    values.addAll(QuestionDao.loadDataPage(pageNumber,pageSize));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                first = firstVisibleItem;
                visible = visibleItemCount;
                total=  totalItemCount;
            }
        });
    }
}
