package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.telephony.CellSignalStrength;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class PsdCreatorActivity  extends Activity implements CompoundButton.OnCheckedChangeListener{

    private ImageView psd_creator_back;


    private EditText editText1;
    private CheckBox checkbox_1;
    private CheckBox checkbox_2;
    private CheckBox checkbox_3;
    private CheckBox checkbox_4;
    private Button creat;
    private Button copy;
    private EditText editText2;
    private OnCheckedChangeListener checkbox_listen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_psd_creator);
        psd_creator_back = findViewById(R.id.psd_creator_back);

        checkbox_1 = (CheckBox) findViewById(R.id.id_checkbox_1);
        checkbox_2 = (CheckBox) findViewById(R.id.id_checkbox_2);
        checkbox_3 = (CheckBox) findViewById(R.id.id_checkbox_3);
        checkbox_4 = (CheckBox) findViewById(R.id.id_checkbox_4);
        creat = (Button) findViewById(R.id.btn_psdcreat);
        copy = findViewById(R.id.psd_creator_copy);
        editText1=findViewById(R.id.psd_cre_len);
        editText2=findViewById(R.id.psd_created);

        checkbox_1.setOnCheckedChangeListener(this);
        checkbox_2.setOnCheckedChangeListener(this);
        checkbox_3.setOnCheckedChangeListener(this);
        checkbox_4.setOnCheckedChangeListener(this);


        creat.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //String str=

                int len_psd=Integer.parseInt(editText1.getText().toString());
                Password password = new Password();
                int []para_psd= new int[4];
                if(checkbox_1.isChecked())
                    para_psd[0]=1;
                else
                    para_psd[0]=0;

                if(checkbox_2.isChecked())
                    para_psd[1]=1;
                else
                    para_psd[1]=0;

                if(checkbox_3.isChecked())
                    para_psd[2]=1;
                else
                    para_psd[2]=0;

                if(checkbox_4.isChecked())
                    para_psd[3]=1;
                else
                    para_psd[3]=0;

                editText2.setText(password.generate(len_psd,para_psd[0],para_psd[1],para_psd[2],para_psd[3]));

                Toast.makeText(PsdCreatorActivity.this,"生成成功",Toast.LENGTH_SHORT).show();
            }
        });

        copy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //copy psd
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(editText2.getText());
                Toast.makeText(PsdCreatorActivity.this, "复制成功！", Toast.LENGTH_SHORT).show();
            }
        });

        psd_creator_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
        // TODO Auto-generated method stub

    }
}