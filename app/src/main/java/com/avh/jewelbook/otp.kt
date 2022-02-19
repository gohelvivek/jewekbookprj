package com.avh.jewelbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class otp : AppCompatActivity() {

    lateinit var session: sessionmanager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_otp)

        session = sessionmanager(applicationContext)
        val setpwbtn = findViewById<Button>(R.id.setbtn)
        val otpt = findViewById<EditText>(R.id.otatp)

        val enpwwd = findViewById<EditText>(R.id.nepwd)
        val encpwdd = findViewById<EditText>(R.id.cpewd)

        val mesaage = intent.getStringExtra("message_key").toString()


        setpwbtn.setOnClickListener {

            val paass: String = "963548"
            val cpass: String = otpt.getText().toString()

            if (paass.equals(cpass)) {

                val paass: String = enpwwd.getText().toString()
                val cpass: String = encpwdd.getText().toString()

                if (!paass.equals(cpass)) {
                    Toast.makeText(this, " Password Does not match", Toast.LENGTH_LONG).show()


                } else {

                    val db = DBHelper(this, null)

                    if (db.updateData(cpass, mesaage)) {

                        Toast.makeText(this, " Password changed", Toast.LENGTH_LONG).show()
                        otpt.text.clear()
                        enpwwd.text.clear()
                        encpwdd.text.clear()

                        session.createLoginSession("atul sorani", "a@gmail.com", "9601")
                        var i: Intent = Intent(applicationContext, home::class.java)
                        startActivity(i)
                        finish()


                    } else {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {

                Toast.makeText(this, "OTP does not match", Toast.LENGTH_LONG).show()

            }

        }
    }
}