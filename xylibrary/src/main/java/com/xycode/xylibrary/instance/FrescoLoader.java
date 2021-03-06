package com.xycode.xylibrary.instance;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xycode.xylibrary.utils.ImageUtils;

/**
 * Created by XY on 2016-09-27.
 */

public class FrescoLoader {

    private static FrescoLoader instance;
    private OnFrescoListener onFrescoListener = null;

    public static FrescoLoader getInstance() {
        if (instance == null) {
            instance = new FrescoLoader();
        }
        return instance;
    }

    public static void init(Context context, OnFrescoListener onFrescoListener) {
        getInstance().onFrescoListener = onFrescoListener;
        if (context != null) Fresco.initialize(context);
    }

    public static String getPreviewUri(String url) {
        if (url.startsWith("http") && getInstance().onFrescoListener != null) {
            return getInstance().onFrescoListener.getPreviewUri(url);
        }
        return null;
    }

    public static void setImageURI(SimpleDraweeView simpleDraweeView, String url) {
        ImageUtils.setImageUriWithPreview(simpleDraweeView, url, FrescoLoader.getPreviewUri(url));
    }

    public interface OnFrescoListener {
        String getPreviewUri(String url);
    }

}
