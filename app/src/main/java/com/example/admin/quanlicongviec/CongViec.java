package com.example.admin.quanlicongviec;

import java.io.Serializable;

/**
 * Created by Admin on 5/17/2018.
 */

public class CongViec implements Serializable{
    int _id;
    String noidung;
    String thoigian;
    String note;
    public CongViec (String noidung, String thoigian, String note)
    {
        this.noidung = noidung;
        this.thoigian = thoigian;
        this.note = note;
    }
    public CongViec (int _id, String noidung, String thoigian, String note)
    {
        this._id = _id;
        this.noidung = noidung;
        this.thoigian = thoigian;
        this.note = note;
    }
}
