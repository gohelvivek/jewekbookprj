package com.avh.jewelbook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class Account : AppCompatActivity() {

    companion object{
        lateinit var dbHandler: DBHandler
    }

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        dbHandler = DBHandler(this,null,null,1, )

        viewCustomer()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawlout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.master -> Toast.makeText(applicationContext, "Home Clicked", Toast.LENGTH_LONG)
                    .show()
                R.id.acc ->  {
                    val intent= Intent(this,Account::class.java)
                    startActivity(intent)
                }


                R.id.coim -> Toast.makeText(
                    applicationContext,
                    "Contacts Import Clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.opst -> Toast.makeText(
                    applicationContext,
                    "Opening Stock Clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.sell -> Toast.makeText(
                    applicationContext,
                    "Sell/Purchase Clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.caseb -> Toast.makeText(
                    applicationContext,
                    "Casebook Clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.repo -> Toast.makeText(
                    applicationContext,
                    "Reports Clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.setti -> Toast.makeText(
                    applicationContext,
                    "Settings Clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.supp -> Toast.makeText(
                    applicationContext,
                    "Support Clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.profi -> Toast.makeText(
                    applicationContext,
                    "Profile Clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.logouttt -> Toast.makeText(applicationContext,"log out",Toast.LENGTH_SHORT)
            }
            true
        }

    }

    @SuppressLint("WrongConstant")
    private fun viewCustomer(){
        val customerslist = dbHandler.getCustomers(this)
        val adapter = CustomerAdapter(this,customerslist)
        val rv:RecyclerView = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
