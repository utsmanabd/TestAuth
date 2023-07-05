package com.everybodv.testauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        val db = AppDatabase.getInstance(this)
        userDao = db.userDao()

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            lifecycleScope.launch(Dispatchers.Main) {
                val user = userDao.getUser(username)

                if (user != null && user.password == password) {
                    val role = user.role
                    showToast("Login successful! Role: $role")

                    when (role) {
                        "user" -> {}
                        "admin" -> {}
                        "manager" -> {}
                    }
                    // Tambahkan logika untuk membuka activity setelah login berhasil
                    // Contoh: startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(this@MainActivity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}