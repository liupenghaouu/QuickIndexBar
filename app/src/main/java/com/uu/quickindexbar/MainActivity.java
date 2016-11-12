package com.uu.quickindexbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {

    private QuickIndexBar qid;
    private ListView lvMain;
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private TextView tvCurWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillList();
        initView();
    }

    /**
     * 初始化  控件
     */
    private void initView() {
        qid = (QuickIndexBar) findViewById(R.id.qib);
        lvMain = (ListView) findViewById(R.id.lv_main);
        lvMain.setAdapter(new MyAdapter(this, friends));

        tvCurWord = (TextView) findViewById(R.id.tv_currentWord);
        tvCurWord.setVisibility(View.GONE);  //隐藏中心提示文字


        qid.setOnTouchListener(new QuickIndexBar.OnTouchListener() {
            @Override
            public void onPositionChange(String text) {
                //定位到当前 listView  的  所对应的条目
                for (int i = 0; i < friends.size(); i++) {
                    Friend f = friends.get(i);
                    String letter = f.getPinyin().charAt(0) + "";

                    if (letter.equals(text)) {
                        lvMain.setSelection(i);
                        break;
                    }

                }
                //显示
                showCurrentWord(text);
            }
        });


    }

    private boolean isScale = false;   //是否正在缩放
    private Handler handler = new Handler();

    /**
     * 展示当前触摸的字
     *
     * @param text
     */
    private void showCurrentWord(String text) {
        tvCurWord.setVisibility(View.VISIBLE);
        tvCurWord.setText(text);
        if (!isScale) {     //如果缩小了-----------> 放大
            isScale = true;
            ViewPropertyAnimator.animate(tvCurWord).scaleX(1f).setDuration(450).start();
            ViewPropertyAnimator.animate(tvCurWord).scaleY(1f).setDuration(450).start();

        }
        handler.removeCallbacksAndMessages(null);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewPropertyAnimator.animate(tvCurWord).scaleX(0f).setDuration(450).start();
                ViewPropertyAnimator.animate(tvCurWord).scaleY(0f).setDuration(450).start();
                isScale = false;
            }
        }, 1500);
    }

    /**
     * 添加数据
     */
    private void fillList() {
        // 虚拟数据
        friends.add(new Friend("李伟"));
        friends.add(new Friend("张三"));
        friends.add(new Friend("阿三"));
        friends.add(new Friend("阿四"));
        friends.add(new Friend("段誉"));
        friends.add(new Friend("段正淳"));
        friends.add(new Friend("张三丰"));
        friends.add(new Friend("陈坤"));
        friends.add(new Friend("林俊杰1"));
        friends.add(new Friend("陈坤2"));
        friends.add(new Friend("王二a"));
        friends.add(new Friend("林俊杰a"));
        friends.add(new Friend("张四"));
        friends.add(new Friend("林俊杰"));
        friends.add(new Friend("王二"));
        friends.add(new Friend("王二b"));
        friends.add(new Friend("赵四"));
        friends.add(new Friend("杨坤"));
        friends.add(new Friend("赵子龙"));
        friends.add(new Friend("杨坤1"));
        friends.add(new Friend("李伟1"));
        friends.add(new Friend("宋江"));
        friends.add(new Friend("宋江1"));
        friends.add(new Friend("李伟3"));
        Collections.sort(friends);   // 对  friend  进行排序

    }


}
