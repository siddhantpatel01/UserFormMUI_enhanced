package com.example.userformmui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.userformmui.Factory.SharedPrefViewmodelFactory
import com.example.userformmui.Model.SharedPrefrenceViewmodel
import com.example.userformmui.databinding.ActivitySignInBinding
import com.example.userformmui.repository.SharedPreferenceRepo


class SignIn : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignInBinding
    lateinit var viewmodelFactory: SharedPrefViewmodelFactory
    lateinit var viewmodel: SharedPrefrenceViewmodel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
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
        binding.skip.setOnClickListener(this)
        binding.dontAccount.setOnClickListener(this)
        viewmodelFactory = SharedPrefViewmodelFactory(SharedPreferenceRepo,this)
        viewmodel = ViewModelProvider(this, viewmodelFactory)[SharedPrefrenceViewmodel::class.java]

        viewmodel.getFirstname()
        viewmodel.getLastname()


        binding.signIn.setOnClickListener {
           if(binding.Phone.editText?.text.toString().equals(viewmodel.getPhone())){
               startActivity(Intent(this, MainActivity::class.java))
           }else {
               Toast.makeText(this, "${binding.Phone.editText?.text.toString()} not registered", Toast.LENGTH_SHORT).show()
           }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.skip -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.dont_Account -> {
                startActivity(Intent(this, SignUp::class.java))
            }
        }
    }
}