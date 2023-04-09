package com.example.userformmui.Model

import androidx.lifecycle.ViewModel
import com.example.userformmui.repository.Sqlite_DB_Repo

class SqlViewModel(private val repository: Sqlite_DB_Repo) : ViewModel()/*Inherits View model class */ {
    fun createData(fName: String, lName: String, phone: String ){
        repository.createData(fName, lName, phone)
    }
    fun getAllData():ArrayList<Student_Info> {
        return repository.getAllData()
    }
}