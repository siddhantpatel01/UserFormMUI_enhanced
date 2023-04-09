package com.example.userformmui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.userformmui.Model.Student_Info
import com.example.userformmui.R
import com.example.userformmui.databinding.LayoutitemsBinding

/*BaseAdapter is a very generic adapter that allows you to do pretty much whatever you want. However,
you have to do a bit more coding yourself to get it working.
ArrayAdapter is a more complete implementation that works well for data in arrays or ArrayLists.
 Similarly, there is a related CursorAdapter that you should use if your data is in a Cursor.
Both of these extend BaseAdapter.*/
class ListViewAdapter(private val ListofStudent: ArrayList<Student_Info>) : BaseAdapter() {
    override fun getCount(): Int {
        return ListofStudent.size
    }

    override fun getItem(position: Int): Any {
        return ListofStudent[position]
    }

    override fun getItemId(position: Int): Long {
        return ListofStudent[position].hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        view = LayoutInflater.from(parent!!.context).inflate(R.layout.layoutitems, parent, false)
        val binding = DataBindingUtil.bind<LayoutitemsBinding>(view)
        binding!!.fname.text = ListofStudent[position].FirstNamee
        binding!!.Lname.text = ListofStudent[position].LastName
        binding!!.Phone.text = ListofStudent[position].phone
        return view

    }

}