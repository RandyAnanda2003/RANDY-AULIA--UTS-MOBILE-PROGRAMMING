package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Mendapatkan referensi ke button
        val loginButton: Button = findViewById(R.id.loginButton)
        val email: TextView = findViewById(R.id.email)
        val password: TextView = findViewById(R.id.password)

        // Set padding untuk view dengan ID main
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // SharedPreferences dan editor dideklarasikan di dalam onCreate
        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        // Set onClickListener untuk loginButton
        loginButton.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()

            if(validate(email,password)){
                val nama = "Randy Aulia Ananda Ruslani"
                val npm = "2207411041"
                val kelas = "TI-4B"
                editor.apply{
                    putString("email",email)
                    putString("password",password)
                    putString("nama", nama)
                    putString("npm", npm)
                    putString("kelas", kelas)
                    apply()
                }
                masuk()
            } else {
                Toast.makeText(this, "Email/Password salah", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun validate(femail:String, fpassword: String): Boolean {
        val validEmail = "randyaananda@gmail.com"
        val validPassword = "1234"
        return femail==validEmail && fpassword==validPassword
    }

    // Fungsi untuk menangani klik pada loginButton
    private fun masuk() {
        // Buat Intent untuk memulai TentangActivity
        val intent = Intent(this, TentangActivity::class.java)
        startActivity(intent)
    }
}
