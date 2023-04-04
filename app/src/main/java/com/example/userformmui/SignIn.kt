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
    lateinit var Goforlogin : String

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
        viewmodel.getPhoneNo()



//        viewmodel.errormessage.observe(this, Observer {
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            goForlogin = it
//        })

        viewmodel.getPhoneNo()
        viewmodel.phoneNumber.observe(this, Observer {
             Goforlogin = it
        })

        binding.signIn.setOnClickListener {
           if(Goforlogin == binding.Phone1.text.toString()){
               startActivity(Intent(this, MainActivity::class.java))
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