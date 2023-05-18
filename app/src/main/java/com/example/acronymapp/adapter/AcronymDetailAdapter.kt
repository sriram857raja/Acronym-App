package com.example.acronymapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapp.databinding.DetailsListBinding
import com.example.acronymapp.model.Vars

class AcronymDetailAdapter(private val vars: ArrayList<Vars>) :
    RecyclerView.Adapter<AcronymDetailAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = DetailsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.apply {
            bind(vars[position])
        }
    }

    override fun getItemCount(): Int {
        return vars.size
    }

    class DetailViewHolder(private var binding: DetailsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(varsModel: Vars) {
            binding.varsModel = varsModel
        }
    }
}