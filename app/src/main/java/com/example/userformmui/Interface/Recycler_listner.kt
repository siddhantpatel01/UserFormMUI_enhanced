package com.example.userformmui.Interface

import com.example.userformmui.Model.Student_Info

interface Recycler_listner {
    fun onItemClick(position: Int, student: Student_Info)
}
