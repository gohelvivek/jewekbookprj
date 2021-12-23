package com.avh.jewelbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class forgotpassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_forgotpassword)
    }
}