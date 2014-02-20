package com.example.travelcompanion;

import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;

public class MainActivity extends Activity {
 
 WebView myBrowser;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  
  myBrowser = (WebView)findViewById(R.id.mybrowser);
  myBrowser.loadUrl("file:///android_asset/Index.html");
  //myBrowser.loadUrl("file:///android_asset/simplemap.html");
  myBrowser.getSettings().setJavaScriptEnabled(true);
 }

}
