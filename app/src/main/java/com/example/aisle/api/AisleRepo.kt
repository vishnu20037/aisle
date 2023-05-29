package com.example.aisle.api

import com.example.aisle.data.NodeApiResult
import com.example.aisle.data.PhoneNumberLoginRequest
import com.example.aisle.data.VerifyOTPRequest
import com.example.aisle.data.VerifyOTPResponse
import retrofit2.Response

class AisleRepo(private val aisleAPIService: AisleAPIService) {
    suspend fun getApiResult(authToken: String): Response<NodeApiResult> {
        return aisleAPIService.getApiResult(authToken)
    }

    suspend fun phoneNumberLogin(requestBody: PhoneNumberLoginRequest) {
        return aisleAPIService.phoneNumberLogin(requestBody)
    }

    suspend fun verifyOTP(requestBody: VerifyOTPRequest): Response<VerifyOTPResponse> {
        return aisleAPIService.verifyOTP(requestBody)
    }


}