package com.example.admin.quanlicongviec;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    QuanLyCongViec qlcv;
    ArrayList<CongViec> ds = new ArrayList<CongViec>();
    Calendar lich = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.Listview);
        qlcv = new QuanLyCongViec(MainActivity.this);
        capnhatgiaodien();
        registerForContextMenu(lv);
    }


    public void capnhatgiaodien() {
        ds = qlcv.layTatCaCongViec();
        MyAdapter adapter = new MyAdapter(MainActivity.this, ds);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                        MainActivity.this);
                alertDialog.setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addnote) {
            taoCustomDialogThem();
        } else if (item.getItemId() == R.id.calendar) {

        }
        return super.onOptionsItemSelected(item);
    }

    public void taoCustomDialogThem() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_them_sua_layout, null);
        TextView tv_title = view.findViewById(R.id.title);
        ImageView img_time = view.findViewById(R.id.imgtime);
        final EditText et_tg = view.findViewById(R.id.password);
        Typeface font = Typeface.createFromAsset(getAssets(),"slimtony");
        tv_title.setTypeface(font);
        tv_title.setText("Add Note");
        img_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpdialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // TODO Auto-generated method stub
                                et_tg.setText(hourOfDay + ":" + minute);
                            }
                        },
                        lich.get(Calendar.HOUR),
                        lich.get(Calendar.MINUTE),
                        false);
                tpdialog.show();
            }
        });

        alertDialog.setView(view);

        //nut yes duocnhan
        alertDialog.setPositiveButton("add",
                new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int which) {
                        final EditText et_nd = view.findViewById(R.id.username);
                        final EditText et_note = view.findViewById(R.id.note);

                        CongViec cv = new CongViec(et_nd.getText().toString(), et_tg.getText().toString(), et_note.getText().toString());
                        qlcv.themCongViec(cv);
                        capnhatgiaodien();
                        dialog.cancel();
                    }
                });

        //nut no duocnhan
        alertDialog.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    public void taoCustomDialogSua(CongViec cv)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_them_sua_layout, null);
        alertDialog.setView(view);

        TextView tv_title = view.findViewById(R.id.title);
        Typeface font = Typeface.createFromAsset(getAssets(),"slimtony");
        tv_title.setTypeface(font);
        tv_title.setText("edit");
       // ((TextView)view.findViewById(R.id.title)).setText("EDIT");
        ((TextView)view.findViewById(R.id._id)).setText(cv._id+"");
        ((EditText)view.findViewById(R.id.username)).setText(cv.noidung);
        ((EditText)view.findViewById(R.id.note)).setText(cv.note);
        ((EditText)view.findViewById(R.id.password)).setText(cv.thoigian);

        final EditText et_tg = view.findViewById(R.id.password);
        ImageView img_time = view.findViewById(R.id.imgtime);

        img_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpdialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // TODO Auto-generated method stub
                                et_tg.setText(hourOfDay + ":" + minute);
                            }
                        },
                        lich.get(Calendar.HOUR),
                        lich.get(Calendar.MINUTE),
                        false);
                tpdialog.show();
            }
        });

        //nut yes duocnhan
        alertDialog.setPositiveButton("edit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv_id = view.findViewById(R.id._id);
                        EditText et_nd = view.findViewById(R.id.username);
                        //EditText et_tg = view.findViewById(R.id.password);
                        EditText et_note = view.findViewById(R.id.note);
                        int id=Integer.parseInt(tv_id.getText().toString()); //id cu
                        String noidung=et_nd.getText().toString();// noidung moi
                      //  String thoigian=et_tg.getText().toString();//thoi gian moi
                        String note=et_note.getText().toString();
                        CongViec cv=new CongViec(id,noidung,et_tg.getText().toString(), note);

                        qlcv.suaCongViec(cv);
                        capnhatgiaodien();
                        dialog.cancel();
                    }
                });

        //nut no duocnhan
        alertDialog.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();

    }

    // CONTEXT MENU ON ITEM CLICK - WORKED!
    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inf=getMenuInflater();
        inf.inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int index=info.position;//lay ra duoc index tren listview (adapter)
        int id=ds.get(index)._id;//tu index lay ra congviec roi lay vo id cua cong viec do
        if(item.getItemId()==R.id.sua)
        {
            CongViec cv=ds.get(index);//cong viec can sua
            Intent i=new Intent(MainActivity.this,noidungActivity.class);
            i.putExtra("congviec", cv);
            i.putExtra("noidung", cv.noidung);
            i.putExtra("thoigian", cv.thoigian);
            i.putExtra("note", cv.note);
            startActivityForResult(i,333);//qua activity them sua de sua

        }
        return super.onContextItemSelected(item);
    }*/

}




