package com.example.lenovo.indexhtml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private Button btn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.my_button);
        webView = (WebView) findViewById(R.id.My_webview);
        tv = (TextView) findViewById(R.id.my_textview);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface( new AndroidBridge(tv),"android");

        webView.loadUrl("file:///android_asset/index.html");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:useJs()");
            }
        });
    }
}
class AndroidBridge{
    private TextView tv;
    public AndroidBridge(TextView tv){
        this.tv = tv;
    }
    @JavascriptInterface
    public void changeText(){
        tv.setText("收到了来自网页的消息");
    }

}
