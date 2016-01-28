package com.example.etu.projet_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by etu on 02/12/15.
 */
public class internetfragment extends Fragment {
    private WebView internetvue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //permet de créer des messages 
        Log.d("intenet", "onCreateView");
        // postionne la vue avec le xml
        View v = inflater.inflate(R.layout.internetfragmentvue, container, false);

        internetvue = (WebView)v.findViewById(R.id.internetview);

       internetvue.getSettings().setJavaScriptEnabled(true);
        internetvue.loadUrl("http://www.google.com");

        internetvue.setWebViewClient(new WebViewClient());

    return v;
    }
}









