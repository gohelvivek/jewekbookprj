package com.avh.jewelbook

import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class contactPage : AppCompatActivity() {

    var lst: ListView? = null
    var arrayAdapter: ArrayAdapter<String>? = null

    var arrayList: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_page)
        var actionBar: ActionBar ?= supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle("Contact Import")
            actionBar.setHomeAsUpIndicator(R.drawable.backarrow)
        }

        lst = findViewById(R.id.m_lst)
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        val selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_SOURCE
        val resolver = contentResolver

        val res = resolver.query(
            uri,
            projection,
            selection,
            null,
            null
        )
        arrayList = ArrayList()

        if (res!!.count == 0) {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
        } else {
            while (res!!.moveToNext()) {

                val db = DBHelper(this, null)
                val name = res.getString(0)
                val num = res.getString(1)
                var pwd: String = ""

                val data: Cursor = db.getNumber(num)
                if (data != null && data.moveToFirst()) {
                    db.updateNumber(name, num)
                } else {
                    db.addAccount(name, num, pwd)
                    Toast.makeText(this, "Contact Imported", Toast.LENGTH_LONG).show()
                }

                arrayList!!.add(res!!.getString(0) + "\n" + res!!.getString(1))
                arrayAdapter = ArrayAdapter(applicationContext, R.layout.lyout, R.id.c_name, arrayList!!)
                lst!!.setBackgroundColor(Color.rgb(255, 255, 255))
                lst!!.setAdapter(arrayAdapter)

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

}