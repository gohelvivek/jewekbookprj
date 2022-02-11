package com.avh.jewelbook

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class home : AppCompatActivity() {


    lateinit var session: sessionmanager
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        var ccname = intent.getStringExtra("namo")
        var cnamee = intent.getStringExtra("cnamo")

        session = sessionmanager(applicationContext)
        var lblname = findViewById<TextView>(R.id.name)
        var lblmail = findViewById<TextView>(R.id.mail)
        var lblmunber = findViewById<TextView>(R.id.numbr)

        session.chackLogin()
        var user: HashMap<String, String> = session.getUerDetails()
        var name: String = user.get(sessionmanager.KEY_NAME)!!
        var mail: String = user.get(sessionmanager.KEY_EMAIL)!!
        var number: String = user.get(sessionmanager.KEY_NUMBER)!!

        val db = DBHelper(this, null)
        val data: Cursor? = db.getName(number)
        if (data != null && data.moveToFirst()) {
            do {
                val lnnmm: String = data.getString(0)
                val cnnmm: String = data.getString(1)
                lblname.setText("Name : " + lnnmm)
                lblmail.setText("Company : " + cnnmm)
            } while (data.moveToNext())
        } else {
            Toast.makeText(applicationContext, "Data Is Empty", Toast.LENGTH_SHORT).show()
        }
        lblmunber.setText("Number : " + number)

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
                R.id.acc -> {
                    Toast.makeText(applicationContext, "Address Clicked", Toast.LENGTH_LONG)
                }


                R.id.coim -> {
                    val intent = Intent(this, contactImport::class.java)
                    startActivity(intent)
                }

                R.id.item -> {
                    val intent = Intent(this, item::class.java)
                    startActivity(intent)
                }


                R.id.opst -> Toast.makeText(
                    applicationContext,
                    "Opening Stock Clicked",
                    Toast.LENGTH_LONG
                ).show()
                R.id.sell ->  {
                    val intent = Intent(applicationContext, Sell_pur::class.java)
                    startActivity(intent)
                }
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
                    "Settings Clickedasfdsadfsaf",
                    Toast.LENGTH_LONG
                ).show()
                R.id.supp -> Toast.makeText(
                    applicationContext,
                    "Support Clickedsfsafdasdfasfdasfd",
                    Toast.LENGTH_LONG
                ).show()
                R.id.profi -> {
                    val intent = Intent(this, Profile::class.java)
                    intent.putExtra("nummm", number)
                    startActivity(intent)
                }
                R.id.logouttt -> session.logoutUser()
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}