package com.avh.jewelbook

import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val btnpro = findViewById<Button>(R.id.btn_edtpro)
        val btnchped = findViewById<Button>(R.id.ch_pwd)
        val edtpwd = findViewById<androidx.cardview.widget.CardView>(R.id.ch_pds)
        val card_pro = findViewById<androidx.cardview.widget.CardView>(R.id.edt_pro)

        val db = DBHelper(this, null)

        var num = intent.getStringExtra("nummm").toString()

        val data: Cursor? = num?.let { db.getAlldata(it) }
        while (data!!.moveToNext()) {

            // var iddd = findViewById<EditText>(R.id.idd)
            var name = findViewById<EditText>(R.id.name)
            var cname = findViewById<EditText>(R.id.cname)
            var num = findViewById<EditText>(R.id.num)
            var wnum = findViewById<EditText>(R.id.wnum)
            var mail = findViewById<EditText>(R.id.mail)
            var site = findViewById<EditText>(R.id.site)
            var adrs = findViewById<EditText>(R.id.adrs)

            //iddd.setText(data.getString(0))
            name.setText(data.getString(1))
            cname.setText(data.getString(2))
            num.setText(data.getString(3))
            wnum.setText(data.getString(5))
            mail.setText(data.getString(6))
            site.setText(data.getString(7))
            adrs.setText(data.getString(8))

        }

        val upbtn = findViewById<Button>(R.id.updatebtn)
        upbtn.setOnClickListener {
            Toast.makeText(applicationContext, "hello", Toast.LENGTH_LONG).show()
        }

        btnpro.setOnClickListener {
            card_pro.isVisible = true
            edtpwd.isVisible = false
        }
        btnchped.setOnClickListener {
            card_pro.isVisible = false
            edtpwd.isVisible = true
        }

        var enpwwd = findViewById<EditText>(R.id.npass)
        var encpwdd = findViewById<EditText>(R.id.cnpass)

        val btnchpass = findViewById<Button>(R.id.Changebtn)
        btnchpass.setOnClickListener {
            val paass: String = enpwwd.getText().toString()
            val cpass: String = encpwdd.getText().toString()

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