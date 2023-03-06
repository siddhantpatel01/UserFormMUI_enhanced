package com.example.userformmui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userformmui.databinding.ActivityUserInformationBinding

class UserInformation : AppCompatActivity() {
    private lateinit var binding: ActivityUserInformationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

//
//        val intent = intent
//        val  name = intent.getStringExtra("name")
//        binding.info.editText.toString()=""
        val firstname = intent.getStringExtra("firstname")
        val lastname = intent.getStringExtra("lastname")
        val phone1 = intent.getStringExtra("phone1")
        val phone2 = intent.getStringExtra("phone2")
        val email = intent.getStringExtra("email")
        val gender= intent.getStringExtra("gender")
        val hobbies= intent.getStringExtra("hobbies")
        binding.info.text = " Name:-  $firstname $lastname \n Phone No:-  $phone1 \n Alternative Phone No :- $phone2  \n Email :- $email  \n Gender :-  $gender \n Hobbies :- $hobbies"


    }
}