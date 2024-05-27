package com.usaclean.myaestheticspro.main.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usaclean.myaestheticspro.DayName
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.ItemDesignCalenderCellBinding

class CalendarAdapter(
    val context: Context,
    val list: List<DayName>
) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDesignCalenderCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val daysItem = list[position]
        holder.binding.apply {
            dayTV.text = daysItem.dayName
            dateTV.text = daysItem.dayNumber
        }
        Log.d("LOGGER", "fORMaTE: $daysItem.")
        if (position == 23) {
            holder.binding.dateTV.setBackgroundResource(R.drawable.selected_calander_bg)
        } else {
            holder.binding.dateTV.setBackgroundResource(0)
        }


    }

    inner class ViewHolder(val binding: ItemDesignCalenderCellBinding) :
        RecyclerView.ViewHolder(binding.root)
}
