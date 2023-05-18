package com.example.acronymapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.acronymapp.R
import com.example.acronymapp.adapter.AcronymDetailAdapter
import com.example.acronymapp.databinding.AcronymDetailsBinding


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: AcronymDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.acronym_details)

        val obj = MainActivity.CompanionObject
        val detailsAdapter = AcronymDetailAdapter(obj.varsList)
        binding.selectedItem = obj.selectedLf
        binding.recyclerview.adapter = detailsAdapter

    }
}