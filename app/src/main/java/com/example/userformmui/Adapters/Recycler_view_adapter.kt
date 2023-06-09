package com.example.userformmui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.userformmui.Interface.Recycler_listner
import com.example.userformmui.Model.Student_Info
import com.example.userformmui.R
import com.example.userformmui.databinding.LayoutitemsBinding

class Recycler_view_adapter (private val listOfStudent: List<Student_Info>, private val context: Context) : RecyclerView.Adapter<Recycler_view_adapter.MyViewHolder>() {

    private lateinit var onItemClickListener: Recycler_listner
    inner class MyViewHolder(var binding: LayoutitemsBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Recycler_view_adapter.MyViewHolder {
        var view: View? = LayoutInflater.from(parent.context).inflate(R.layout.layoutitems, parent, false)
        var binding: LayoutitemsBinding = DataBindingUtil.bind(view!!)!!
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: Recycler_view_adapter.MyViewHolder, position: Int) {
        val student = listOfStudent[position]
        holder.binding.apply {
            this.fname.text = student.FirstNamee
            this.Lname.text = student.LastName
            this.Phone.text = student.phone
            holder.itemView.setOnClickListener {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, listOfStudent[position])
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return listOfStudent.size
    }

    fun setOnItemClickListener(onItemClickListener:Recycler_listner ) {
        this.onItemClickListener = onItemClickListener
    }
}