package com.example.myapplication.ui.psdbook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class PsdBookFragment extends Fragment {

    private ListView mLv1;
    private MyDatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_psdbook,null);
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        mLv1 = view.findViewById(R.id.lv_1);
        mLv1.setAdapter(new MyListAdapter(getActivity()));
        dbHelper = new MyDatabaseHelper(getActivity().getApplicationContext(), "Account.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("Account_info",null,null,
                null,null,null,null);
        mLv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击跳转
                cursor.moveToFirst();
                if (position == 0){
                    //do nothing
                }else {
                    for(int i = 0; i < position; i++){
                        cursor.moveToNext();
                    }
                }
                String curr_ID = cursor.getString(cursor.getColumnIndex("id"));
                Intent intent = new Intent(getActivity(), PsdDetailActivity.class);
                intent.putExtra("curr_ID",curr_ID);
                startActivity(intent);
                cursor.moveToFirst();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden){

        }else {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}