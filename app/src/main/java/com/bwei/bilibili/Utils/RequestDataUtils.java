package com.bwei.bilibili.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/3/10 20:38.
 */

public class RequestDataUtils {

    public interface CallBackData {
        void callBackData(String s);
    }

    public static void requestData(String url, final CallBackData callBack) {
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.callBackData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


}
