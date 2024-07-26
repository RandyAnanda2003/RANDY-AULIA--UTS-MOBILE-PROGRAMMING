package com.example.myapplication

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class AlumniActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button
    lateinit var dbh: DBHelper
    private lateinit var newArray: ArrayList<Datalist>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alumni)
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

        recyclerView = findViewById(R.id.recylerView)

        dbh = DBHelper(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        getAlumniData()
    }

    private fun getAlumniData() {
        val newcursor: Cursor? = dbh.getText()
        newArray = ArrayList<Datalist>()
        while (newcursor!!.moveToNext()) {
            val unpm = newcursor.getString(0)
            val uname = newcursor.getString(1)
            newArray.add(Datalist(unpm, uname))
        }

        val adapter = MyAdapter2(newArray)
        adapter.setOnItemClickListener(object : MyAdapter2.onItemClickListener {
            override fun onItemClick(position: Int) {
                // Handle item click here
            }
        })
        recyclerView.adapter = adapter
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
