package com.example.aisle.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aisle.api.AisleRepo

class AisleVMFactory(private val aisleRepo: AisleRepo, private val authToken: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AisleViewModel(aisleRepo, authToken) as T
    }
}