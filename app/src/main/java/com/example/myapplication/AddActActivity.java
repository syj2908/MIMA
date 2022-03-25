package com.example.myapplication;

import android.content.ContentValues;
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

import com.example.myapplication.ui.psdbook.DES;
import com.example.myapplication.ui.psdbook.MyDatabaseHelper;

public class AddActActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private MyDatabaseHelper dbHelper;
    byte[] key = {1, 2, 3, 4, 5, 6, 7, 8};

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_addact);

        //create or open the database.
        dbHelper = new MyDatabaseHelper(getApplicationContext(),"Account.db",null,1);
        dbHelper.getWritableDatabase();

        Button addData = findViewById(R.id.btn_addact);
        ImageView addact_back = findViewById(R.id.addact_back);
        editText1 = findViewById(R.id.add_company);
        editText2 = findViewById(R.id.add_userName);
        editText3 = findViewById(R.id.add_password);

        addact_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //read the string form editText
                String company = editText1.getText().toString();
                String eCompany = DES.desEncrypt(company, key);

                String userName = editText2.getText().toString();
                String eUserName = DES.desEncrypt(userName, key);

                String password = editText3.getText().toString();
                String ePassword = DES.desEncrypt(password, key);

                //write in
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("company",eCompany);
                values.put("userName",eUserName);
                values.put("password",ePassword);
                db.insert("Account_info",null,values);
                values.clear();
                Toast.makeText(AddActActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
