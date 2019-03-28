package com.example.testone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

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

    }
}
