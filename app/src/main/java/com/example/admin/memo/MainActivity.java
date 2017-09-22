package com.example.admin.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    String[] aMemo = {
            "1. 按一下可以編輯備忘",
            "2. 長按可以清除備忘", "3.", "4.", "5.", "6."};
    ListView lv;
    ArrayAdapter<String> aa; //ListView與備忘錄aMemo的橋樑

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, aMemo);
        lv.setAdapter(aa); //設定ListView的內容
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        Intent it = new Intent(this, Edit.class);
        //it.putExtra("編號", pos + 1);
        it.putExtra("備忘", aMemo[pos]);
        //it.putExtra("日期", new Date().toString()); //取得日期時間
        startActivityForResult(it, pos);
    }

    protected void onActvityResult(int requestCode, int resultCode, Intent it) {
        if (resultCode == RESULT_OK) {
            aMemo[requestCode] = it.getStringExtra("備忘"); //使用傳回的資料更新陣列內容，由Intent物件中取出編輯後的備忘資料，然後存到requestCode位置的備忘項目中
            aa.notifyDataSetChanged(); //通知Adapter陣列內容有更新
            Toast.makeText(this, "備忘資料於\n" + it.getStringExtra("日期") + "\n 修改", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
        aMemo[pos] = (pos + 1) + "."; //將內容清除
        aa.notifyDataSetChanged(); //通知ListView要更新顯示的內容
        return true;
    }
}
