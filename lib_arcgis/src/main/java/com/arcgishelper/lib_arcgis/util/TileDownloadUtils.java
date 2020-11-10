package com.arcgishelper.lib_arcgis.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Ronny on 2020/11/10
 */
public class TileDownloadUtils {
    public static class LazyHolder {
        private static final TileDownloadUtils INSTANCE = new TileDownloadUtils();
    }

    public static TileDownloadUtils getInstance() {
        return TileDownloadUtils.LazyHolder.INSTANCE;
    }

    private OkHttpClient mHttpClient;

    private TileDownloadUtils() {
        mHttpClient = new OkHttpClient();
    }

    public byte[] getImageFromURL(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = mHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    return body.bytes();
                }
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }
}
