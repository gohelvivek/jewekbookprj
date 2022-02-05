package com.avh.jewelbook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class contactImport : AppCompatActivity() {

    var reqpdf = 21


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_import)
        var reqpdf = 21
        var chbtn = findViewById<Button>(R.id.choosebtn)
        var upbtn = findViewById<Button>(R.id.uploadbtn)

        chbtn.setOnClickListener() {
            var choosefile = Intent(Intent.ACTION_GET_CONTENT)
            choosefile.setType("application/pdf")
            choosefile = Intent.createChooser(choosefile, "Chhose a File")
            startActivityForResult(choosefile, reqpdf)
        }

        upbtn.setOnClickListener(){

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode.equals(reqpdf) && resultCode.equals(RESULT_OK)) {



           /* var txtchose = findViewById<TextView>(R.id.txt_filechose)
            txtchose.setText("Document Selected")
            Toast.makeText(applicationContext, "Document Selected", Toast.LENGTH_SHORT).show()*/
        }
    }

}