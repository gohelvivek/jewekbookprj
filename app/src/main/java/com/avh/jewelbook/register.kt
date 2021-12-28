package com.avh.jewelbook

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val subtn = findViewById<Button>(R.id.crec)

        val bch = findViewById<CheckBox>(R.id.chec)

        bch.setOnCheckedChangeListener { buttonView, isChecked ->

            if (bch.isChecked) {

                subtn.isEnabled = true

            } else {
                subtn.isEnabled = false
            }
        }
        subtn.setOnClickListener {
            val db = DBHelper(this, null)

            val ennm = findViewById<EditText>(R.id.nm)
            val encnm = findViewById<EditText>(R.id.cnm)
            val enmobile = findViewById<EditText>(R.id.no)
            val enpwd = findViewById<EditText>(R.id.pwd)
            val enrpwd = findViewById<EditText>(R.id.cpwd)
            val enwno = findViewById<EditText>(R.id.wno)
            val enemail = findViewById<EditText>(R.id.email)
            val enweb = findViewById<EditText>(R.id.wb)
            val enaddre = findViewById<EditText>(R.id.address)

            val paass: String = enpwd.getText().toString()
            val cpass: String = enrpwd.getText().toString()
            if (!paass.equals(cpass)){
                Toast.makeText(this," Password Does not match", Toast.LENGTH_LONG).show()
            }else {


                val name = ennm.text.toString()
                val ccname = encnm.text.toString()
                val mobileno = enmobile.text.toString()
                val pass = enpwd.text.toString()
                val wtno = enwno.text.toString()
                val mail = enemail.text.toString()
                val websitee = enweb.text.toString()
                val addrress = enaddre.text.toString()

                db.addName(name, ccname, mobileno, pass, wtno, mail, websitee, addrress)

                Toast.makeText(this, name + " Added to database", Toast.LENGTH_LONG).show()

                ennm.text.clear()
                encnm.text.clear()
                enmobile.text.clear()
                enpwd.text.clear()
                enrpwd.text.clear()
                enwno.text.clear()
                enemail.text.clear()
                enweb.text.clear()
                enaddre.text.clear()
            }
        }

        val closebtn = findViewById<Button>(R.id.colse)
        closebtn.setOnClickListener{
            super.onBackPressed()
        }

    }
}