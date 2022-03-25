package com.example.myapplication.ui.psdbook;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class PsdDetailActivity extends AppCompatActivity {

    private TextView Text1;
    private TextView Text2;
    private TextView Text3;
    private TextView detailText;
    private MyDatabaseHelper dbHelper;
    private String curr_ID;
    private ImageView detail_back;
    private Button detail_delete;
    private Button copy_act;
    private Button copy_psd;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        detailText = findViewById(R.id.detail_text);
        detail_back = findViewById(R.id.detail_back);
        detail_delete = findViewById(R.id.detail_delete);
        copy_act = findViewById(R.id.copy_act);
        copy_psd = findViewById(R.id.copy_psd);
        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8};

        //receive curr_ID
        Intent intent = getIntent();
        curr_ID = intent.getStringExtra("curr_ID");

        //create or open the database.
        dbHelper = new MyDatabaseHelper(this,"Account.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Button changeData = findViewById(R.id.change_data);
        Text1 = findViewById(R.id.company);
        Text2 = findViewById(R.id.user_name);
        Text3 = findViewById(R.id.password);

        //read form database
        Cursor cursor = db.query("Account_info",null,"id="+curr_ID,
                null,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                //read data and decode
                String curr_company = cursor.getString(cursor.getColumnIndex("company"));
                String dCompany = DES.desDecrypt(curr_company, key);

                String curr_userName = cursor.getString(cursor.getColumnIndex("userName"));
                String dUserName = DES.desDecrypt(curr_userName, key);

                String curr_password = cursor.getString(cursor.getColumnIndex("password"));
                String dPassword = DES.desDecrypt(curr_password, key);
                detailText.setText(dCompany.substring(0,1));
                Text1.setText(dCompany);
                Text2.setText(dUserName);
                Text3.setText(dPassword);
            } while (cursor.moveToNext());
        }

        changeData.setOnClickListener(v -> {
            Intent changeIntent = new Intent(PsdDetailActivity.this, ChangeDetailActivity.class);
            changeIntent.putExtra("curr_ID",curr_ID);
            startActivity(changeIntent);
            finish();
        });

        detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detail_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除Acount_info表中，id=后面数组值的行
                db.delete("Account_info","id=?",new String[]{curr_ID});
                Toast.makeText(PsdDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        copy_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //copy account;
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(Text2.getText());
                Toast.makeText(PsdDetailActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });

        copy_psd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //copy psd;
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(Text3.getText().toString());
                Toast.makeText(PsdDetailActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}