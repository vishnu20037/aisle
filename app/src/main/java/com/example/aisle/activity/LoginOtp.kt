package com.example.aisle.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.databinding.DataBindingUtil
import com.example.aisle.util.Constants
import com.example.aisle.R
import com.example.aisle.api.AisleAPIService
import com.example.aisle.api.AisleRepo
import com.example.aisle.api.AppDelegate
import com.example.aisle.api.RetrofitHelper
import com.example.aisle.data.VerifyOTPRequest
import com.example.aisle.databinding.ActivityLoginOtpBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginOtp : AppCompatActivity() {
    private lateinit var binding: ActivityLoginOtpBinding
    private var authToken: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_otp)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_otp)
        val phoneNo = intent.getStringExtra(Constants.phoneNo)
        phoneNo?.let {
            binding.phoneNo.text = it
        }
        val mobileNo = binding.phoneNo.text.toString()
        val otp = binding.etOtp.text.toString()
        val aisleAPIService = RetrofitHelper.getInstance(this).create(AisleAPIService::class.java)
        val aisleRepo = AisleRepo(aisleAPIService)
        val verifyOTPRequest = VerifyOTPRequest(mobileNo, otp)
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            runBlocking {
                val job = launch {
                    authToken = aisleRepo.verifyOTP(verifyOTPRequest).body()?.token.toString()
                }
                job.join()
            }
            AppDelegate.saveTokenToLocalStorage(this, authToken!!)
            startActivity(intent)
            finish()
        }
        binding.phoneNo.setOnClickListener {
            val intent = Intent(this, LoginPhoneNo::class.java)
            intent.putExtra(Constants.phoneNo, binding.phoneNo.text.toString())
            startActivity(intent)
        }
        val timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding.tvTime.text = "00: $secondsRemaining"
            }
            override fun onFinish() {
            }
        }
        timer.start()

    }
}