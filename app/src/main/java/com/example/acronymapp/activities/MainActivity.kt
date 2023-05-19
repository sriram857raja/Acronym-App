package com.example.acronymapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.acronymapp.R
import com.example.acronymapp.adapter.AcronymAdapter
import com.example.acronymapp.databinding.AcronymActivityBinding
import com.example.acronymapp.interfaces.ItemClickListener
import com.example.acronymapp.model.Lfs
import com.example.acronymapp.model.Vars
import com.example.acronymapp.utils.CommonUtils
import com.example.acronymapp.viewmodel.AcronymViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: AcronymActivityBinding
    private lateinit var acronymViewModel: AcronymViewModel
    private lateinit var acronymAdapter: AcronymAdapter
    private val lfsList = ArrayList<Lfs>()
    private lateinit var mContext: Context


    companion object CompanionObject {
        var varsList = ArrayList<Vars>()
        var selectedLf: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.acronym_activity)
        acronymViewModel = ViewModelProvider(this)[AcronymViewModel::class.java]
        mContext = this.applicationContext

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (isValidText(it.toString())) {
                        getInfo(binding.editText.text.toString().trim())
                    }
                }
            }
        })

        setObservers()

        acronymAdapter = AcronymAdapter(lfsList, "", null)

    }

    private fun setObservers() {
        acronymViewModel.acronymResponse.observe(this) {
            if (it != null && it.lfs.isNotEmpty()) {
                acronymAdapter =
                    AcronymAdapter(it.lfs, it.sf.toString(), object : ItemClickListener {
                        override fun onItemClick(vars: ArrayList<Vars>, lf: String) {
                            if (vars.isNotEmpty()) {
                                selectedLf = lf
                                varsList = vars
                                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                                startActivity(intent)

                            } else {
                                CommonUtils.showToast(mContext, getString(R.string.no_records))
                            }
                        }
                    })

                binding.recyclerview.adapter = acronymAdapter
            } else {
                CommonUtils.showToast(mContext, getString(R.string.no_records))
            }
        }

        acronymViewModel.errorMessage.observe(this) {
            if (this::acronymAdapter.isInitialized) {
                acronymAdapter.clearList()
            }
            CommonUtils.showToast(mContext, it)
        }

        acronymViewModel.progressBar.observe(this) {
            binding.progressBar = it
        }
    }

    private fun getInfo(info: String) {
        if (CommonUtils.isOnline(mContext)) {
            acronymViewModel.getAcronymData(mContext, info, "")
        } else {
            CommonUtils.showToast(mContext, getString(R.string.network_error))
        }
    }


    fun isValidText(text: String): Boolean {
        if (text.length >= 3) {
            return true
        }
        return false
    }


}