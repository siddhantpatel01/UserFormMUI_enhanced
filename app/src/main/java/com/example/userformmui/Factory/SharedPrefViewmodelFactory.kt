package com.example.userformmui.Factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.userformmui.Model.SharedPrefrenceViewmodel
import com.example.userformmui.repository.SharedPreferenceRepo

class SharedPrefViewmodelFactory(private val sharedPrefRepo: SharedPreferenceRepo, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedPrefrenceViewmodel::class.java)){
            return SharedPrefrenceViewmodel(sharedPrefRepo, context) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}