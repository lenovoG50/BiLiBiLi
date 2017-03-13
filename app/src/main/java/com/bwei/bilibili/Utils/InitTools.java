package com.bwei.bilibili.Utils;

import android.app.Application;
import android.content.SharedPreferences;

import com.bwei.bilibili.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

import java.util.HashMap;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/3/10 20:28.
 */

public class InitTools extends Application {
    public static HashMap<String, Integer> map = new HashMap<String, Integer>();
    private SharedPreferences shp;
    private static SharedPreferences.Editor edit;


    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);

        shp = getApplicationContext().getSharedPreferences("switchMode", MODE_PRIVATE);
        edit = shp.edit();
    }

    public static void selectColor(int i) {
        switch (i) {
            case 1://夜间
                map.put("textColor", R.color.textColorNight);
                map.put("colorSlidingBackground", R.color.colorSlidingBackgroundNight);
                map.put("colorBcakground", R.color.colorBcakgroundNight);
                map.put("icon", R.drawable.ic_switch_daily);
                map.put("home", R.color.colorSlidingBackgroundNight);
                edit.putInt("mode", 1);
                edit.commit();
                break;
            case 0://日见模式
                map.put("textColor", R.color.textColorday);
                map.put("colorSlidingBackground", R.color.textColorNight);
                map.put("colorBcakground", R.color.recommendText);
                edit.putInt("mode", 0);
                map.put("icon", R.drawable.ic_switch_night);
                map.put("home", R.color.homeBcakground);

                edit.commit();
                break;
        }
    }
}
