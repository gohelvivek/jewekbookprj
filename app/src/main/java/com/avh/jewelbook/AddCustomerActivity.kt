package com.avh.jewelbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class AddCustomerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)
        var btnSave = findViewById<Button>(R.id.BtnSave)
        var editCustomerName = findViewById<EditText>(R.id.editCustomerName)
        var editCustomerNumber = findViewById<EditText>(R.id.editCustomerNumber)
        var editCustomerPassword = findViewById<EditText>(R.id.editCustomerPassword)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle("Profile")
            actionBar.setHomeAsUpIndicator(R.drawable.backarrow)
        }

        btnSave.setOnClickListener{

            if (editCustomerName.text.isEmpty()){
                Toast.makeText(this,"Enter Customer Name",Toast.LENGTH_SHORT).show()
                editCustomerName.requestFocus()
            }else{
                val db = DBHelper(this, null)
                val name = editCustomerName.text.toString()
                val num = editCustomerNumber.text.toString()
                val pass = editCustomerPassword.text.toString()
                db.addAccount(name, num, pass)
                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
            }
        }

        var btnCancel = findViewById<Button>(R.id.btnClose)
        btnCancel.setOnClickListener{
            ClearEdits()
            finish()
        }
    }
    private fun ClearEdits(){
        var editCustomerName = findViewById<EditText>(R.id.editCustomerName)
        editCustomerName.text.toString()
        var editCustomerNumber = findViewById<EditText>(R.id.editCustomerNumber)
        editCustomerNumber.text.toString()
        var editCustomerPassword = findViewById<EditText>(R.id.editCustomerPassword)
        editCustomerPassword.text.toString()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}