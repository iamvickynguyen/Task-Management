package com.example.admin.quanlicongviec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class noidungActivity extends AppCompatActivity {
    TextView tv_id, et_noidung, et_thoigian, etnote;
    ImageView imgclose;
    CongViec cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noidung);
        tv_id = findViewById(R.id.textView);
        et_noidung = findViewById(R.id.etnoidung);
        et_thoigian = findViewById(R.id.etthoigian);
        etnote = findViewById(R.id.etnote);
        imgclose = findViewById(R.id.imgclose);

        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        cv=(CongViec)getIntent().getExtras().get("congviec");
        tv_id.setText(cv._id+"");
        et_noidung.setText(cv.noidung);
        et_thoigian.setText(cv.thoigian);
        etnote.setText(cv.note);

        }
    }

