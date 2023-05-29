package com.example.aisle.api

import com.example.aisle.data.NodeApiResult
import com.example.aisle.data.PhoneNumberLoginRequest
import com.example.aisle.data.VerifyOTPRequest
import com.example.aisle.data.VerifyOTPResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AisleAPIService {
    @POST("users/phone_number_login")
    suspend fun phoneNumberLogin(@Body requestBody: PhoneNumberLoginRequest)

    @POST("users/verify_otp")
    suspend fun verifyOTP(@Body requestBody: VerifyOTPRequest): Response<VerifyOTPResponse>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("users/test_profile_list")
    suspend fun getApiResult(@Header("Authorization") authToken: String): Response<NodeApiResult>

}