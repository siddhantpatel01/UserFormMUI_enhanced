package com.example.userformmui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.userformmui.Factory.SharedPrefViewmodelFactory
import com.example.userformmui.Model.SharedPrefrenceViewmodel
import com.example.userformmui.databinding.ActivitySignUpBinding
import com.example.userformmui.repository.SharedPreferenceRepo

class SignUp : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignUpBinding
    lateinit var viewmodelFactory: SharedPrefViewmodelFactory
    lateinit var viewmodel: SharedPrefrenceViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.hide();
        getWindow().getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        );// set status background white


        getSupportActionBar()?.hide()
        binding.AlreadyAccount.setOnClickListener(this)
        viewmodelFactory = SharedPrefViewmodelFactory(SharedPreferenceRepo,this)
        viewmodel = ViewModelProvider(this, viewmodelFactory)[SharedPrefrenceViewmodel::class.java]

       // binding.lifecycleOwner = this

        viewmodel.getFirstname()
        binding.signUp.setOnClickListener {
            Toast.makeText(this, "successfully registered", Toast.LENGTH_LONG).show()
            viewmodel.saveData(binding.Firstname.text.toString(), binding.Lastname.text.toString(),binding.Phone1.text.toString())
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.Already_Account -> {
                startActivity(Intent(this, SignIn::class.java))
            }



        }


    }
}