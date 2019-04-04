package com.example.testthree;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfigurationTest extends Activity {
    EditText ori;
    EditText navigation;
    EditText touch;
    EditText mnc;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_configuration);
        // 获取应用界面中的界面组件
        ori = (EditText) findViewById(R.id.ori);
        navigation = (EditText) findViewById(R.id.navigation);
        touch = (EditText) findViewById(R.id.touch);
        mnc = (EditText) findViewById(R.id.mnc);
        Button bn = (Button) findViewById(R.id.bn9);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Configuration cfg = getResources().getConfiguration();
                String screen = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ?
                        "横向屏幕": "竖向屏幕";
                String mncCode = cfg.mnc + "";
                String naviName = cfg.orientation == Configuration.NAVIGATION_NONAV ? "没有方向控制": cfg.orientation == Configuration.NAVIGATION_WHEEL ? "滚轮控制方向": cfg.orientation == Configuration.NAVIGATION_DPAD ? "方向键控制方向": "轨迹球控制方向";
                navigation.setText(naviName);
                String touchName = cfg.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "无触摸屏": cfg.touchscreen == Configuration.TOUCHSCREEN_STYLUS ? "触摸笔式触摸屏": "接受手指的触摸屏";
                ori.setText(screen);
                mnc.setText(mncCode);
                touch.setText(touchName);
            }
        });
    }}
