package com.example.userformmui.Model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userformmui.Observable.Observables
import com.example.userformmui.Utlities.Keys
import com.example.userformmui.repository.SharedPreferenceRepo

class SharedPrefrenceViewmodel(private val sharedPrefRepo: SharedPreferenceRepo, private val context: Context) : ViewModel() {

    var firstName: MutableLiveData<String> = MutableLiveData()
    val lastName : MutableLiveData<String> = MutableLiveData()
    val phoneNumber : MutableLiveData<String> = MutableLiveData()
    var baseObservable: Observables = Observables()
     var a : MutableLiveData<String> = MutableLiveData()

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

    //    fun getPhoneNo(): MutableLiveData<String>{
//        val phoneNO = sharedPrefRepo.getPreference(context).getString(Keys.MOBNO, "")
//        phoneNumber.value = phoneNO
//        return phoneNumber
//    }
    fun getPhone(): String {
        val phoneNo = sharedPrefRepo.getPreference(context).getString(Keys.MOBNO, "")
        return phoneNo!!
    }
//    fun goLogin(){
//        if(baseObservable.height.equals(getPhoneNo())){
//            a.equals(true)
//        }else{
//            a.value = "sorry"
//        }
    }


