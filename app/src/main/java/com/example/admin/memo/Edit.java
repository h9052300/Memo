package com.example.admin.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class Edit extends AppCompatActivity {
    TextView txv;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent it = getIntent(); //取得傳入的Intent物件
        //int no = it.getIntExtra("編號", 0); //讀出名為"編號"的Int資料，若沒有則傳回0
        String s = it.getStringExtra("備忘"); //讀出名為"備忘"的String資料
        //String ds = it.getStringExtra("日期");

        txv = (TextView) findViewById(R.id.textView);
        //txv.setText(no + ".");
        txv.setText(s.substring(0, 2)); //將編號顯示在畫面左上角
        edt = (EditText) findViewById(R.id.editText);
        if (s.length() > 3)
            edt.setText(s.substring(3)/** + "\n" + ds*/); //將傳來的備忘資料去除前3個字，然後填入EditText元件中，加上收到的日期時間字串
    }

    public void onCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onSave(View view) {
        Intent it2 = new Intent();
        it2.putExtra("備忘", txv.getText() + " " + edt.getText());
        it2.putExtra("日期", new Date().toString());
        setResult(RESULT_OK, it2);
        finish();
    }
}
