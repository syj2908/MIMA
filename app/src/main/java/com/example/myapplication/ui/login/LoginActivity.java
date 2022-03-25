package com.example.myapplication.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    //两个布局
    private LinearLayout login_layout;
    private LinearLayout register_layout;
    //login组件
    private EditText login_psd;
    private TextView login_info1;
    private TextView login_info2;
    private Button btn_login;
    //register组件
    private EditText psd1;
    private EditText psd2;
    private Button btn_register;
    private TextView register_info1;
    private TextView register_info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        login_layout = findViewById(R.id.login_layout);
        register_layout = findViewById(R.id.register_layout);

        String nullPsd = "";
        SharedPreferences pref = getSharedPreferences("LoginPsd",0);
        String psd = pref.getString("psd","");
        if (psd.equals(nullPsd)){
            //register
            register_layout.setVisibility(View.VISIBLE);

            psd1 = findViewById(R.id.register_psd1);
            psd2 = findViewById(R.id.register_psd2);
            btn_register = findViewById(R.id.set_register_psd);
            register_info1 = findViewById(R.id.register_info1);
            register_info2 = findViewById(R.id.register_info2);

            btn_register.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    if (Objects.equals(psd1.getText().toString(),nullPsd)){
                        psd1.setText(nullPsd);
                        psd2.setText(nullPsd);
                        register_info2.setVisibility(View.INVISIBLE);
                        register_info1.setVisibility(View.VISIBLE);
                    } else {
                        register_info1.setVisibility(View.INVISIBLE);
                        if (Objects.equals(psd1.getText().toString(), psd2.getText().toString())){
                            register_info2.setVisibility(View.INVISIBLE);
                            String psd = psd1.getText().toString();
                            SharedPreferences.Editor editor = getSharedPreferences("LoginPsd",0).edit();
                            editor.putString("psd",psd);
                            editor.commit();
                            Toast.makeText(LoginActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            register_info2.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        } else {
            //login
            login_layout.setVisibility(View.VISIBLE);

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
                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(LoginActivity.this, MainActivity.class);
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
}
