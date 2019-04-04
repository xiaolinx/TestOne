package com.example.testthree;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProgressDialogTest extends Activity {
    private ProgressBar progressBar;
    private Button btn9;
    private Handler handler;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_progressdialog);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == 0x123) {
                            if (i <= 100) {
                                progressBar.setProgress(i);
                                i++;
                            }
                        }
                    }
                };
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0x123);
                    }
                }, 0, 1000);
            }
        });
    }
}

