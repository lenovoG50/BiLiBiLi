package com.bwei.bilibili.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.bilibili.R;
import com.bwei.bilibili.bean.Body;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/3/11 11:20.
 */

public class RecommendAdapter extends BaseAdapter {

    private Context context;
    private List<Body> body;
    private ViewHolder vh;

    public RecommendAdapter(FragmentActivity activity) {
        context = activity;
    }

    @Override
    public int getCount() {
        return body.size();
    }

    @Override
    public Body getItem(int position) {
        return body.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public String toString() {
        return "RecommendAdapter{" +
                "context=" + context +
                ", body=" + body +
                ", vh=" + vh +
                '}';
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_recommend, null);
            vh = new ViewHolder();
            vh.title = (TextView) convertView.findViewById(R.id.title);
            vh.img = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        DisplayImageOptions options = DisplayImageOptions.createSimple();
        vh.title.setText(body.get(position).getTitle());

        ImageLoader.getInstance().displayImage(body.get(position).getCover(), vh.img, options);
        return convertView;
    }

    public void addData(List<Body> list) {
        body = list;
    }

    class ViewHolder {
        TextView  title;
        ImageView img;
    }
}
