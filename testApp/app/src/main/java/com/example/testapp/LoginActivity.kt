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
                loginBinding.button2.setOnClickListener(::signInBtn)
            }
            "registration" -> {
                regBinding = ActivityRegistrationBinding.inflate(layoutInflater)
                setContentView(regBinding.root)
                regBinding.button.setOnClickListener(::finishRegBtn)
            }
            else -> {
                Log.e("LoginAct", "wrong stringExtra")
                throw FailedRegistrationException("no extra string with name:" +
                        " ${intent.getStringExtra("operation")}")
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

    private fun finishRegBtn(view: View) {
        try {
            val userData = getData()
            intent.putExtra("userdata", userData)
            setResult(RESULT_OK, intent)
            finish()
        } catch (e: FailedRegistrationException) {
            Log.e("LoginActLog", "registration failed", e)
        }
    }

    private fun signInBtn(view: View){
        val userData = intent.getParcelableExtra<UserData>("userData")
        if(userData == null || userData!!.email != loginBinding.editEmail.text.toString() ||
                userData!!.password != loginBinding.editTextTextPassword.text.toString())
        {
            loginBinding.errorText.visibility = View.VISIBLE
            loginBinding.errorText.text = "wrong password or email"
        }else{
            setResult(RESULT_OK, intent)
            finish()
        }
    }





}