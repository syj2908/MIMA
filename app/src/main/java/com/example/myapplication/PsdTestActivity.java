package com.example.myapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ui.psdbook.CheckStrength;

public class PsdTestActivity extends AppCompatActivity {

    private ImageView psd_test_back;
    private EditText editText;
    private Button btn;
    private View resultLayout;
    private TextView text1;
    private TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_psd_test);

        psd_test_back = findViewById(R.id.psd_test_back);
        editText =findViewById(R.id.psd_test_back_passwd);
        btn = findViewById(R.id.psd_test_back_btn);
        resultLayout = findViewById(R.id.psd_test_back_result_list);
        text1 = findViewById(R.id.psd_test_back_result_text1);
        text2 = findViewById(R.id.psd_test_back_result_text2);
        ColorStateList defaultColor = text1.getTextColors();

        psd_test_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Passwd = editText.getText().toString();
                if(Passwd.equals(""))
                {
                    editText.setHint("密码不能为空！");
                }
                else
                {
                    String strength = CheckStrength.getPasswordLevel(Passwd).toString();
                    String toCN = "";
                    switch (strength) {
                        case "EASY":
                            toCN="低";
                            text1.setTextColor(Color.parseColor("#DC143C"));
                            break;
                        case "MIDIUM":
                            toCN="中";
                            text1.setTextColor(Color.parseColor("#FF8C00"));
                            break;
                        case "STRONG":
                            toCN="高";
                            text1.setTextColor(defaultColor);
                            break;
                        case "VERY_STRONG":
                            toCN="很高";
                            text1.setTextColor(defaultColor);
                            break;
                        case "EXTREMELY_STRONG":
                            toCN="***又高又硬!!***";
                            text1.setTextColor(Color.parseColor("#000000"));
                            break;
                    }
                    text1.setText(toCN);
                    text2.setText(CheckStrength.getAdvice(Passwd));
                    resultLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}