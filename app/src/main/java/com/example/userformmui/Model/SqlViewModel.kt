package com.example.userformmui.Model

import android.database.Cursor
import androidx.lifecycle.ViewModel
import com.example.userformmui.repository.Sqlite_DB_Repo

class SqlViewModel(private val repository: Sqlite_DB_Repo) : ViewModel()/*Inherits View model class */ {
    fun createData(fName: String, lName: String, phone: String , phone1 : String , mail : String , gender : String ){
        repository.createData(fName, lName, phone,phone1,mail,gender)
    }
    fun getAllData():ArrayList<Student_Info> {
        return repository.getAllData()
    }
    fun deleteSingleRecord(rowId: String){
        return repository.deleteSingleRecord(rowId)
    }
    fun getData():Cursor{
        return repository.getData()
    }
    fun updateData(fName: String, lName: String, phone: String,altphone : String,mail:String,Gender: String, rowId: String){
        repository.updateRecord(fName, lName, phone, altphone,mail,Gender,rowId)
    }
}