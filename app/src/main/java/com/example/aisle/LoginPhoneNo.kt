package com.example.aisle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.aisle.databinding.ActivityLoginPhoneNoBinding

class LoginPhoneNo : AppCompatActivity() {
    lateinit var binding: ActivityLoginPhoneNoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_phone_no)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_phone_no)

    }
}