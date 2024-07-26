package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class BeritaActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Berita>
    lateinit var idGambar : Array<Int>
    lateinit var judul : Array<String>
    lateinit var deskripsi : Array<String>
    lateinit var berita : Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_berita)

        idGambar = arrayOf(
            R.drawable.berita1,
            R.drawable.berita2,
            R.drawable.berita3
        )

        judul = arrayOf(
           "ORDIK departemen teknik sipil 1989",
            "IKAPUNIJA turut ikut andil dalam akreditasi PNJ",
            "Nostalgia pintu masuk POLTEK UI 1994"

        )

        deskripsi = arrayOf(
            "mahasiswa departemen sipil sedang menjalani ospek jurusan, yuk intip keseruannya",
            "Dalam Rangka Akreditasi Unggul, Pimpinan PNJ mengajak semua stakeholder untuk turut menyukseskan",
            "Wah, siapa ni yang kepo sama jalan kenangan gerbang masuk PNJ tahun 94"
        )

        berita = arrayOf(
            getString(R.string.berita1),
            getString(R.string.berita2),
            getString(R.string.berita3)
        )

        newRecyclerView = findViewById(R.id.recylerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Berita>()
        getUserdata()





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

    }

    private fun getUserdata() {
        for(i in idGambar.indices) {
            val berita = Berita(idGambar[i],judul[i],deskripsi[i])
            newArrayList.add(berita)
        }
        var adapter =  MyAdapter(newArrayList)

        newRecyclerView.adapter =adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                val intent = Intent(this@BeritaActivity,DetailBeritaActivity::class.java)
                intent.putExtra("judul",newArrayList[position].heading)
                intent.putExtra("idGambar",newArrayList[position].titleImage)
                intent.putExtra("berita",berita[position] )

                startActivity(intent)
//                Toast.makeText(this@BeritaActivity, "kamu mengklik beritan no. $position", Toast.LENGTH_SHORT).show()

            }

        } )
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