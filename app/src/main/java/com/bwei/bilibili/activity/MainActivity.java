package com.bwei.bilibili.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwei.bilibili.R;
import com.bwei.bilibili.Utils.InitTools;
import com.bwei.bilibili.Utils.TabLayoutTitle;
import com.bwei.bilibili.fragment.Find;
import com.bwei.bilibili.fragment.Live;
import com.bwei.bilibili.fragment.Recommend;
import com.bwei.bilibili.fragment.Serialize;
import com.bwei.bilibili.fragment.Sort;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout sliding;
    private ImageView game;
    private ImageView downLoad;
    private ImageView seach;
    private ImageView login;
    private TextView nick;
    private ArrayList<Fragment> list = new ArrayList<Fragment>();
    private ViewPager vp;
    private TabLayout title;
    private SlidingMenu menu;
    private ImageView dayOrNight;
    private RelativeLayout rlv;
    private FrameLayout frl;
    private RelativeLayout header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化布局
        initView();
        //添加碎片
        initFragment();
        //实现联动
        initVpAdapter();
        title.setupWithViewPager(vp);
        title.setTabMode(TabLayout.MODE_FIXED);
        //侧滑
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.left_menu);

        dayOrNight = (ImageView) findViewById(R.id.dayOrNight);
        dayOrNight.setOnClickListener(this);

        frl = (FrameLayout) findViewById(R.id.slidingHeader);
        rlv = (RelativeLayout) findViewById(R.id.selector);

    }

    private void initVpAdapter() {
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return new TabLayoutTitle().getTitle()[position];
            }
        });
    }

    private void initFragment() {
        list.add(new Live());
        list.add(new Recommend());
        list.add(new Serialize());
        list.add(new Sort());
        list.add(new Find());
    }

    private void initView() {

        sliding = (RelativeLayout) findViewById(R.id.sliding);
        header = (RelativeLayout) findViewById(R.id.header);
        game = (ImageView) findViewById(R.id.iconGame);
        downLoad = (ImageView) findViewById(R.id.iconDownload);
        seach = (ImageView) findViewById(R.id.iconSeach);
        login = (ImageView) findViewById(R.id.iconLogin);
        nick = (TextView) findViewById(R.id.nick);
        vp = (ViewPager) findViewById(R.id.vp);
        title = (TabLayout) findViewById(R.id.title);

        sliding.setOnClickListener(this);
        game.setOnClickListener(this);
        downLoad.setOnClickListener(this);
        seach.setOnClickListener(this);
        login.setOnClickListener(this);
        nick.setOnClickListener(this);

        InitTools.selectColor(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sliding:

                break;
            case R.id.iconGame:

                break;
            case R.id.iconDownload:

                break;
            case R.id.iconSeach:

                break;

            case R.id.dayOrNight:
                SharedPreferences shp = getSharedPreferences("switchMode", MODE_PRIVATE);
                int mode = shp.getInt("mode", 0);
                if (mode == 0) {
                    InitTools.selectColor(1);
                } else {
                    InitTools.selectColor(0);
                }
                switchMode();
                break;

        }
    }

    private void switchMode() {
        rlv.setBackgroundColor(getResources().getColor(InitTools.map.get("colorSlidingBackground")));
        frl.setBackgroundColor(getResources().getColor(InitTools.map.get("colorBcakground")));
        dayOrNight.setBackgroundResource(InitTools.map.get("icon"));
        vp.setBackgroundColor(getResources().getColor(InitTools.map.get("colorSlidingBackground")));
        title.setBackgroundColor(getResources().getColor(InitTools.map.get("colorSlidingBackground")));
        header.setBackgroundColor(getResources().getColor(InitTools.map.get("colorSlidingBackground")));
    }
}
