package uk.ac.kent.babylink;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 42350 on 08/08/2017.
 */

public class VideoActivity extends AppCompatActivity {

    WebView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        view = (WebView) findViewById(R.id.wv);

        SharedPreferences prefs = getSharedPreferences("CODE", MODE_PRIVATE);
        String code = prefs.getString("code", null);

        //Enable JavaScript
        view.getSettings().setJavaScriptEnabled(true);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);

        //Set Render Property to High
        view.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        view.getSettings().setDomStorageEnabled(true);
        view.getSettings().setDatabaseEnabled(true);
        view.getSettings().setAppCacheEnabled(true);
        view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        //Load URL
        view.loadUrl("https://videolinkapp.co.uk/" + code);
        view.setWebViewClient(new WebViewClient());

        //The following code is to debug using chrome deve tools
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        view.setWebChromeClient(new WebChromeClient(){
            @Override
            //I think this bit allows the WebView to use the Camera and Microphone etc.
            public void onPermissionRequest(PermissionRequest request) {
                request.grant(request.getResources());
            }



        });
    }

    @Override
    public void onBackPressed() {
        view.destroy();
        finish();
    }
}
