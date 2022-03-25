package com.example.myapplication.ui.psdbook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ChangeDetailActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private MyDatabaseHelper dbHelper;
    private String curr_ID;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_change_detail);

        ImageView detail_back = findViewById(R.id.change_detail_back);
        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8};

        //receive curr_ID
        Intent intent = getIntent();
        curr_ID = intent.getStringExtra("curr_ID");

        //create or open the database.
        dbHelper = new MyDatabaseHelper(this,"Account.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Button savaData = findViewById(R.id.save_data);
        editText1 = findViewById(R.id.company);
        editText2 = findViewById(R.id.user_name);
        editText3 = findViewById(R.id.password);

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

                editText1.setText(dCompany);
                editText2.setText(dUserName);
                editText3.setText(dPassword);
            } while (cursor.moveToNext());
        }

        savaData.setOnClickListener(v -> {

            //read the string form editText
            String company = editText1.getText().toString();
            String eCompany = DES.desEncrypt(company, key);

            String userName = editText2.getText().toString();
            String eUserName = DES.desEncrypt(userName, key);

            String password = editText3.getText().toString();
            String ePassword = DES.desEncrypt(password, key);

            //write in
            ContentValues values = new ContentValues();
            values.put("company",eCompany);
            values.put("userName",eUserName);
            values.put("password",ePassword);
            db.update("Account_info",values,"id=?",new String[]{curr_ID});
            values.clear();

            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
            finish();
        });

        detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}