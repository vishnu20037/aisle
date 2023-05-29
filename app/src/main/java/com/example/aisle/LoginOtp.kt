package com.example.aisle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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