package com.avh.jewelbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class Sell_pur : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_pur)
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle("Profile")
            actionBar.setHomeAsUpIndicator(R.drawable.backarrow)
        }





        val btnop = findViewById<Button>(R.id.aitem)

        val btn = findViewById<Button>(R.id.button3);
        btn.setOnClickListener {
            val intent = Intent(this, sell_open::class.java)
            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val intent = Intent(this@Sell_pur, home::class.java)
        startActivity(intent)
        finish()
        return super.onOptionsItemSelected(item)

    }
}