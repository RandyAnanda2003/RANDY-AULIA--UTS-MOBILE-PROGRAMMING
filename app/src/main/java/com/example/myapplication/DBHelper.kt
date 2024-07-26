package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context):SQLiteOpenHelper(context,"DataAlumni",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE DataAlumni (" +
                "npm TEXT PRIMARY KEY," +
                "nama TEXT," +
                "tempat_lahir TEXT," +
                "tanggal_lahir TEXT," +
                "alamat TEXT," +
                "agama TEXT," +
                "nomor_telepon TEXT," +
                "tahun_masuk TEXT," +
                "tahun_lulus TEXT," +
                "pekerjaan TEXT," +
                "jabatan TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS DataAlumni")
    }

    fun simpan( npm: String, nama: String, tempatLahir: String,
               tanggalLahir: String, alamat: String, agama: String, nomorTelepon: String,
               tahunMasuk: String, tahunLulus: String, pekerjaan: String, jabatan: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("npm", npm)
            put("nama", nama)
            put("tempat_lahir", tempatLahir)
            put("tanggal_lahir", tanggalLahir)
            put("alamat", alamat)
            put("agama", agama)
            put("nomor_telepon", nomorTelepon)
            put("tahun_masuk", tahunMasuk)
            put("tahun_lulus", tahunLulus)
            put("pekerjaan", pekerjaan)
            put("jabatan", jabatan)
        }

        val result = db.insert("DataAlumni", null, contentValues)

        if (result == -1L) {
            return false
        } else {
            return true
        }
    }

    fun getText(): Cursor? {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM DataAlumni", null)
        return cursor
    }

    fun getData(npm: String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM DataAlumni WHERE npm=?", arrayOf(npm))
    }


}