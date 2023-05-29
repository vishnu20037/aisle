package com.example.aisle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.aisle.databinding.ActivityLoginOtpBinding

class LoginOtp : AppCompatActivity() {
    private lateinit var binding: ActivityLoginOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_otp)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_otp)
        val phoneNo = intent.getStringExtra(Constants.phoneNo)
        phoneNo?.let {
            binding.phoneNo.text = it
        }
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}