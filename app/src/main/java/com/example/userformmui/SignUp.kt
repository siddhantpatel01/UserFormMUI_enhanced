package com.example.userformmui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.userformmui.Factory.SharedPrefViewmodelFactory
import com.example.userformmui.Model.SharedPrefrenceViewmodel
import com.example.userformmui.databinding.ActivitySignUpBinding
import com.example.userformmui.repository.SharedPreferenceRepo

class SignUp : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivitySignUpBinding
    lateinit var viewmodelFactory: SharedPrefViewmodelFactory
    lateinit var viewmodel: SharedPrefrenceViewmodel
    val data = arrayOf("Guest", "HR", "Admin", "Consultant" , )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//  set status text dark
        window.statusBarColor = ContextCompat.getColor(
            this,
            R.color.white
        )// set status background white


        supportActionBar?.hide()
        binding.AlreadyAccount.setOnClickListener(this)
        viewmodelFactory = SharedPrefViewmodelFactory(SharedPreferenceRepo,this)
        viewmodel = ViewModelProvider(this, viewmodelFactory)[SharedPrefrenceViewmodel::class.java]

       // binding.lifecycleOwner = this

        /*Adapter : - Adapter is a bridge element between data and view
        * Spinner and autoComplete & Multi AutoComplete  : - are uses ArrayAdapter*/
        val dataAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        binding.spinner.adapter = dataAdapter
        binding.spinner.onItemSelectedListener = this

        viewmodel.getFirstname()
        binding.signUp.setOnClickListener {
            if (binding.Firstname.text!!.isBlank()) {
                Toast.makeText(this, "Please enter first name", Toast.LENGTH_SHORT).show()
            } else if (binding.Lastname.text!!.isBlank()) {
                Toast.makeText(this, "Please enter last name", Toast.LENGTH_SHORT).show()
            } else if (binding.Phone1.text!!.isBlank()) {
                Toast.makeText(this, "Please enter phone no", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "successfully registered", Toast.LENGTH_LONG).show()
                viewmodel.saveData(
                    binding.Firstname.text.toString(),
                    binding.Lastname.text.toString(),
                    binding.Phone1.text.toString()
                )
                startActivity(Intent(this, SignIn::class.java))
            }


        }

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.Already_Account -> {
                startActivity(Intent(this, SignIn::class.java))
            }



        }


    }

    override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val data = adapter?.getItemAtPosition(position)
        Toast.makeText(this, "You are selected $data", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(adapter: AdapterView<*>?) {

    }
}