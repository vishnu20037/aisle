package com.example.aisle.viewModel

import androidx.lifecycle.ViewModel
import com.example.aisle.api.AisleRepo
import com.example.aisle.data.NodeApiResult
import retrofit2.Response

class AisleViewModel(private val aisleRepo: AisleRepo, private val authToken: String) :
    ViewModel() {

    suspend fun getApiResult(): Response<NodeApiResult> {
        return aisleRepo.getApiResult(authToken)
    }
}