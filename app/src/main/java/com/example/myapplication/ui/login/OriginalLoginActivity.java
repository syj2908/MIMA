package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class OriginalLoginActivity extends AppCompatActivity {

    private EditText login_psd;
    private TextView login_info1;
    private TextView login_info2;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.original_activity_login);

        login_psd = findViewById(R.id.login_psd);
        login_info1 = findViewById(R.id.login_info1);
        login_info2 = findViewById(R.id.login_info2);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nullPsd = "";
                String inputPsd = login_psd.getText().toString();
                SharedPreferences pref = getSharedPreferences("LoginPsd",0);
                String psd = pref.getString("psd","");
                if (inputPsd.equals(nullPsd)){
                    login_info1.setVisibility(View.INVISIBLE);
                    login_info2.setVisibility(View.VISIBLE);
                } else {
                    login_info2.setVisibility(View.INVISIBLE);
                    if (inputPsd.equals(psd)) {
                        //跳转
                        login_info1.setVisibility(View.INVISIBLE);
                        Toast.makeText(OriginalLoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
                        Intent intent=new Intent();
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setClass(OriginalLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        login_psd.setText("");
                        login_info1.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }



}