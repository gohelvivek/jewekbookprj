package com.avh.jewelbook

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class register : AppCompatActivity() {

    //val subtnn = findViewById<Button>(R.id.apic)


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val subtn = findViewById<Button>(R.id.crec)
        val apbtn = findViewById<Button>(R.id.apic)

        val bch = findViewById<CheckBox>(R.id.chec)

        val mchhh = findViewById<CheckBox>(R.id.mchh)
        var enmobile = findViewById<EditText>(R.id.no).text
        val enwno = findViewById<EditText>(R.id.wno)

        mchhh.setOnCheckedChangeListener { buttonView, isChecked ->

            if (mchhh.isChecked) {

                enwno.text = enmobile

            } else {
                enwno.text = null
            }
        }

        bch.setOnCheckedChangeListener { buttonView, isChecked ->

            if (bch.isChecked) {

                subtn.isEnabled = true
                apbtn.isEnabled = true

            } else {
                subtn.isEnabled = false
                apbtn.isEnabled = false
            }
        }
        subtn.setOnClickListener {
            val db = DBHelper(this, null)

            val ennm = findViewById<EditText>(R.id.nm).text.toString()
            val encnm = findViewById<EditText>(R.id.cnm).text.toString()
            val enmobile = findViewById<EditText>(R.id.no).text.toString()
            val enpwd = findViewById<EditText>(R.id.pwd)
            val enrpwd = findViewById<EditText>(R.id.cpwd)
            val enwno = findViewById<EditText>(R.id.wno).text.toString()
            val enemail = findViewById<EditText>(R.id.email).text.toString()
            val enweb = findViewById<EditText>(R.id.wb).text.toString()
            val enaddre = findViewById<EditText>(R.id.address).text.toString()

            if (ennm.isEmpty() == true || encnm.isEmpty() == true || enmobile.isEmpty() == true || enwno.isEmpty() == true || enemail.isEmpty() == true || enweb.isEmpty() == true || enaddre.isEmpty() == true) {
                Toast.makeText(this, "Textbox is empty", Toast.LENGTH_LONG).show()
            } else {

                val paass: String = enpwd.text.toString()
                val cpass: String = enrpwd.text.toString()
                if (!paass.equals(cpass)) {
                    Toast.makeText(this, " Password Does not match", Toast.LENGTH_LONG).show()
                } else {
                    val name = ennm
                    val ccname = encnm
                    val mobileno = enmobile
                    val pass = enpwd.text.toString()
                    val wtno = enwno
                    val mail = enemail
                    val websitee = enweb
                    val addrress = enaddre

                    db.addName(name, ccname, mobileno, pass, wtno, mail, websitee, addrress)

                    Toast.makeText(this, name + " Added to database", Toast.LENGTH_LONG).show()
                    super.onBackPressed()


                }
            }
        }

        val closebtn = findViewById<Button>(R.id.colse)
        closebtn.setOnClickListener {
            super.onBackPressed()
        }


    }


}