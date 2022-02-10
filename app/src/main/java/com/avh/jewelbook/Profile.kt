package com.avh.jewelbook

import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible

class Profile : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle("Profile")
            actionBar.setHomeAsUpIndicator(R.drawable.backarrow)
        }


        val btnpro = findViewById<Button>(R.id.btn_edtpro)
        val btnchped = findViewById<Button>(R.id.ch_pwd)

        val edtpwd = findViewById<CardView>(R.id.ch_pds)
        val card_pro = findViewById<CardView>(R.id.edt_pro)


        val db = DBHelper(this, null)

        var num = intent.getStringExtra("nummm").toString()

        val data: Cursor? = num?.let { db.getAlldata(it) }
        while (data!!.moveToNext()) {

            // var iddd = findViewById<EditText>(R.id.idd)
            var name = findViewById<EditText>(R.id.name)
            var cname = findViewById<EditText>(R.id.cname)
            var numm = findViewById<EditText>(R.id.num)
            var wnum = findViewById<EditText>(R.id.wnum)
            var mail = findViewById<EditText>(R.id.mail)
            var site = findViewById<EditText>(R.id.site)
            var adrs = findViewById<EditText>(R.id.adrs)


            //iddd.setText(data.getString(0))
            name.setText(data.getString(1))
            cname.setText(data.getString(2))
            numm.setText(data.getString(3))
            wnum.setText(data.getString(5))
            mail.setText(data.getString(6))
            site.setText(data.getString(7))
            adrs.setText(data.getString(8))

        }

        val upbtn = findViewById<Button>(R.id.updatebtn)
        upbtn.setOnClickListener {
            val db = DBHelper(this, null)

            var name = findViewById<EditText>(R.id.name)
            var cname = findViewById<EditText>(R.id.cname)
            var numm = findViewById<EditText>(R.id.num)
            var wnum = findViewById<EditText>(R.id.wnum)
            var mail = findViewById<EditText>(R.id.mail)
            var site = findViewById<EditText>(R.id.site)
            var adrs = findViewById<EditText>(R.id.adrs)

            val ennm = name.text.toString()
            val encnm = cname.text.toString()
            val enmobile = numm.text.toString()
            val enwno = wnum.text.toString()
            val enemail = mail.text.toString()
            val enweb = site.text.toString()
            val enaddre = adrs.text.toString()

            if (ennm.isEmpty() == true || encnm.isEmpty() == true || enmobile.isEmpty() == true || enwno.isEmpty() == true || enemail.isEmpty() == true || enweb.isEmpty() == true || enaddre.isEmpty() == true) {
                Toast.makeText(this, "Textbox is empty", Toast.LENGTH_LONG).show()
            } else {
                val db = DBHelper(this, null)
                if (db.updateMainData(num, ennm, encnm, enmobile, enwno, enemail, enweb, enaddre)) {
                    Toast.makeText(applicationContext, "Profile Updated", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "Profile Not Update", Toast.LENGTH_LONG).show()
                }
            }
        }

        btnpro.setOnClickListener {
            btnchped.setTextColor(Color.parseColor("#B3B3B3"))
            btnpro.setTextColor(Color.parseColor("#ffffff"))
            btnpro.setBackgroundColor(Color.parseColor("#8c3b3b"))
            btnchped.setBackgroundColor(Color.parseColor("#ffffff"))
            card_pro.isVisible = true
            edtpwd.isVisible = false
        }
        btnchped.setOnClickListener {
            btnpro.setTextColor(Color.parseColor("#B3B3B3"))
            btnchped.setTextColor(Color.parseColor("#ffffff"))
            btnpro.setBackgroundColor(Color.parseColor("#ffffff"))
            btnchped.setBackgroundColor(Color.parseColor("#8c3b3b"))
            card_pro.isVisible = false
            edtpwd.isVisible = true
        }

        var enpwwd = findViewById<EditText>(R.id.npass)
        var encpwdd = findViewById<EditText>(R.id.cnpass)

        val btnchpass = findViewById<Button>(R.id.Changebtn)
        btnchpass.setOnClickListener {
            val paass: String = enpwwd.getText().toString()
            val cpass: String = encpwdd.getText().toString()

            if (paass.isEmpty() && cpass.isEmpty()) {
                Toast.makeText(applicationContext, "Enter Password !", Toast.LENGTH_LONG).show()
            } else {

                if (!paass.equals(cpass)) {
                    Toast.makeText(this, " Password Does not match", Toast.LENGTH_LONG).show()
                } else {
                    val db = DBHelper(this, null)

                    if (db.updateData(cpass, num)) {

                        Toast.makeText(this, " Password changed", Toast.LENGTH_LONG).show()
                        enpwwd.text.clear()
                        encpwdd.text.clear()

                    } else {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }


                }
            }
        }
    }



    override fun onBackPressed() {


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = Intent(this@Profile, home::class.java)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@Profile, home::class.java)
        startActivity(intent)
        finish()
        return super.onOptionsItemSelected(item)
    }
}