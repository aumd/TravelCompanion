package com.example.travelcompanion;

import android.os.Bundle;
import android.app.Activity;
import android.view.WindowManager;
import android.webkit.WebView;


public class MainActivity extends Activity {
 
 WebView myBrowser;

 // Comment sa ni for now. Para ni sa home button unta.
 //@Override
// public void onAttachedToWindow() {
//	 if(MyService.Flag == true){

         // TODO Auto-generated method stub
 //        this.getWindow().setType(
 //                WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG
 //                        | WindowManager.LayoutParams.FLAG_FULLSCREEN);
 //        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);

 //    super.onAttachedToWindow();
//	    }else{
//	        finish();
//	    }
//	}


 
 
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
