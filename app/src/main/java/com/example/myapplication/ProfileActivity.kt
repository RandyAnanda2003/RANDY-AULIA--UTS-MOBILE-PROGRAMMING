package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    private lateinit var logoutBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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


        // Mengakses SharedPreferences
        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)

        // Mengambil data dari SharedPreferences
        val email = sharedPref.getString("email", "Default Email")
        val nama = sharedPref.getString("nama", "Default Nama")
        val npm = sharedPref.getString("npm", "Default NPM")
        val kelas = sharedPref.getString("kelas", "Default Kelas")

        // Menampilkan data di TextView atau view lain
        val emailTextView: TextView = findViewById(R.id.email)
        val namaTextView: TextView = findViewById(R.id.nama)
        val npmTextView: TextView = findViewById(R.id.npm)
        val kelasTextView: TextView = findViewById(R.id.kelas)

        emailTextView.text = email
        namaTextView.text = nama
        npmTextView.text = npm
        kelasTextView.text = kelas

        logoutBtn = findViewById(R.id.logoutButton)

        logoutBtn.setOnClickListener{
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
