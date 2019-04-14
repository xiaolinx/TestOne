package com.example.testtwo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.TestLinearLayout).setOnClickListener(this);
        findViewById(R.id.TestRelativeLayout).setOnClickListener(this);
        findViewById(R.id.TestFrameLayout).setOnClickListener(this);
        findViewById(R.id.TestTableLayout).setOnClickListener(this);
        findViewById(R.id.TestGridLayout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.TestLinearLayout:
                intent.setClass(this,TestLinearLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.TestRelativeLayout:
                intent.setClass(this,TestRelativeLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.TestFrameLayout:
                intent.setClass(this,TestFrameLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.TestTableLayout:
                intent.setClass(this,TestTableActivity.class);
                startActivity(intent);
                break;
            case R.id.TestGridLayout:
                intent.setClass(this,TestGridActivity.class);
                startActivity(intent);
                break;
        }
    }
}
