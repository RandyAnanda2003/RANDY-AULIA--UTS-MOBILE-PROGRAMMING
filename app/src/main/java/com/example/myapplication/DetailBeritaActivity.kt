package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailBeritaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_berita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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

        val judulBerita : TextView = findViewById(R.id.judul_berita)
        val isiBerita : TextView = findViewById(R.id.isi_berita)
        val gambarBerita : ImageView = findViewById(R.id.gambar_berita)

        val bundle : Bundle? = intent.extras

        val judul = bundle!!.getString("judul")
        val idGambar = bundle!!.getInt("idGambar")
        val berita = bundle!!.getString("berita")

        judulBerita.text = judul
        isiBerita.text = berita
        gambarBerita.setImageResource(idGambar)


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