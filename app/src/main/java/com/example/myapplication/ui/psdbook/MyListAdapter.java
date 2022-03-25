package com.example.myapplication.ui.psdbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.myapplication.R;

public class MyListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private MyDatabaseHelper dbHelper;
    private Cursor cursor;
    private byte[] key = {1, 2, 3, 4, 5, 6, 7, 8};

    MyListAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        dbHelper = new MyDatabaseHelper(mContext.getApplicationContext(), "Account.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.query("Account_info",null,null,
                null,null,null,null);
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView tvIttx, tvTitle, tvContent;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_list_item, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.detail_item_iv);
            holder.tvIttx = convertView.findViewById(R.id.detail_item_text);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvContent = convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //给控件赋值 初始值在这里被覆盖但我觉得不妥
        int count = getCount();
        if (count == 0){
            //do nothing
        } else {
            cursor.moveToFirst();
            for (int i = 0; i < position; i++){
                cursor.moveToNext();
            }
            String curr_company = cursor.getString(cursor.getColumnIndex("company"));
            String dCompany = DES.desDecrypt(curr_company, key);
            String curr_userName = cursor.getString(cursor.getColumnIndex("userName"));
            String dUserName = DES.desDecrypt(curr_userName, key);
            holder.tvIttx.setText(dCompany.substring(0,1));
            holder.tvTitle.setText(dCompany);
            holder.tvContent.setText(dUserName);
        }
        return convertView;
    }
}
