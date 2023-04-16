package com.example.userformmui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.userformmui.Factory.SharedPrefViewmodelFactory
import com.example.userformmui.Factory.Sqlite_Factory
import com.example.userformmui.Model.SharedPrefrenceViewmodel
import com.example.userformmui.Model.SqlViewModel
import com.example.userformmui.Model.Student_Info
import com.example.userformmui.Utlities.Keys
import com.example.userformmui.databinding.ActivityMainBinding
import com.example.userformmui.repository.SharedPreferenceRepo
import com.example.userformmui.repository.Sqlite_DB_Repo
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class MainActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewmodel: SharedPrefrenceViewmodel
    val list: ArrayList<String> = ArrayList()
    lateinit var viewmodelFactory: SharedPrefViewmodelFactory
    //lateinit var viewmodel: SharedPrefrenceViewmodel
    lateinit var Factory : Sqlite_Factory
    lateinit var ViewModel : SqlViewModel
    var gender: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val courses = resources.getStringArray(R.array.Coursees)
        val arrayAdapter = ArrayAdapter(this,R.layout.drop_down_items,courses)
        binding.autoCompletes.setAdapter(arrayAdapter)

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));// set status background white
        // This peace of code for Submit Button
        binding.dob.setOnClickListener(this)
        binding.submit.setOnClickListener(this)
        // This peace of code for Radio Button
        binding.radioButton.setOnCheckedChangeListener(this)

        // This peace of code for Check Box
        viewmodelFactory = SharedPrefViewmodelFactory(SharedPreferenceRepo,this)
        viewmodel = ViewModelProvider(this, viewmodelFactory)[SharedPrefrenceViewmodel::class.java]


        Factory = Sqlite_Factory(Sqlite_DB_Repo(this))

        ViewModel = ViewModelProvider(this, Factory)[SqlViewModel::class.java]

        val ListofStudent = ViewModel.getAllData()


        binding.usertype.setText(viewmodel.getUsertpe()).toString()
       

     // binding.usertype.setText( intent.getStringExtra("usertype "))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        MenuCompat.setGroupDividerEnabled(menu!!, true)//add horizontal divider
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Student_info ->{
                val intent = Intent(this, Student_info_Activity::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout ->{
                startActivity(Intent(this,SignIn::class.java))
            }
            R.id.recycler_view ->{
                startActivity(Intent(this,Recycler_View::class.java))
            }
        }
        return true
    }


    override fun onClick(view: View) {


        when (view?.id) {

            R.id.submit -> {


                if (binding.Firstname.text!!.isEmpty() && binding.Lastname.text!!.isEmpty() && binding.Phone1.editableText.isEmpty() && binding.Phone2.editableText.isEmpty() && binding.email.text!!.isEmpty()) {
                    binding.Firstname.requestFocus()
                    Toast.makeText(this, "fill required information ", Toast.LENGTH_SHORT).show()
                } else if (binding.Firstname.text!!.isEmpty()) {
                    binding.Firstname.requestFocus()
                    Toast.makeText(this, "Please fill your First name", Toast.LENGTH_SHORT).show()

                } else if (binding.Lastname.text!!.isEmpty()) {
                    binding.Lastname.requestFocus()
                    Toast.makeText(this, "Please fill your Last name", Toast.LENGTH_SHORT).show()

                } else if (binding.Phone1.editableText.isEmpty()) {
                    binding.Phone1.requestFocus()
                    Toast.makeText(this, "Please fill your Phone Number name", Toast.LENGTH_SHORT)
                        .show()
                }/* else if (binding.Phone2.editableText.isEmpty()) {
                    binding.Phone2.requestFocus()
                    Toast.makeText(
                        this,
                        "Please fill your Alternate Number name",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (binding.email.text!!.isEmpty()) {
                    binding.email.requestFocus()
                    Toast.makeText(this, "Please fill your Email name", Toast.LENGTH_SHORT).show()

                } else if (gender == null) {
                    Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show()
                }
                else if (binding.Firstname.text!!.isNotEmpty() && binding.Lastname.text!!.isNotEmpty() && binding.Phone1.editableText.isNotEmpty() && binding.Phone2.editableText.isNotEmpty() && binding.email.text!!.isNotEmpty() && (phone1==phone2)) {
                    Toast.makeText(this, "Please don't enter the same number", Toast.LENGTH_SHORT).show()

                }*/
                else {

                    /* val intent = Intent(this, UserInformation::class.java)
                     val firstname = binding.Firstname.editableText.toString()
                     val lastname = binding.Lastname.editableText.toString()
                     val phone1 = binding.Phone1.editableText.toString()
                     val phone2 = binding.Phone2.editableText.toString()
                     val email = binding.email.editableText.toString()
                     val lst = list.toString().replace("[", " ").replace("]", " ")



                     intent.putExtra("firstname", firstname)
                     intent.putExtra("lastname", lastname)
                     intent.putExtra("phone1", phone1)
                     intent.putExtra("phone2", phone2)
                     intent.putExtra("email", email)
                     intent.putExtra("gender", gender)
                     intent.putExtra("hobbies", lst)
 */

                    ViewModel.createData(
                        binding.username.editText?.text.toString(),
                        binding.UserLastname.editText?.text.toString(),
                        binding.Phone.editText?.text.toString(),
                        binding.PhoneA.editText?.text.toString(),
                        binding.emaillayout.editText?.text.toString(),binding.DobLayout.editText?.text.toString(),
                        gender!!,binding.CourseLayout.editText?.text.toString()
                    )

                }
            }
            R.id.dob ->{
                MaterialDatePicker.Builder.datePicker().build()
                    .show(supportFragmentManager, "DATE PICKER")
                val datePickerBuilder = MaterialDatePicker.Builder.datePicker()
                datePickerBuilder.setTitleText("SELECT UR DOB")
                val datePicker = datePickerBuilder.build()


                datePicker.show(supportFragmentManager, "DATE")
                datePicker.addOnPositiveButtonClickListener {
                    val calender = Calendar.getInstance()
                    calender.time = Date(it)
                    val DAY = calender.get(Calendar.DAY_OF_MONTH)
                    val MONTH = calender.get(Calendar.MONTH) + 1
                    val YEAR = calender.get(Calendar.YEAR)

                  //  Toast.makeText(this@MainActivity, "$DAY / $MONTH / $YEAR", Toast.LENGTH_SHORT).show()
                    val simpelDateFormat = SimpleDateFormat("dd/MM/yyyy")
                    val selectedDate = simpelDateFormat.format(Date(it))
                    binding.dob.setText(selectedDate)
                  //  Toast.makeText(this@MainActivity, "You have selected: $selectedDate", Toast.LENGTH_SHORT).show()


                }

            }



        }
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
