package com.buaa.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.buaa.adapter.MyPagerAdapter;
import com.buaa.adapter.QuestionAdapter;
import com.buaa.util.BaseActivity;
import com.buaa.util.Globals;
import com.buaa.util.QuestionDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {
    private List<View> allViwes;
    private ViewPager pager;
    private MyPagerAdapter pagerAdapter;
    private TextView toptitle;
    private Button[] btns = new Button[3];
    //private Class[] targetActivitys = new Class[]{MainActivity.class, QuestionActivity.class, SearchActivity.class};
    private int[] bottomImgs = new int[]{R.drawable.bottom01b,R.drawable.bottom02b,R.drawable.bottom03b};
    private int[] bottomImgsSelected = new int[]{R.drawable.bottom01a,R.drawable.bottom02a,R.drawable.bottom03a};

    private String[] allTitles = new String[]{"程序员面试宝典","全部问题","搜索关键字"};
    private int i;

    private Button setting;
    private PopupWindow win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init("程序员面试宝典", 0);

        pager = (ViewPager) findViewById(R.id.pager);
        allViwes = new ArrayList<>();

        View mainView = LayoutInflater.from(this).inflate(R.layout.page_main,null);
        View questionView = LayoutInflater.from(this).inflate(R.layout.page_question,null);
        View searchView = LayoutInflater.from(this).inflate(R.layout.page_search,null);
        allViwes.add(mainView);
        allViwes.add(questionView);
        allViwes.add(searchView);

        initQuestionList(questionView);
        initSearch(searchView);

        pager.setOffscreenPageLimit(3);
        pagerAdapter = new MyPagerAdapter(allViwes);
        pager.setAdapter(pagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toptitle.setText(allTitles[position]);

                for (i = 0; i < btns.length; i++) {
                    if (position == i) {
                        btns[i].setBackgroundResource(bottomImgsSelected[i]);
                    } else {
                        btns[i].setBackgroundResource(bottomImgs[i]);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //显示List使用
    private ListView list;
    private List<Map<String,Object>> values;
    private QuestionAdapter adapter;

    //分页使用
    private int pageNumber = 1;
    private int pageSize = 15;
    private  int recordCount;
    private  int pageCount;

    private int first;
    private int visible;
    private int total;

    private  String keyword;

    public  void initQuestionList(View questionView){

        list = (ListView)questionView.findViewById(R.id.list);

        if(keyword == null){
            keyword = "";
        }

        //分页查询，从数据库中获取数据
        values = QuestionDao.loadDataPage(pageNumber, pageSize, keyword);
        recordCount = QuestionDao.getCount(keyword);
        pageCount = (recordCount-1)/pageSize + 1;

        //加入footerView(ListView的内容，专门用来做分页，它本身就是一个TextView)
        final TextView footerView = new TextView(MainActivity.this);
        footerView.setText("正在加载数据，请稍后...");
        footerView.setTextColor(Color.BLACK);
        footerView.setTextSize(12);
        list.addFooterView(footerView);

        adapter = new QuestionAdapter(this,values);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = values.get(position);
                LinearLayout linear = (LinearLayout) view;
                // linearLayout.getChildAt(1).setVisibility(View.VISIBLE);

                if (linear.getChildCount() == 1) {
                    //如果当前只有一行，加入答案
                    TextView answerView = new TextView(MainActivity.this);
                    answerView.setText(map.get("answer").toString());
                    answerView.setTextSize(14);
                    answerView.setTextColor(Color.RED);
                    linear.addView(answerView);
                    map.put("showFlag",true);
                } else {
                    //否则删除答案
                    linear.removeViewAt(1);
                    map.put("showflag",false);
                }
            }
        });

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState==SCROLL_STATE_IDLE && first+visible == total && total !=0){
                    if(pageNumber<pageCount){
                        pageNumber++;
                        values.addAll(QuestionDao.loadDataPage(pageNumber,pageSize,keyword));
                        adapter.notifyDataSetChanged();
                    }else{
                        if(list.getFooterViewsCount()>0){
                            list.removeFooterView(footerView);
                        }
                    }
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

    private EditText searchContent;
    private Button searchBtn;
    public void initSearch(View searchView){
        searchContent = (EditText)searchView.findViewById(R.id.search_content);
        searchBtn = (Button)searchView.findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword = searchContent.getText().toString();
                pageNumber = 1;
                values.addAll(QuestionDao.loadDataPage(pageNumber,pageSize,keyword));
                adapter.notifyDataSetChanged();
                pager.setCurrentItem(1,true);
            }
        });
    }
    public void init(String title,int index){
        toptitle = (TextView)findViewById(R.id.top_title);
        toptitle.setText(title);

        btns[0] = (Button) findViewById(R.id.bottom_btn_01);
        btns[1] = (Button) findViewById(R.id.bottom_btn_02);
        btns[2] = (Button) findViewById(R.id.bottom_btn_03);

        for(i=0;i<btns.length;i++){
            final int temp =  i; //i是变量，处于循环中，下面使用targetActivitys[i]会出现数组越界的错误，所以在此每次都声明一个。
            if(index == i){
                btns[i].setBackgroundResource(bottomImgsSelected[i]);
            }else{
                btns[i].setBackgroundResource(bottomImgs[i]);
            }
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pager.setCurrentItem(temp,true);
                }
            });
        }

        setting = (Button) findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                win = new PopupWindow(Globals.SCREEN_WIDTH / 4, Globals.SCREEN_HEIGHT / 6);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.win_list, null);
                win.setContentView(view);

                if (win.isShowing()) {
                    win.dismiss();
                } else {
                    //把浮动窗口置于按钮下
                    win.showAsDropDown(setting);
                }

                TextView version = (TextView) view.findViewById(R.id.win_version);
                TextView about = (TextView) view.findViewById(R.id.win_about);
                TextView exit = (TextView) view.findViewById(R.id.win_exit);

                version.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        win.dismiss();
                        Toast.makeText(MainActivity.this, "当前版本号：1.0.1", Toast.LENGTH_LONG).show();
                    }
                });
                about.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        win.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("提示信息");
                        builder.setMessage("飞虎团队版权所有");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        });
    }
}
