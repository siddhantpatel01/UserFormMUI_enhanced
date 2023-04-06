package com.example.userformmui.Model

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userformmui.Observable.Observables
import com.example.userformmui.SignIn
import com.example.userformmui.Utlities.Keys
import com.example.userformmui.databinding.ActivitySignUpBinding
import com.example.userformmui.repository.SharedPreferenceRepo

class SharedPrefrenceViewmodel(private val sharedPrefRepo: SharedPreferenceRepo, private val context: Context) : ViewModel()  {

    var firstName: MutableLiveData<String> = MutableLiveData()
    val lastName : MutableLiveData<String> = MutableLiveData()
    var baseObservable : Observables = Observables()
    var errormessage : MutableLiveData<String> = MutableLiveData()

    fun saveData(fname: String, lname: String ,phonenumber:String ){
        sharedPrefRepo.getPreference(context).edit().putString(Keys.FNAME, fname).commit()
        sharedPrefRepo.getPreference(context).edit().putString(Keys.LNAME, lname).commit()
        sharedPrefRepo.getPreference(context).edit().putString(Keys.MOBNO,phonenumber).commit()
    }

    fun getFirstname(): MutableLiveData<String> {
        val fname = sharedPrefRepo.getPreference(context).getString(Keys.FNAME, "")
        firstName.value = fname
        return firstName
    }
    fun getLastname(): MutableLiveData<String> {
        val lname = sharedPrefRepo.getPreference(context).getString(Keys.LNAME, "")
        lastName.value = lname
        return lastName
    }


    fun getPhone(): String {
        val phoneNo = sharedPrefRepo.getPreference(context).getString(Keys.MOBNO, "")
        return phoneNo!!
    }

//    fun validation(context: Context,intent: Intent){
//        if(baseObservable.firstname.isBlank()){
//            errormessage.value = "Please enter the phone number"
//        }else if(baseObservable.firstname.isBlank()){
//            errormessage.value = "Please enter the first name"
//        }else if(baseObservable.lastname.isBlank()){
//            errormessage.value = "Please enter the last name"
//        }else{
//          //  saveData(binding.Firstname.text.toString(), binding.Lastname.text.toString(),binding.Phone1.text.toString())
//            context.startActivity(intent)
//
//
//        }
//
//    }


    }


