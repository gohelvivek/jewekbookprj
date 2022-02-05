package com.avh.jewelbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class item : AppCompatActivity() {

    lateinit var session: sessionmanager
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerlayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.master -> Toast.makeText(applicationContext, "Home Clicked", Toast.LENGTH_LONG)
                    .show()
                R.id.acc -> Toast.makeText(applicationContext, "Account Clicked", Toast.LENGTH_LONG)
                    .show()
                R.id.coim -> Toast.makeText(
                    applicationContext,
                    "Contacts Import Clicked",
                    Toast.LENGTH_LONG
                ).show()

                R.id.item -> {
                    var u: Intent = Intent(applicationContext, item::class.java)
                    startActivity(u)
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

        val addbtn = findViewById<Button>(R.id.aitem)

        addbtn.setOnClickListener() {
            val intent = Intent(this, additem::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
