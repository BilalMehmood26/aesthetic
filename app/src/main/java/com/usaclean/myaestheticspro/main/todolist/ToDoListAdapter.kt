package com.usaclean.myaestheticspro.main.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usaclean.myaestheticspro.databinding.ItemDesignTodoListBinding

class ToDoListAdapter (val context : Context, val list: ArrayList<String>) :
    RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDesignTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }


    inner class ViewHolder(val binding : ItemDesignTodoListBinding) : RecyclerView.ViewHolder(binding.root)


}
