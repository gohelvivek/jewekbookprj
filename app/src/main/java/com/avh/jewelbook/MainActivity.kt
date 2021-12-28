package com.avh.jewelbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        val usermo = findViewById<EditText>(R.id.nmet)
        val puw = findViewById<EditText>(R.id.passwordEt)


        //login code here
        val lgl = findViewById<Button>(R.id.lgn_1)
        lgl.setOnClickListener {


            val username: String = usermo.getText().toString()
            val pass: String = puw.getText().toString()


            val db = DBHelper(this, null)

            if (db.getUser(username, pass)) {

                startActivity(Intent(this@MainActivity, home::class.java))


            } else {
                Toast.makeText(applicationContext, "Wrong username/password", Toast.LENGTH_SHORT).show()
            }
        }


            val clbtn = findViewById<Button>(R.id.btn)
            clbtn.setOnClickListener() {
                startActivity(Intent(this@MainActivity, register::class.java))
            }

            val clpass = findViewById<Button>(R.id.butn)
            clpass.setOnClickListener() {
                startActivity(Intent(this@MainActivity, forgotpassword::class.java))
            }
        }
    }
