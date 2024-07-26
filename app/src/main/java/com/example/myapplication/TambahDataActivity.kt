package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText



class TambahDataActivity : AppCompatActivity() {
    private lateinit var nama: TextInputEditText
    private lateinit var npm: TextInputEditText
    private lateinit var tempatLahir: TextInputEditText
    private lateinit var tanggalLahir: TextInputEditText
    private lateinit var alamat: TextInputEditText
    private lateinit var agama: TextInputEditText
    private lateinit var tahunMasuk: TextInputEditText
    private lateinit var tahunLulus: TextInputEditText
    private lateinit var telepon: TextInputEditText
    private lateinit var pekerjaan: TextInputEditText
    private lateinit var jabatan: TextInputEditText
    private lateinit var simpanBtn: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data)


        nama = findViewById(R.id.nama)
        npm = findViewById(R.id.npm)
        tempatLahir = findViewById(R.id.tempatlahir)
        tanggalLahir = findViewById(R.id.tanggallahir)
        tahunMasuk = findViewById(R.id.tahunmasuk)
        tahunLulus = findViewById(R.id.tahunlulus)
        alamat = findViewById(R.id.alamat)
        agama = findViewById(R.id.agama)
        telepon = findViewById(R.id.telpon)
        pekerjaan = findViewById(R.id.pekerjaan)
        jabatan = findViewById(R.id.jabatan)
        simpanBtn = findViewById(R.id.simpanButton)
        db = DBHelper(this)


        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_option1 -> {
                    // Tindakan yang diambil ketika opsi "Tambah data" diklik
                    // Intent untuk mengarahkan ke aktivitas "Tambah data"
                    val intent = Intent(this, TambahDataActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_option2 -> {
                    // Tindakan yang diambil ketika opsi "Data Alumni" diklik
                    // Intent untuk mengarahkan ke aktivitas "Data Alumni"
                    val intent = Intent(this, AlumniActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_option3 -> {
                    // Hapus data SharedPreferences
                    val sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()

                    // Mulai MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                    // Tutup aktivitas saat ini
                    finish()

                    true
                }
                else -> false
            }
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome -> {
                    // Pindah ke BeritaActivity saat opsi kedua dipilih
                    val intent = Intent(this, TentangActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navBerita -> {
                    // Pindah ke BeritaActivity saat opsi kedua dipilih
                    val intent = Intent(this, BeritaActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navProfil -> {
                    // Pindah ke BeritaActivity saat opsi kedua dipilih
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }




        simpanBtn.setOnClickListener {
            val npmValue = npm.text.toString()
            val namaValue = nama.text.toString()
            val tempatLahirValue = tempatLahir.text.toString()
            val tanggalLahirValue = tanggalLahir.text.toString()
            val alamatValue = alamat.text.toString()
            val agamaValue = agama.text.toString()
            val nomorTeleponValue = telepon.text.toString()
            val tahunMasukValue = tahunMasuk.text.toString()
            val tahunLulusValue = tahunLulus.text.toString()
            val pekerjaanValue = pekerjaan.text.toString()
            val jabatanValue = jabatan.text.toString()

            if (npmValue.isEmpty() || namaValue.isEmpty() || tempatLahirValue.isEmpty() ||
                tanggalLahirValue.isEmpty() || alamatValue.isEmpty() || agamaValue.isEmpty() ||
                nomorTeleponValue.isEmpty() || tahunMasukValue.isEmpty() || tahunLulusValue.isEmpty() ||
                pekerjaanValue.isEmpty() || jabatanValue.isEmpty()) {
                Toast.makeText(this, "Data tidak lengkap, harap isi semua field", Toast.LENGTH_SHORT).show()
            } else {
                val simpanData = db.simpan(
                    npmValue, namaValue, tempatLahirValue, tanggalLahirValue, alamatValue,
                    agamaValue, nomorTeleponValue, tahunMasukValue, tahunLulusValue,
                    pekerjaanValue, jabatanValue
                )

                if (simpanData) {
                    Toast.makeText(this, "Data alumni berhasil disimpan", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Data alumni gagal disimpan, cek konfig", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navHome -> {
                // Pindah ke BeritaActivity saat opsi kedua dipilih
                val intent = Intent(this, TentangActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.navBerita -> {
                // Pindah ke BeritaActivity saat opsi kedua dipilih
                val intent = Intent(this, BeritaActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.navProfil -> {
                // Pindah ke BeritaActivity saat opsi kedua dipilih
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
