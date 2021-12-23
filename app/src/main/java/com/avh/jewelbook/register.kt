package com.avh.jewelbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}