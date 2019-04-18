package com.example.testfive1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {
    SQLiteDatabase db;
    Button bn = null;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/my.db3",null);
        listView =(ListView)findViewById(R.id.show);
        bn = (Button)findViewById(R.id.ok);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                String title=((EditText)findViewById(R.id.title)).getText().toString();
                String content=((EditText)findViewById(R.id.content)).getText().toString();
                try {
                    insertData(db,title,content);
                    Cursor cursor =db.rawQuery("select * from news_inf",null);
                    inflateList(cursor);
                }
                catch (SQLiteException se){
                    db.execSQL("create table news_inf(_id integer"
                            +" primary key autoincrement,"
                            + " news_title varchar(50),"
                            + " news_content varchar(255))"
                    );
                    insertData(db,title,content);
                    Cursor cursor=db.rawQuery("select * from news_inf",null);
                    inflateList(cursor);
                }
            }
        });
    }

    private void insertData(SQLiteDatabase db, String title, String content)//插入数据
    {
        db.execSQL("insert into news_inf values(null,?,?)",new String[]{title,content});
    }

    private void inflateList(Cursor cursor)
    {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this,
                R.layout.line,
                cursor,
                new String[]{"news_title" , "news_content"},
                new int[]{R.id.my_title,R.id.my_content},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroy()//退出时关闭
    {
        super.onDestroy();
        if(db!=null&&db.isOpen())
        {
            db.isOpen();
        }
    }
}
