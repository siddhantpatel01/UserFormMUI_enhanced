package com.example.userformmui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userformmui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener,
    CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: ActivityMainBinding
    val list: ArrayList<String> = ArrayList()
    var gender: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This peace of code for Submit Button
        binding.submit.setOnClickListener(this)

        // This peace of code for Radio Button
        binding.radioButton.setOnCheckedChangeListener(this)

        // This peace of code for Check Box
        binding.Coding.setOnCheckedChangeListener(this)
        binding.readingBook.setOnCheckedChangeListener(this)
        binding.movies.setOnCheckedChangeListener(this)
        binding.Playing.setOnCheckedChangeListener(this)
        binding.traveling.setOnCheckedChangeListener(this)
    }

    override fun onClick(view: View) {


        when (view?.id) {

            R.id.submit -> {
                val phone1 = binding.Phone1.editableText.toString()
                val phone2 = binding.Phone2.editableText.toString()

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
                } else if (binding.Phone2.editableText.isEmpty()) {
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

                }
                else  {

                    val intent = Intent(this, UserInformation::class.java)
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

                    startActivity(intent)

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

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.Coding -> {
                if (binding.Coding.isChecked) {
                    list.add(binding.Coding.text.toString())
                    // list.toString().replace("[", "").replace("]", "");
                } else {
                    list.remove(binding.Coding.text.toString())
                }

            }
            R.id.readingBook -> {
                if (binding.readingBook.isChecked) {
                    list.add(binding.readingBook.text.toString())

                } else {
                    list.remove(binding.readingBook.text.toString())
                }

            }
            R.id.movies -> {
                if (binding.movies.isChecked) {
                    list.add(binding.movies.text.toString())
                    // list.toString().replace("[", "").replace("]", "");
                } else {
                    list.remove(binding.movies.text.toString())
                }

            }
            R.id.Playing -> {
                if (binding.Playing.isChecked) {
                    list.add(binding.Playing.text.toString())
                    // list.toString().replace("[", "").replace("]", "");
                } else {
                    list.remove(binding.Playing.text.toString())
                }

            }
            R.id.traveling -> {
                if (binding.traveling.isChecked) {
                    list.add(binding.traveling.text.toString())
                    // list.toString().replace("[", "").replace("]", "");
                } else {
                    list.remove(binding.traveling.text.toString())
                }

            }
        }
    }
}
