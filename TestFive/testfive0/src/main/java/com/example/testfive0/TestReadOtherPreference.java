package com.example.testfive0;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestReadOtherPreference extends Activity {
    Context useCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_read_other_preference);
        try {
            useCount = createPackageContext("com.example.testfive",Context.MODE_PRIVATE);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs= useCount.getSharedPreferences("count",Activity.MODE_PRIVATE);
        int count=prefs.getInt("count",0);
        TextView show=(TextView)findViewById(R.id.show);
        show.setText("UseCount应用程序以前被使用了"+ count +"次。");
    }
}
