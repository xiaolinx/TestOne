package com.example.testtwo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.LEFT;


public class TestLinearLayoutActivity extends Activity{
    Button horizon;
    Button vertical;
    Button left;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_linear_layout);
        horizon =findViewById(R.id.horizon);
        vertical=findViewById(R.id.vertical);
        left=findViewById(R.id.left);
        horizon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout l=findViewById(R.id.linearLayout);
                l.setGravity(CENTER);
                l.setOrientation(LinearLayout.HORIZONTAL);
            }
        });
        vertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout l=findViewById(R.id.linearLayout);
                l.setGravity(CENTER);
                l.setOrientation(LinearLayout.VERTICAL);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout l=findViewById(R.id.linearLayout);
                l.setGravity(LEFT|CENTER);
            }
        });
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TestLinearLayoutActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
