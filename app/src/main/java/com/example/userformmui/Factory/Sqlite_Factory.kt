package com.example.userformmui.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.userformmui.Model.SqlViewModel
import com.example.userformmui.repository.Sqlite_DB_Repo

class Sqlite_Factory(private val repository: Sqlite_DB_Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(SqlViewModel::class.java)) {
            return SqlViewModel(repository) as T
        }

        throw java.lang.IllegalArgumentException("Unknown Class")
    }

}