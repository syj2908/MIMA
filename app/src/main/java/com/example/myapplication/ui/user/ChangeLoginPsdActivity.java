package com.example.myapplication.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class ChangeLoginPsdActivity extends AppCompatActivity {

    private Button changeLogin;
    private ImageView change_back;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private TextView changeLogin_info1;
    private TextView changeLogin_info2;
    private TextView changeLogin_info3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.example.myapplication.R.layout.activity_changelogin_psd);
        changeLogin = findViewById(R.id.changeLogin);
        change_back = findViewById(R.id.change_back);
        changeLogin_info1 = findViewById(R.id.changeLogin_info1);
        changeLogin_info2 = findViewById(R.id.changeLogin_info2);
        changeLogin_info3 = findViewById(R.id.changeLogin_info3);

        changeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLogin_info1.setVisibility(View.INVISIBLE);
                changeLogin_info2.setVisibility(View.INVISIBLE);
                changeLogin_info3.setVisibility(View.INVISIBLE);
                editText1 = findViewById(R.id.orignPsd);
                editText2 = findViewById(R.id.newPsd);
                editText3 = findViewById(R.id.repeatPsd);

                String orign = editText1.getText().toString();
                String newer = editText2.getText().toString();
                String repeat = editText3.getText().toString();

                SharedPreferences pref = getSharedPreferences("LoginPsd", 0);
                String psd = pref.getString("psd", "");
                String nullPsd = "";
                if (orign.equals(psd)){
                    if (newer.equals(nullPsd)){
                        editText3.setText(nullPsd);
                        changeLogin_info2.setVisibility(View.VISIBLE);
                    } else {
                        if (repeat.equals(newer)){
                            SharedPreferences.Editor editor = getSharedPreferences("LoginPsd", 0).edit();
                            editor.putString("psd", newer);
                            editor.commit();
                            Toast.makeText(ChangeLoginPsdActivity.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            changeLogin_info3.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    changeLogin_info1.setVisibility(View.VISIBLE);
                }

//                if(!repeat.equals(newer)){
//                    changeLogin_info3.setVisibility(View.VISIBLE);
//                }else {
//                    SharedPreferences pref = getSharedPreferences("Loginpsd", 0);
//                    String psd = pref.getString("psd", "");
//                    String nullPsd = "";
//                    if (newer.equals(nullPsd)){
//                        editText3.setText(nullPsd);
//                        changeLogin_info2.setVisibility(View.VISIBLE);
//                    }else {
//                        if (orign.equals(psd)) {
//                            SharedPreferences.Editor editor = getSharedPreferences("Loginpsd", 0).edit();
//                            editor.putString("psd", newer);
//                            editor.commit();
//                            Toast.makeText(ChangeLoginPsdActivity.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
//                        } else {
//                            changeLogin_info1.setVisibility(View.VISIBLE);
//                            editText1.setText(nullPsd);
//                            editText2.setText(nullPsd);
//                            editText3.setText(nullPsd);
//                        }
//                    }
//                }
            }
        });

        change_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        };

    }