package com.example.userformmui

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.userformmui.Adapters.ListViewAdapter
import com.example.userformmui.Factory.Sqlite_Factory
import com.example.userformmui.Model.SqlViewModel
import com.example.userformmui.databinding.ActivityStudentInfoBinding
import com.example.userformmui.repository.Sqlite_DB_Repo

class Student_info_Activity : AppCompatActivity(),
    AdapterView.OnItemLongClickListener {

    private lateinit var ViewModel: SqlViewModel
    lateinit var Factory: Sqlite_Factory
    private lateinit var binding: ActivityStudentInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_info)

        Factory = Sqlite_Factory(Sqlite_DB_Repo(this))
        ViewModel = ViewModelProvider(this, Factory)[SqlViewModel::class.java]
        val ListofStudent = ViewModel.getAllData()

        val myAdapter = ListViewAdapter(ListofStudent)
        binding.listViewUserInfo.adapter = myAdapter


        registerForContextMenu(binding.listViewUserInfo)
        binding.listViewUserInfo.setOnItemLongClickListener(this)

        //binding.listViewUserInfo.setOnItemClickListener(this)
    }
    override fun onItemLongClick(adapter: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
        return false
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.crud_operation_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
            }

            R.id.Update -> {
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }





//    override fun onItemClick(adapter: AdapterView<*>?, view: View?, position: Int, row: Long) {
//        val data = adapter?.getItemAtPosition(position).toString()
//        Toast.makeText(this, "clicked $data", Toast.LENGTH_SHORT).show()
//    }


}