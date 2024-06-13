package com.example.youtubeplay.activity;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

import com.example.youtubeplay.R;

public class CustomWebChromeClient extends WebChromeClient {
    private final FrameLayout fullscreenContainer;
    private final Activity activity;
    private View customView;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private int originalOrientation;

    public CustomWebChromeClient(Activity activity) {
        this.activity = activity;
        this.fullscreenContainer = activity.findViewById(R.id.containerYoutube);
    }

    @Override
    public void onHideCustomView() {
        if (customView == null) {
            return;
        }

        fullscreenContainer.removeView(customView);
        fullscreenContainer.setVisibility(View.GONE);
        customView = null;
        customViewCallback.onCustomViewHidden();

        activity.setRequestedOrientation(originalOrientation);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    @Override
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        if (customView != null) {
            onHideCustomView();
            return;
        }

        customView = view;
        originalOrientation = activity.getRequestedOrientation();
        customViewCallback = callback;

        fullscreenContainer.addView(customView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        fullscreenContainer.setVisibility(View.VISIBLE);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}