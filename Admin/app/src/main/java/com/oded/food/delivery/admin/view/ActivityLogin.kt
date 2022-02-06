package com.oded.food.delivery.admin.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.oded.food.delivery.admin.R
import com.oded.food.delivery.admin.common.Logger
import com.oded.food.delivery.admin.databinding.ActivityLoginBinding

class ActivityLogin : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        fun intent(context: Context) : Intent {
            return Intent(context, ActivityLogin::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editUserName.setText("admin@marketoded.com")
        binding.editPassword.setText("MarketOded1**")
        binding.btnLogin.setOnClickListener { onLogin() }
    }

    private fun onLogin() {

        binding.layoutEditUsername.error = ""
        binding.layoutEditPassword.error = ""

        if (binding.editUserName.text.isNullOrEmpty()) {
            binding.layoutEditUsername.error = getString(R.string.username_empty)
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.editUserName.text).matches()) {
            binding.layoutEditUsername.error = getString(R.string.inavlid_email)
            return
        }


        if (binding.editPassword.text.isNullOrEmpty()) {
            binding.layoutEditPassword.error = getString(R.string.password_empty)
            return
        }

        val username = binding.editUserName.text.toString()
        val password = binding.editPassword.text.toString()

        showProgressBar(getString(R.string.please_login))
        auth?.signInWithEmailAndPassword(username, password)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Logger.e("signInWithEmail:success")
                    dismissProgressBar()
                    next()
                } else {
                    dismissProgressBar()
                    Logger.e("signInWithEmail:failure ${task.exception}", )
                    Toast.makeText(baseContext, getString(R.string.auth_failed),
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun next() {
        startActivity(MainActivity.intent(this@ActivityLogin))
        finish()
    }
}