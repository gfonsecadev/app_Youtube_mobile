package com.example.youtubeplay.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeplay.R;

public class YoutubeActivety extends AppCompatActivity {
    private WebView webViewYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_activety);

        webViewYoutube = findViewById(R.id.webViewYoutube);
        webViewYoutube.getSettings().setJavaScriptEnabled(true);
        webViewYoutube.setWebViewClient(new WebViewClient());
        webViewYoutube.setWebChromeClient(new CustomWebChromeClient(this));


        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey("idVideo")) {
            String idVideo = bundle.getString("idVideo");
            String link = "<html><body style=\"padding:0; margin:0;\"><iframe width=\"100%\" height=\"100%\"\n" +
                    "src=\"https://www.youtube.com/embed/" + idVideo + "\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe><body><html>";
            webViewYoutube.loadData(link, "text/html", "utf-8");
        }
    }
}