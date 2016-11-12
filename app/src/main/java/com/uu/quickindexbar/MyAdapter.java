package com.uu.quickindexbar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.uu.quickindexbar.R.id.first_word;

/**
 * listView 的  Adapter
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Friend> list;

    public MyAdapter(Context context, ArrayList<Friend> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Friend getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview, null);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.first_word = (TextView) convertView.findViewById(first_word);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        //设置数据   先设置名字
        Friend friend = list.get(position);
        holder.name.setText(friend.getName());

        String currentWord = friend.getPinyin().charAt(0) + "";

        if (position > 0) {
            //获取上一个item的首字母
            String lastWord = list.get(position - 1).getPinyin().charAt(0) + "";
            //拿当前的首字母和上一个首字母比较
            if (currentWord.equals(lastWord)) {
                //说明首字母相同，需要隐藏当前item的first_word
                holder.first_word.setVisibility(View.GONE);
            } else {
                //不一样，需要显示当前的首字母
                //由于布局是复用的，所以在需要显示的时候，再次将first_word设置为可见
                holder.first_word.setVisibility(View.VISIBLE);
                holder.first_word.setText(currentWord);
            }
        } else {
            holder.first_word.setVisibility(View.VISIBLE);
            holder.first_word.setText(currentWord);
        }

        return convertView;
    }

    public static class ViewHolder {
        public TextView name, first_word;
    }
}
