package com.example.myapplication

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetailaAlumni : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detaila_alumni)

        // Inisialisasi semua EditText
        val npmTextView = findViewById<EditText>(R.id.npmTextView)
        val namaTextView = findViewById<EditText>(R.id.namaTextView)
        val tempatLahirEditText = findViewById<EditText>(R.id.tempatLahirTextView)
        val tanggalLahir = findViewById<EditText>(R.id.tanggalLahirTextView)
        val alamat = findViewById<EditText>(R.id.alamatTextView)
        val agama = findViewById<EditText>(R.id.agamaTextView)
        val telepon = findViewById<EditText>(R.id.telponTextView)
        val tahunMasuk = findViewById<EditText>(R.id.tahunMasukTextView)
        val tahunKeluar = findViewById<EditText>(R.id.tahunKeluarTextView)
        val pekerjaan = findViewById<EditText>(R.id.pekerjaanTextView)
        val jabatan = findViewById<EditText>(R.id.jabatanTextView)
        // Tambahkan EditText lainnya sesuai kebutuhan

        // Ambil data alumni dari database menggunakan DBHelper
        val dbHelper = DBHelper(this)
        val cursor = dbHelper.getText()

        // Cek apakah cursor tidak null dan memiliki data
        if (cursor != null && cursor.moveToFirst()) {
            npmTextView.setText(cursor.getString(cursor.getColumnIndex("npm")))
            namaTextView.setText(cursor.getString(cursor.getColumnIndex("nama")))
            tempatLahirEditText.setText(cursor.getString(cursor.getColumnIndex("tempat_lahir")))
            tanggalLahir.setText(cursor.getString(cursor.getColumnIndex("tanggal_lahir")))
            alamat.setText(cursor.getString(cursor.getColumnIndex("alamat")))
            agama.setText(cursor.getString(cursor.getColumnIndex("agama")))
            telepon.setText(cursor.getString(cursor.getColumnIndex("nomor_telepon")))
            tahunMasuk.setText(cursor.getString(cursor.getColumnIndex("tahun_masuk")))
            tahunKeluar.setText(cursor.getString(cursor.getColumnIndex("tahun_lulus")))
            pekerjaan.setText(cursor.getString(cursor.getColumnIndex("pekerjaan")))
            jabatan.setText(cursor.getString(cursor.getColumnIndex("jabatan")))
            // Tambahkan setText untuk EditText lainnya sesuai kebutuhan
        }
        // Tutup cursor setelah penggunaannya
        cursor?.close()
    }
}
