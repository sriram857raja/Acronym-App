package com.example.acronymapp.interfaces

import com.example.acronymapp.model.Vars

interface ItemClickListener {
    fun onItemClick(vars: ArrayList<Vars>, lf : String)
}