package com.avh.jewelbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_home)
    }
}