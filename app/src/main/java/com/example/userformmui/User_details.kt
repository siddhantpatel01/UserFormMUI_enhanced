package com.example.userformmui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.userformmui.Model.Student_Info
import com.example.userformmui.Utlities.Keys
import com.example.userformmui.databinding.ActivityUserDetailsBinding
import com.google.gson.Gson

class User_details : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    lateinit var student: Student_Info
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = " Student's details"
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_details)

        val tempIntent = intent
        student = Gson().fromJson(tempIntent.getStringExtra(Keys.StudentsingleRecord), Student_Info::class.java)

        binding.fname.text = student.FirstNamee
        binding.Lname.text = student.LastName
        binding.AlterPhone.text = student.altPhones
        binding.Gmail.text = student.emails
        binding.Gmail.text = student.genders
        binding.Phone.text = student.phone

    }
}