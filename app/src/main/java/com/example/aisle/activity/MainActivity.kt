package com.example.aisle.activity

import android.content.Context
import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.aisle.R
import com.example.aisle.api.AisleAPIService
import com.example.aisle.api.AisleRepo
import com.example.aisle.api.RetrofitHelper
import com.example.aisle.data.NodeApiResult
import com.example.aisle.data.NotesResult
import com.example.aisle.databinding.ActivityMainBinding
import com.example.aisle.util.Constants.IS_FIRST_LOGIN
import com.example.aisle.util.Constants.PREFS_NAME
import com.example.aisle.util.Constants.TOKEN_KEY
import com.example.aisle.viewModel.AisleVMFactory
import com.example.aisle.viewModel.AisleViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var aisleViewModel: AisleViewModel
    private var authToken: String? = null
    var nodeApiResult: NodeApiResult? = null
    private lateinit var aisleRepo: AisleRepo
    private lateinit var aisleAPIService: AisleAPIService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        aisleAPIService = RetrofitHelper.getInstance(this).create(AisleAPIService::class.java)
        aisleRepo = AisleRepo(aisleAPIService)
        activityStart()
    }
    private fun activityStart() {
        if (isFirstLogin()) {
            val intent = Intent(this, LoginPhoneNo::class.java)
            startActivity(intent)
            finish()
        } else {
            authToken = getTokenFromLocalStorage(this)
            aisleViewModel =
                ViewModelProvider(
                    this,
                    AisleVMFactory(aisleRepo, authToken!!)
                ).get(AisleViewModel::class.java)
            runBlocking {
                val job = launch {
                    val result = aisleViewModel.getApiResult()
                    if (result.body() != null) {
                        nodeApiResult = result.body()
                    }
                }
                job.join()
            }
            settingUpUi()
        }
    }

    private fun settingUpUi() {
        val notesResult: NotesResult? =
            nodeApiResult?.let {
                NotesResult(
                    it.invites.profiles.get(0).general_information.first_name,
                    it.likes.profiles.get(0).first_name,
                    it.likes.profiles.get(1).first_name,
                    it.invites.profiles.get(0).general_information.age.toString(),
                    if (it.invites.pending_invitations_count > 50) "50+" else it.invites.pending_invitations_count.toString(),
                    if (it.likes.likes_received_count > 50) "50+" else it.likes.likes_received_count.toString()
                )
            }
        binding.result = notesResult
        Glide.with(binding.root.context)
            .load(nodeApiResult?.invites?.profiles?.get(0)?.photos?.get(0)?.photo)
            .into(binding.ivFirst)
        if (nodeApiResult?.likes?.can_see_profile == false) {
            binding.ivSecond.setRenderEffect(
                RenderEffect.createBlurEffect(
                    80.0f, 80.0f, Shader.TileMode.REPEAT
                )
            )
            binding.ivThird.setRenderEffect(
                RenderEffect.createBlurEffect(
                    80.0f, 80.0f, Shader.TileMode.REPEAT
                )
            )
        }
        Glide.with(binding.root.context).load(nodeApiResult?.likes?.profiles?.get(0)?.avatar)
            .into(binding.ivSecond)
        Glide.with(binding.root.context).load(nodeApiResult?.likes?.profiles?.get(1)?.avatar)
            .into(binding.ivThird)
    }

    private fun getTokenFromLocalStorage(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(TOKEN_KEY, null)
    }

    private fun isFirstLogin(): Boolean {
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(IS_FIRST_LOGIN, true)
    }
}