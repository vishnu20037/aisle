package com.example.aisle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.aisle.databinding.ActivityLoginPhoneNoBinding

class LoginPhoneNo : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPhoneNoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_phone_no)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_phone_no)
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, LoginOtp::class.java)
            intent.putExtra(
                Constants.phoneNo,
                binding.etCode.text.toString() + binding.etNumber.text.toString()
            )
            startActivity(intent)
        }
    }
}