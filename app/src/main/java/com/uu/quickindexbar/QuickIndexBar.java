package com.uu.quickindexbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by penghao on 2016/11/12.
 *
 *    快速搜索  条的自定义 控件
 */

public class QuickIndexBar extends View {
    //搜索条的宽高
    private int width;
    private int cellHeight;
    private Paint paint;
    private String[] indexArr = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    private int mCurrentPos = -1;   //当前点击的位置
    private OnTouchListener listener;

    public QuickIndexBar(Context context) {
        super(context);
        init();
    }


    public QuickIndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * 在此方法中  获取  测量的宽高
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        cellHeight = getMeasuredHeight() / indexArr.length;
    }

    /**
     * 初始化 画笔  工具
     */
    private void init() {
        //设置抗锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(26);
        paint.setTextAlign(Paint.Align.CENTER);  //设置文本的绘制起点是文字边框底边的中心
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < indexArr.length; i++) {
            float x = width / 2;
            float y = cellHeight / 2 + getTextHeight(indexArr[i]) / 2 + i * cellHeight;
            paint.setColor(mCurrentPos == i ? Color.RED : Color.BLACK);
            canvas.drawText(indexArr[i], x, y, paint);
        }
    }

    /**
     * 触摸事件的  处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int index = (int) (y / cellHeight);

                //只有在当前的位置不一样的  时候才重新绘制
                if (mCurrentPos != index) {
                    mCurrentPos = index;

                    if(index>=0 && index<indexArr.length){
                        if(listener!=null){  //设置回调
                            listener.onPositionChange(indexArr[index]);
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                mCurrentPos = -1;

                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    /**
     * 获取文本的高度
     * @param text
     * @return
     */
    private int getTextHeight(String text) {
        //获取文本的高度
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.height();
    }

    /**
     * 设置触摸监听
     *
     * @param listener
     */
    public void setOnTouchListener(OnTouchListener listener) {
        this.listener = listener;
    }

    /**
     * 对当前控件 触摸事件的监听
     */
    public interface OnTouchListener {
        void onPositionChange(String text);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}
