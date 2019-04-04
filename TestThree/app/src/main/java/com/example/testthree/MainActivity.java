package com.example.testthree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements View.OnClickListener {
    Button bn1;
    Button bn2;
    Button bn4;
    Button bn5;
    @BindView(R.id.btn6)
    Button btn6;
    public Button bn7;
    Button bn8;
    TextView show;
    @BindView(R.id.textView)
    TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        show = (TextView) findViewById(R.id.textView);
        bn1 = (Button) findViewById(R.id.btn1);
        bn1.setOnClickListener(this);//Activity作为监听器
        bn2 = (Button) findViewById(R.id.btn2);//匿名内部类
        bn2.setOnClickListener(new View.OnClickListener() {//匿名内部类
            @Override
            public void onClick(View v2) {
                show.setText("点击了采用了匿名内部类绑定的监听器");
            }
        });
        bn4 = (Button) findViewById(R.id.btn4);//内部类
        MyButtonListener listener = new MyButtonListener();
        bn4.setOnClickListener(listener);
        bn5 = (Button) findViewById(R.id.btn5);//外部类
        bn5.setOnClickListener(SendSmsListener);
        bn7 = (Button) findViewById(R.id.btn7);
        bn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v7) {//系统信息的跳转
                Intent i1 = new Intent(MainActivity.this, ConfigurationTest.class);
                startActivity(i1);
            }
        });
        bn8 = (Button) findViewById(R.id.btn8);
        bn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//模拟进度条的跳转
                Intent i2 = new Intent(MainActivity.this, ProgressDialogTest.class);
                startActivity(i2);
            }
        });
    };

    class MyButtonListener implements View.OnClickListener {//内部类
        @Override
        public void onClick(View v4) {
            show.setText("点击了采用了内部类绑定的监听器");
        }
    }

    @Override
    public void onClick(View v1)//相应Activity的函数
    {
        show.setText("点击了采用了Activity绑定的监听器");
    }

    public void clickTag(View v3)//绑定到标签所用函数 利用xml文件中的id onclick
    {
        show.setText("点击了采用了绑定到标签绑定的监听器");
    }

    View.OnClickListener SendSmsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v5) {
            show.setText("点击了采用外部类绑定的监听器");
        }
    };

    @OnClick(R.id.btn6)//利用butterknife
    public void onClick() {
        show.setText("点击了采用ButterKnife绑定的监听器");
    }
}

