package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.testapp.databinding.ActivityLoginBinding
import com.example.testapp.databinding.ActivityRegistrationBinding
import com.example.testapp.exceptions.FailedRegistrationException

class LoginActivity : AppCompatActivity() {

    lateinit var regBinding: ActivityRegistrationBinding
    lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (intent.getStringExtra("operation")) {
            "login" -> {
                loginBinding = ActivityLoginBinding.inflate(layoutInflater)
                setContentView(loginBinding.root)
            }
            "registration" -> {
                regBinding = ActivityRegistrationBinding.inflate(layoutInflater)
                setContentView(regBinding.root)
                regBinding.button.setOnClickListener(::registerBtn)
            }
        }
    }

    private fun regFailed(message: String?): Nothing {
        regBinding.errorMessageView.text = message
        regBinding.errorMessageView.visibility = View.VISIBLE
        throw FailedRegistrationException(message)
    }

    private fun getData(): UserData {
        var errorMess: String

        val name = regBinding.editName.text.toString()
        val email = regBinding.editEmail.text.toString()
        val password = regBinding.editPassword.text.toString()

        val description = regBinding.editDescription.text.toString()
        val checkPassword = regBinding.checkPassword.text.toString()

        if (password != checkPassword) {
            errorMess = "password and reentered password doesn't match"
            regFailed(errorMess)
        }
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errorMess = "one of necessary fields is empty"
            regFailed(errorMess)
        }
        return UserData(name, email, password, description)
    }

    private fun registerBtn(view: View) {
        try {
            val userData = getData()
            intent.putExtra("userdata", userData)
            setResult(RESULT_OK)
            finish()
        } catch (e: FailedRegistrationException) {
            Log.e("LoginActLog", "registration failed", e)
        }
    }


}