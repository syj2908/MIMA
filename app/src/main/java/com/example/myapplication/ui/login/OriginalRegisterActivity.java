package com.example.myapplication.ui.login;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.Objects;

public class OriginalRegisterActivity extends AppCompatActivity {

    private EditText psd1;
    private EditText psd2;
    private Button btn_register;
    private TextView info1;
    private TextView info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.original_activity_register);
        psd1 = findViewById(R.id.register_psd1);
        psd2 = findViewById(R.id.register_psd2);
        btn_register = findViewById(R.id.set_register_psd);
        info1 = findViewById(R.id.register_info1);
        info2 = findViewById(R.id.register_info2);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String nullPsd = "";
                if (Objects.equals(psd1.getText().toString(),nullPsd)){
                    psd1.setText(nullPsd);
                    psd2.setText(nullPsd);
                    info2.setVisibility(View.INVISIBLE);
                    info1.setVisibility(View.VISIBLE);
                } else {
                    info1.setVisibility(View.INVISIBLE);
                    if (Objects.equals(psd1.getText().toString(), psd2.getText().toString())){
                        info2.setVisibility(View.INVISIBLE);
                        String psd = psd1.getText().toString();
                        SharedPreferences.Editor editor = getSharedPreferences("LoginPsd",0).edit();
                        editor.putString("psd",psd);
                        editor.commit();
                        Toast.makeText(OriginalRegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OriginalRegisterActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        info2.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

}
