package com.usaclean.myaestheticspro.main.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.usaclean.myaestheticspro.DayName
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.ActivityHomeBinding
import com.usaclean.myaestheticspro.databinding.FragmentProcedureReportBinding
import com.usaclean.myaestheticspro.databinding.FragmentTodoListBinding
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Locale

class TodoListFragment : Fragment() {

    private val daysNameList = ArrayList<DayName>()
    private var dayPosition = 0

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoListBinding.inflate(inflater)
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ToDoListAdapter(requireContext(), arrayListOf())
            }
            setCalenderView()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setCalenderView() {
        val calender: Calendar = Calendar.getInstance().clone() as Calendar
        val currentDay = calender.get(Calendar.DAY_OF_MONTH)
        val maxDays = calender.getActualMaximum(Calendar.DAY_OF_MONTH)
        val mDay = calender.get(Calendar.DAY_OF_MONTH)
        val currentMonthYear = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(calender.time)
        binding.textMonth.text = currentMonthYear

        for (day in 1..maxDays) {
            calender.set(Calendar.DAY_OF_MONTH, day)
            val dayName = SimpleDateFormat("EEE", Locale.US).format(calender.time)
            daysNameList.add(DayName(dayName = dayName, dayNumber = day.toString()))
        }
        calender.set(Calendar.DAY_OF_MONTH, mDay)

        if (currentDay > 28) {
            daysNameList[0].isSelected = true
            dayPosition = 0
        } else {
            for (i in 1..daysNameList.size) {
                if (i == currentDay) {
                    daysNameList[i - 1].isSelected = true
                    dayPosition = i - 1
                }
            }
        }
        binding.monthRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.monthRV.adapter = CalendarAdapter(requireContext(), daysNameList)
        (binding.monthRV.layoutManager as LinearLayoutManager).scrollToPosition(20)
    }
}