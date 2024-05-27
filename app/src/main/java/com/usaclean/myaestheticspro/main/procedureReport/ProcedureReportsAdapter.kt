package com.usaclean.myaestheticspro.main.procedureReport

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usaclean.myaestheticspro.databinding.ItemDesignProcedureReportsBinding

class ProcedureReportsAdapter(
    val context: Context,
    val list: ArrayList<String>,
    val onCLick: () -> Unit
) :
    RecyclerView.Adapter<ProcedureReportsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDesignProcedureReportsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            root.setOnClickListener {
                onCLick.invoke()
            }
        }
    }


    inner class ViewHolder(val binding: ItemDesignProcedureReportsBinding) :
        RecyclerView.ViewHolder(binding.root){

        }


}
