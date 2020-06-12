package com.uusafe.testtinker;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends Activity {

    private Button mLoadPatch;
    private Button mChmod;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadPatch = (Button) findViewById(R.id.load_patch);
        textView = (TextView) findViewById(R.id.set_view);
        mChmod = (Button) findViewById(R.id.chmod_id);
        mLoadPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File patch = new File("/sdcard/app-debug.apk");
                android.util.Log.e("zhw","patch is " + patch.exists() + " " + patch.canRead());
                TinkerManager.loadPatch(patch.getPath());
                android.util.Log.e("zhw","click Patch 444");
                chmodRootDir();
            }
        });
        mChmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chmodRootDir();
            }
        });
        textView.setText("on Click 777");
        android.util.Log.e("zhw","new Patch 666");
    }

    public static void chmodRootDir() {
        chmodDir(new File("/data/user/0/com.uusafe.testtinker/"));
    }

    public static void chmodDir(File file) {
        try {
            File[] files = file.listFiles();
            for (File f:files) {
                f.setReadable(true, false);
                f.setExecutable(true, false);
                f.setWritable(true, false);
                android.util.Log.e("zhw","chmod dir " + f.toString());
                if (f.isDirectory()) {
                    chmodDir(f);
                    f.setReadable(true, false);
                    f.setExecutable(true, false);
                    f.setWritable(true, false);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
