package com.example.userformmui

import android.app.ActionBar
import android.app.Dialog
import android.database.Cursor
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.userformmui.Adapters.ListViewAdapter
import com.example.userformmui.Factory.Sqlite_Factory
import com.example.userformmui.Model.SqlViewModel
import com.example.userformmui.Model.Student_Info
import com.example.userformmui.databinding.ActivityMainBinding
import com.example.userformmui.databinding.ActivityStudentInfoBinding
import com.example.userformmui.repository.*

class Student_info_Activity : AppCompatActivity(), AdapterView.OnItemLongClickListener,
    RadioGroup.OnCheckedChangeListener {

    private lateinit var ViewModel: SqlViewModel
    lateinit var Factory: Sqlite_Factory
    private var position = 0
    var gender: String? = null
    private lateinit var cursor: Cursor
    private lateinit var binding: ActivityStudentInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_info)

        Factory = Sqlite_Factory(Sqlite_DB_Repo(this))
        ViewModel = ViewModelProvider(this, Factory)[SqlViewModel::class.java]
        val ListofStudent = ViewModel.getAllData()

        supportActionBar?.title = "Registered Students"
        val myAdapter = ListViewAdapter(ListofStudent)
        binding.listViewUserInfo.adapter = myAdapter


        registerForContextMenu(binding.listViewUserInfo)
        binding.listViewUserInfo.onItemLongClickListener = this
        setListview()

        //binding.listViewUserInfo.setOnItemClickListener(this)
    }
    override fun onItemLongClick(adapter: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
        this.position = position
        return false
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.crud_operation_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                //Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                cursor.moveToPosition(position)
                val rowId = cursor.getString(0)
                ViewModel.deleteSingleRecord(rowId)
                setListview()
                 }

            R.id.Update -> {
               // Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()

                cursor.moveToPosition(position)
                val rowId = cursor.getString(0)
                val FirstName = cursor.getString(1)
                val LastName = cursor.getString(2)
                val phone = cursor.getString(3)
                val altPhones = cursor.getString(4)
                val emails = cursor.getString(5)
                val genders = cursor.getString(6)
                var tempGender = genders


                val layoutCustomBinding = ActivityMainBinding.inflate(layoutInflater)
                val dialog = Dialog(this)
                dialog.setContentView(layoutCustomBinding.root)
                layoutCustomBinding.submit.text = "UPDATE"
                dialog.setCancelable(false)
                val windowManager = WindowManager.LayoutParams()
                windowManager.width = ActionBar.LayoutParams.MATCH_PARENT
                windowManager.height = ActionBar.LayoutParams.WRAP_CONTENT
                dialog.window?.attributes = windowManager
                dialog.show()
                if(tempGender.equals("male", true)){
                    layoutCustomBinding.male.isChecked = true
                }
                else if(tempGender.equals("male", true)){
                    layoutCustomBinding.Female.isChecked = true
                }else{
                    layoutCustomBinding.TransGender.isChecked = true
                }
                layoutCustomBinding.Firstname.setText(FirstName)
                layoutCustomBinding.Lastname.setText(LastName)
                layoutCustomBinding.Phone1.setText(phone)
                layoutCustomBinding.Phone2.setText(altPhones) // RUN
                layoutCustomBinding.email.setText(emails)



                layoutCustomBinding.radioButton.setOnCheckedChangeListener { radioGroup, i ->
                    val id = radioGroup.checkedRadioButtonId
                    when (id) {
                        R.id.male -> {
                            val tempMale = dialog.findViewById<RadioButton>(id)
                            tempGender = tempMale.text.toString()
                        }
                        R.id.Female -> {
                            val tempFemale = dialog.findViewById<RadioButton>(id)
                            tempGender = tempFemale.text.toString()
                        }
                        R.id.TransGender -> {
                            val tempTrans = dialog.findViewById<RadioButton>(id)
                            tempGender = tempTrans.text.toString()
                        }

                    }
                }

               // genders!!


                layoutCustomBinding.submit.setOnClickListener {
                    ViewModel.updateData(layoutCustomBinding.username.editText?.text.toString(),
                        layoutCustomBinding.UserLastname.editText?.text.toString(), layoutCustomBinding.Phone.editText?.text.toString(),
                        layoutCustomBinding.Phone.editText?.text.toString(),layoutCustomBinding.emaillayout.editText?.text.toString(),
                        tempGender,rowId)
                    setListview()
                    dialog.dismiss()
                }
            }
        }
        return super.onContextItemSelected(item)
    }






//    override fun onItemClick(adapter: AdapterView<*>?, view: View?, position: Int, row: Long) {
//        val data = adapter?.getItemAtPosition(position).toString()
//        Toast.makeText(this, "clicked $data", Toast.LENGTH_SHORT).show()
//    }

    fun setListview(){
        val listOfStudents = getAllData()
        val myAdapter = ListViewAdapter(listOfStudents)
        binding.listViewUserInfo.adapter = myAdapter
        registerForContextMenu(binding.listViewUserInfo)
        binding.listViewUserInfo.onItemLongClickListener = this

       // binding.listViewUserInfo.setOnItemClickListener(this)
    }
    fun getAllData(): ArrayList<Student_Info>{
        cursor = ViewModel.getData()
        var listofStudent = ArrayList<Student_Info>()

        if(cursor.count/*Count return number of row in Cursor*/>0){
            cursor.moveToFirst()
            do {
                val SRNO = cursor.getString(0)
                val FirstName = cursor.getString(1)
                val LastName = cursor.getString(2)
                val phone = cursor.getString(3)
                val altPhones = cursor.getString(4)
                val emails = cursor.getString(5)
                val genders = cursor.getString(6)

                val Student = Student_Info(SRNO,FirstName,LastName,phone, altPhones , emails , genders   )
                listofStudent.add(Student)
            }while (cursor.moveToNext())
        }else{
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()
        }
        return listofStudent
    }


        override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
            when (group?.checkedRadioButtonId) {
                R.id.male -> {
                    val rbMale = findViewById<RadioButton>(checkedId)
                    gender = rbMale.text.toString()
                }
                R.id.Female -> {
                    val rbFemale = findViewById<RadioButton>(checkedId)
                    gender = rbFemale.text.toString()
                }
                R.id.TransGender -> {
                    val rbtransgender = findViewById<RadioButton>(checkedId)
                    gender = rbtransgender.text.toString()
                }

            }
        }
    }


