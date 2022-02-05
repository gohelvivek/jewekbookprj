package com.avh.jewelbook

import android.content.Intent
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

       /* val ooname = findViewById<TextView>(R.id.oname)
        ooname.setText(intent.getStringExtra("namo"))

        val oocname = findViewById<TextView>(R.id.cname)
        oocname.setText(intent.getStringExtra("namo"))*/

        var ccname = intent.getStringExtra("namo")
        var cnamee = intent.getStringExtra("cnamo")

        session = sessionmanager(applicationContext)
        var lblname = findViewById<TextView>(R.id.name)
        var lblmail = findViewById<TextView>(R.id.mail)

        session.chackLogin()
        var user: HashMap<String, String> = session.getUerDetails()
        var name: String = user.get(sessionmanager.KEY_NAME)!!
        var mail: String = user.get(sessionmanager.KEY_EMAIL)!!
        lblname.setText("Name : " + ccname)
        lblmail.setText("Company : " + cnamee)


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
                    Toast.makeText(applicationContext, "Address Clicked", Toast.LENGTH_LONG)
                }



                R.id.coim -> Toast.makeText(
                    applicationContext,
                    "Contacts Import Clicked",
                    Toast.LENGTH_LONG
                ).show()

                R.id.item -> {
                    val intent = Intent(this, item::class.java)
                    startActivity(intent)
                }


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