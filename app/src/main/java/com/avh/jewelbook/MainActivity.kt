package com.avh.jewelbook

import android.content.Intent
import android.database.Cursor
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.nio.channels.NetworkChannel


class MainActivity : AppCompatActivity {
    lateinit var session: sessionmanager
    val lnm = ""
    val cnm = ""

    constructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        val usermo = findViewById<EditText>(R.id.nmet)
        val puw = findViewById<EditText>(R.id.passwordEt)


        //login via api
        val lgbtn = findViewById<Button>(R.id.apilg)
        lgbtn.setOnClickListener {
            val username: String = usermo.getText().toString()
            val pass: String = puw.getText().toString()

            if (username.isEmpty() && pass.isEmpty()) {
                Toast.makeText(this, "Enter Username of PAssword", Toast.LENGTH_LONG).show()
            } else {

                loginfun(username,pass)

            }
        }

        session = sessionmanager(applicationContext)
        if (session.isLoggedIn()) {
            var i: Intent = Intent(applicationContext, home::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }

        //login code here
        val lgl = findViewById<Button>(R.id.lgn_1)
        lgl.setOnClickListener {

            val username: String = usermo.getText().toString()
            val pass: String = puw.getText().toString()

            val db = DBHelper(this, null)

            if (db.getUser(username, pass)) {

                var i: Intent = Intent(applicationContext, home::class.java)
                val data: Cursor? = db.getName(username)
                if (data != null && data.moveToFirst()) {
                    do {
                        val lnm: String = data.getString(0)
                        val cnm: String = data.getString(1)

                        session.createLoginSession(lnm, cnm, username)

                        i.putExtra("namo", lnm)
                        i.putExtra("cnamo", cnm)
                    } while (data.moveToNext())
                } else {
                    Toast.makeText(applicationContext, "Data Is Empty", Toast.LENGTH_SHORT).show()
                }
                startActivity(i)
                finish()
            } else {
                Toast.makeText(applicationContext, "Wrong username/password", Toast.LENGTH_SHORT)
                    .show()
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

    fun loginfun(username: String, pass: String) {
        val URL = "http://192.168.10.48/simple_crud/api/getdata.php"
        val queue = Volley.newRequestQueue(this)

        val req =
            object : StringRequest(Request.Method.POST, URL, Response.Listener { response ->
                if (response == "1"){
                    var intent = Intent(this,HomeApi::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Wrong Username or Password!", Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()

            }) {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params.put("username", username)
                    params.put("password", pass)
                    return params
                }
            }
        queue.add(req)
    }
}
