package com.avh.jewelbook

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var session: sessionmanager
    val lnm = ""
    val cnm = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)


        session = sessionmanager(applicationContext)
        if (session.isLoggedIn()) {
            var i: Intent = Intent(applicationContext, home::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }


        val usermo = findViewById<EditText>(R.id.nmet)
        val puw = findViewById<EditText>(R.id.passwordEt)


        //login code here
        val lgl = findViewById<Button>(R.id.lgn_1)
        lgl.setOnClickListener {

            val username: String = usermo.getText().toString()
            val pass: String = puw.getText().toString()

            val db = DBHelper(this, null)

            if (db.getUser(username, pass)) {

                var i: Intent = Intent(applicationContext, home::class.java)
                session.createLoginSession(lnm, cnm)
                val data: Cursor? = db.getName(username)
                if (data != null && data.moveToFirst()) {
                    do {
                        val lnm: String = data.getString(0)
                        val cnm: String = data.getString(1)
                        i.putExtra("namo",lnm)
                        i.putExtra("namo",cnm)
                    } while (data.moveToNext())
                } else {
                    Toast.makeText(applicationContext, "Data Is Empty", Toast.LENGTH_SHORT).show()
                }
                Toast.makeText(applicationContext, lnm, Toast.LENGTH_SHORT).show()
                Toast.makeText(applicationContext, cnm, Toast.LENGTH_SHORT).show()



                startActivity(i)
                finish()
            } else {
                Toast.makeText(applicationContext, "Wrong username/password", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val apilogin = findViewById<Button>(R.id.apilg)

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
