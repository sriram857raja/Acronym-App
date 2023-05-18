package com.example.acronymapp.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapp.databinding.AcronymListBinding
import com.example.acronymapp.interfaces.ItemClickListener
import com.example.acronymapp.model.Lfs

class AcronymAdapter(
    private val lfsModel: ArrayList<Lfs>,
    private val sf: String,
    private val listener: ItemClickListener?
) :
    RecyclerView.Adapter<AcronymAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = AcronymListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.apply {
            listener?.let { bind(lfsModel[position], sf, it) }
        }
    }

    override fun getItemCount(): Int {
        return lfsModel.size
    }

    class MainViewHolder(private var binding: AcronymListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(lfsModel: Lfs, sf: String, listener: ItemClickListener) {
            binding.definitionText = lfsModel
            binding.acronymText = sf
            if (bindingAdapterPosition % 2 == 0) {
                binding.totalLayout.setBackgroundColor(Color.parseColor("#bac4bd"))
            } else {
                binding.totalLayout.setBackgroundColor(Color.parseColor("#f1f3f2"))
            }

            binding.root.setOnClickListener {
                listener.onItemClick(lfsModel.vars, lfsModel.lf.toString())
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearList() {
        if (lfsModel.isNotEmpty()) {
            lfsModel.clear()
            notifyDataSetChanged()
        }
    }
}