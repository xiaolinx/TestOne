package com.example.testtwo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class TestFrameLayoutActivity extends Activity{

    private FrameLayout frameLayout;
    private ImageView main_imageview;
    private int i = 0;
    Timer timer = new Timer();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.e("@@@", i + "");
            if (i > 5)
            {
                i = 0;
            }
            else {
                switch (i) {
                    case 1:
                        main_imageview.setImageResource(R.drawable.a);
                        break;
                    case 2:
                        main_imageview.setImageResource(R.drawable.b);
                        break;
                    case 3:
                        main_imageview.setImageResource(R.drawable.c);
                        break;
                    case 4:
                        main_imageview.setImageResource(R.drawable.d);
                        break;
                    case 5:
                        main_imageview.setImageResource(R.drawable.e);
                        break;
                    default:
                        break;
                }
                frameLayout.invalidate();
            }
            super.handleMessage(msg);
        }
    };
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_frame_layout);
        initView();
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TestFrameLayoutActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initView() {
        frameLayout= (FrameLayout) findViewById(R.id.frameLayout);
        main_imageview=(ImageView)findViewById(R.id.ImageView);
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                i++;
                Message mesasge = new Message();
                mesasge.what = i;
                handler.sendMessage(mesasge);
            }
        }, 0, 500);
    }
    @Override
    protected void onDestroy()
    {
        timer.cancel();
        super.onDestroy();
    }
}
