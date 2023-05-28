package com.example.aisle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.aisle.databinding.ActivityLoginOtpBinding

class LoginOtp : AppCompatActivity() {
    lateinit var binding: ActivityLoginOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_otp)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_otp)
    }
}