package com.example.admin.quanlicongviec;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 5/17/2018.
 */

public class MyAdapter extends BaseAdapter {
    Context c;
    ArrayList<CongViec> ds;
    //ListView lv;
    MyAdapter(Context c, ArrayList<CongViec> ds)
    {
        this.c = c;
        this.ds = ds;
    }

    public static  class View_Mot_O
    {
        TextView id;
        TextView noidung;
        TextView thoigian;
        TextView note;
        ImageView imgdelete;
        ImageView imgedit;
    }
    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup viewGroup) {
        View_Mot_O mot_o;
        LayoutInflater inf= ((Activity)c).getLayoutInflater();
        if(arg1==null)
        {
            mot_o = new View_Mot_O();
            arg1 = inf.inflate(R.layout.one_item, null);
            mot_o.id=
                    (TextView) arg1.findViewById(R.id.txt_id);
            mot_o.noidung =
                    (TextView)arg1.findViewById(R.id.txt_noidung);
            mot_o.thoigian =
                    (TextView)arg1.findViewById(R.id.txt_thoigian);
            mot_o.note=
                    (TextView) arg1.findViewById(R.id.txt_note);
            mot_o.imgdelete=(ImageView)arg1.findViewById(R.id.imgdelete);
            mot_o.imgedit=(ImageView)arg1.findViewById(R.id.imgedit);

            arg1.setTag(mot_o);
        }
        else
            mot_o=(View_Mot_O)arg1.getTag();

        mot_o.id.setText(ds.get(arg0)._id+"");
        mot_o.noidung.setText(ds.get(arg0).noidung);
        mot_o.thoigian.setText(ds.get(arg0).thoigian);
        mot_o.note.setText(ds.get(arg0).note);

        mot_o.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //arg0 la index tren ds
                int _id=ds.get(arg0)._id;//id can xoa
                //context cung chinh la MainActivity
                //ep context ve MainActivity thi co the goi bien hoac ham viet ben MainActivity
                ((MainActivity)c).qlcv.xoaCongViec(_id);
                ((MainActivity)c).capnhatgiaodien();
                //Toast.makeText(c, arg0+"", Toast.LENGTH_SHORT).show();
            }
        });

        mot_o.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CongViec cv=ds.get(arg0);//cong viec can sua
                ((MainActivity)c).taoCustomDialogSua(cv);
            }
        });

        final View v = arg1;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CongViec cv = ds.get(arg0);
                Intent k=new Intent((MainActivity)c,noidungActivity.class);
                k.putExtra("congviec", cv);
                k.putExtra("noidung", cv.noidung);
                k.putExtra("thoigian", cv.thoigian);
                k.putExtra("note", cv.note);
                c.startActivity(k);//qua activity them sua de sua

            }
        });
        return arg1;
    }
}
