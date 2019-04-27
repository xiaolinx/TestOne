package com.example.testapi;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private EditText phoneNum;
    private TextView show;
    private final static int start = 0;
    private final static int finish = 1;
    private String phone;

    //显示查询的信息
    private static String province;
    private static String city;
    private static String company;
    private static String card;

    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;

    public static final String APPKEY="899ec6d754dbaad7b021ca7d9322554d";

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case start:
                    Toast.makeText(MainActivity.this, "正在查询，请稍后", Toast.LENGTH_SHORT).show();
                    break;
                case finish:
                    show.setText(province + " " + city + " " +  company + " " + card);
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void btn(View view) {
        phone = phoneNum.getText().toString().trim();//将num传给phone
        if (!TextUtils.isEmpty(phone)) {
            new Thread() {
                public void run() {
                    handler.obtainMessage(start).sendToTarget();
                    getRequest(phone);
                    handler.obtainMessage(finish).sendToTarget();
                };
            }.start();
        } else {
            Toast.makeText(MainActivity.this, "输入号码不能为空", Toast.LENGTH_SHORT).show();
        }
    }
//查询
    public static void getRequest(String phone) {
        String result = null;
        String url = "http://apis.juhe.cn/mobile/get";
        Map params = new HashMap();//请求参数
        params.put("phone", phone);
        params.put("key", APPKEY);
        params.put("dtype","");
        try {
            //解析
            result = net(url, params, "GET");
            JSONObject object = new JSONObject(result);
            JSONObject ob = new JSONObject(object.get("result").toString() + "");

            province = ob.getString("province");
            city = ob.getString("city");
            company = ob.getString("company");
            card = ob.getString("card");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String net(String strUrl, Map params, String method) throws Exception {
        //初始化
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();

            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    private static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        phoneNum = (EditText) findViewById(R.id.phoneNum);
        show = (TextView) findViewById(R.id.text);
    }
}
