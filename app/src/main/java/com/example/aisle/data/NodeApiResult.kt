package com.example.aisle.data

data class NodeApiResult(
    val invites: Invites,
    val likes: Likes
)
data class PhoneNumberLoginRequest(val number: String)

data class VerifyOTPRequest(val number: String, val otp: String)

data class VerifyOTPResponse(val token: String)