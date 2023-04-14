package com.example.userformmui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class User_details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = " Student's details"
        setContentView(R.layout.activity_user_details)
    }
}