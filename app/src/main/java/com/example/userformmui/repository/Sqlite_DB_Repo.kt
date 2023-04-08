package com.example.userformmui.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


private const val DBNAME = "StudentInfo"
private const val DB_VERSION = 1
private const val TABLE_NAME = "Student"
private const val FIRSTNAME = "FirstName"
private const val LASTNAME = "LastName"
private const val PHONE = "Phone"
private const val SRNO = "Sr"

class Sqlite_DB_Repo(private val context: Context) {
    /*
        Query for creating table*/
    val query = "CREATE TABLE $TABLE_NAME($SRNO INTEGER PRIMARY KEY AUTOINCREMENT, $FIRSTNAME TEXT, $LASTNAME TEXT, $PHONE TEXT  )"

    /* Create Object of DBHelper Inner class because of :-  if repo is accessed by ViewModel or Factory here Sqlite Db repo is goes,  not will going inner class ,
       Inner class Functionality should be accessed by outer class , i.e why need of and object
  */
    val dbHelper = MyDBHelper(context) /*Here gives the object of Inner class(Child of open Helper class )
     to Sqlite DB repo because of it have a  method  those facilitate You , you can perform read & Write Operations in DB
      , Basically it have Two methods getReadableDatabase() & getWritableDatabase() and both methods returns Object of SQLITE-DATABASE  */
    val sqliteDb = dbHelper.writableDatabase  /*After writing this method You have permission You Can Perform Read / Write Operations */

    fun createData(fName: String, lName: String, phone: String ) {
        val contentValue = ContentValues()
        contentValue.put(FIRSTNAME, fName)
        contentValue.put(LASTNAME, lName)
        contentValue.put(PHONE, phone)
        val id: Long = sqliteDb.insert(TABLE_NAME, null, contentValue)
        if (id > 0) {
            Toast.makeText(context, "Data Saved Successfully.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
        }
    }

    // Create a inner class :- this is a child of open helper class
    inner class MyDBHelper(private val context: Context)/*Main Work of DB Helper To create & Upgrade */ : SQLiteOpenHelper(context, DBNAME, null, DB_VERSION) {

        override fun onCreate(sqliteDb: SQLiteDatabase?)/*OnCreate Method is Create DB & TABLE & Provide initial value*/ {
            sqliteDb?./*execute a single statement that is not a Select or any other SQL statement that returns data*/execSQL(query)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)/*If perform any changes in database then call onUpgrade */ {
            TODO("Not yet implemented")
        }

    }
}