package com.avh.jewelbook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Account() : AppCompatActivity() {



    lateinit var toggle: ActionBarDrawerToggle
    val db = DBHelper(this, null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle("Profile")
            actionBar.setHomeAsUpIndicator(R.drawable.backarrow)
        }

        viewCustomer()

        val man = findViewById<Button>(R.id.fabbb)
        man.setOnClickListener {
            val intent = Intent(this, AddCustomerActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("WrongConstant")
    private fun viewCustomer(){
        val customerslist = db.getCustomers(this)
        val adapter = CustomerAdapter(this,customerslist)
        val rv:RecyclerView = findViewById(R.id.rvv)
        rv.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@Account, home::class.java)
        startActivity(intent)
        finish()
        return super.onOptionsItemSelected(item)
    }
    override fun onResume() {
        viewCustomer()
        super.onResume()
    }
  private fun onListItemClick(position: Int) {
      Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
}
}
