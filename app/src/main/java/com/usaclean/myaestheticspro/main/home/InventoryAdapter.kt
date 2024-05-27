package com.usaclean.myaestheticspro.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.usaclean.myaestheticspro.InventoryModel
import com.usaclean.myaestheticspro.R
import com.usaclean.myaestheticspro.databinding.ItemInventoryBinding

class InventoryAdapter(
    val context: Context,
    val list: ArrayList<InventoryModel>,
    val onCLick: () -> Unit
) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemInventoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            root.setOnClickListener {
                onCLick.invoke()
            }

            holder.binding.apply {
                prodIv.load(item.image)
                medName.text = item.name
                formulaName.text = item.formulaName

            }
        }
    }


    inner class ViewHolder(val binding: ItemInventoryBinding) :
        RecyclerView.ViewHolder(binding.root){

    }


}
