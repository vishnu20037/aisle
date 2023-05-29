package com.example.aisle.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.databinding.DataBindingUtil
import com.example.aisle.util.Constants
import com.example.aisle.R
import com.example.aisle.api.AisleAPIService
import com.example.aisle.api.AisleRepo
import com.example.aisle.api.AppDelegate
import com.example.aisle.api.RetrofitHelper
import com.example.aisle.data.PhoneNumberLoginRequest
import com.example.aisle.databinding.ActivityLoginPhoneNoBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginPhoneNo : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPhoneNoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_phone_no)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_phone_no)
        val phone = intent.getStringExtra(Constants.phoneNo)
        if (phone != null) {
            binding.etCode.text = Editable.Factory.getInstance().newEditable(phone.substring(0, 3))
            binding.etCode.isEnabled = true
            binding.etNumber.text = Editable.Factory.getInstance().newEditable(phone.substring(3))
            binding.etNumber.isEnabled = true
        }
        val phoneNo = binding.etCode.text.toString() + binding.etNumber.text.toString()
        val aisleAPIService = RetrofitHelper.getInstance(this).create(AisleAPIService::class.java)
        val aisleRepo = AisleRepo(aisleAPIService)
        val phoneNumberLoginRequest = PhoneNumberLoginRequest(phoneNo)
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, LoginOtp::class.java)
            intent.putExtra(
                Constants.phoneNo,
                phoneNo
            )
            AppDelegate.saveFirstLoginFlag(this, false)
            runBlocking {
                val job = launch {
                    aisleRepo.phoneNumberLogin(phoneNumberLoginRequest)
                }
                job.join()
            }
            startActivity(intent)
            finish()
        }
    }
}