package com.example.userformmui

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userformmui.Adapters.Recycler_view_adapter
import com.example.userformmui.Factory.Sqlite_Factory
import com.example.userformmui.Interface.Recycler_listner
import com.example.userformmui.Model.SqlViewModel
import com.example.userformmui.Model.Student_Info
import com.example.userformmui.Utlities.Keys
import com.example.userformmui.databinding.ActivityRecyclerViewBinding
import com.example.userformmui.databinding.ActivityUserDetailsBinding
import com.example.userformmui.repository.Sqlite_DB_Repo
import com.google.gson.Gson

class Recycler_View : AppCompatActivity(), Recycler_listner {
    lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var ViewModel: SqlViewModel
    lateinit var listofStudent : ArrayList<Student_Info>
    lateinit var Factory: Sqlite_Factory
    private lateinit var cursor: Cursor
    private lateinit var myAdapter: Recycler_view_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view)
        Factory = Sqlite_Factory(Sqlite_DB_Repo(this))
        ViewModel = ViewModelProvider(this, Factory)[SqlViewModel::class.java]

        supportActionBar?.title = "Registered Students"

        setRecyclerview()
        myAdapter.setOnItemClickListener(this)

    }

    private fun setRecyclerview() {
        binding.recyclerViewss.layoutManager = LinearLayoutManager(this)
        //binding.recyclerViewss.layoutManager = GridLayoutManager(this, 2)
        myAdapter = Recycler_view_adapter(getAllData(), this)
        binding.recyclerViewss.adapter = myAdapter
        myAdapter.notifyDataSetChanged()
    }

    fun getAllData(): ArrayList<Student_Info>{
        cursor = ViewModel.getData()
         listofStudent = ArrayList<Student_Info>()

        if(cursor.count/*Count return number of row in Cursor*/>0){
            cursor.moveToFirst()
            do {
                val SRNO = cursor.getString(0)
                val FirstName = cursor.getString(1)
                val LastName = cursor.getString(2)
                val phone = cursor.getString(3)
                val altPhones = cursor.getString(4)
                val emails = cursor.getString(5)
                val dob = cursor.getString(6)
                val genders = cursor.getString(7)
                val course = cursor.getString(8)

                val Student = Student_Info(SRNO,FirstName,LastName,phone, altPhones , emails , genders ,dob , course )
                listofStudent.add(Student)
            }while (cursor.moveToNext())
        }else{
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()
        }
        return listofStudent
    }

    override fun onItemClick(position: Int, student: Student_Info) {
        Toast.makeText(this, "clicked $position", Toast.LENGTH_SHORT).show()
        var intent = Intent(this,User_details::class.java)

        var student = listofStudent[position]
        intent.putExtra(Keys.StudentsingleRecord,Gson().toJson(student))
        startActivity(intent)
    }

}