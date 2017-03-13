package com.bwei.bilibili.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.bwei.bilibili.R;
import com.bwei.bilibili.Utils.RequestDataUtils;
import com.bwei.bilibili.adapter.RecommendAdapter;
import com.bwei.bilibili.bean.Body;
import com.bwei.bilibili.bean.RecommendBean;
import com.bwei.bilibili.bean.Result;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途:推荐
 *
 * @author 李辉
 * @date 2017/3/9 18:44.
 */

public class Recommend extends Fragment implements SwipyRefreshLayout.OnRefreshListener {

    private View view;
    private SwipyRefreshLayout srl;
    private GridView gv;
    private RecommendAdapter adapter;
    private List<Body> body;
    List<Body> list = new ArrayList<Body>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recommend, null);
        //初始化控件
        initView();
        //请求数据
        initData();


        //刷新加载
        initRefreshOrDownLoad();
        return view;
    }

    private void initRefreshOrDownLoad() {
        srl.setOnRefreshListener(this);
        srl.setDirection(SwipyRefreshLayoutDirection.BOTH);
    }


    private void initData() {
        RequestDataUtils.requestData("http://app.bilibili.com/x/show/old?platform=android&device=&build=412001", new RequestDataUtils.CallBackData() {


            @Override
            public void callBackData(String s) {

                Gson gson = new Gson();
                RecommendBean recommendBean = gson.fromJson(s, RecommendBean.class);
                List<Result> result = recommendBean.getResult();
                for (int i = 0; i < result.size(); i++) {
                    body = result.get(i).getBody();
                    for (int j = 0; j < body.size(); j++) {
                        list.add(body.get(j));
                    }
                }

                if (adapter == null) {
                    adapter = new RecommendAdapter(getActivity());
                    adapter.addData(list);
                    gv.setAdapter(adapter);
                    srl.setRefreshing(false);
                } else {
                    adapter.notifyDataSetChanged();
                    srl.setRefreshing(false);
                }
            }

        });
    }

    private void initView() {
        srl = (SwipyRefreshLayout) view.findViewById(R.id.srl);
        gv = (GridView) view.findViewById(R.id.gv);
    }

    @Override
    public void onRefresh(int index) {
        list.clear();
        initData();
    }

    @Override
    public void onLoad(int index) {
        initData();
    }
}
