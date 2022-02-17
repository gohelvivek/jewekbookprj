package com.avh.jewelbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class item : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null

    companion object {
        lateinit var DBHelper1: DBHelper1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle("Profile")
            actionBar.setHomeAsUpIndicator(R.drawable.backarrow)
        }

        DBHelper1 = DBHelper1(this, null)
        viewitem()

        val addbtn = findViewById<Button>(R.id.aitem)

        addbtn.setOnClickListener() {
            val intent = Intent(this, additem::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }



    private fun viewitem() {
        val itemlist = DBHelper1.getallitem(this)
        val adapter = Adaptersitems(this, itemlist)
        val rv: RecyclerView = findViewById(R.id.recyclerview)
        rv.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
        rv.adapter = adapter

    }

    override fun onResume() {
        viewitem()
        super.onResume()
    }
}
