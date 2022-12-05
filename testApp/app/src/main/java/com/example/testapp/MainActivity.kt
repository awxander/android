package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.testapp.Constance.LOGIN_REQUEST_CODE
import com.example.testapp.Constance.REGISTRATION_REQUEST_CODE
import com.example.testapp.Constance.SIGN_IN
import com.example.testapp.Constance.SIGN_STATE
import com.example.testapp.Constance.SIGN_UP
import com.example.testapp.Constance.USER_DATA
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.databinding.ActivityProfileBinding


class MainActivity : AppCompatActivity() {


    private lateinit var bindingClass: ActivityMainBinding
    private lateinit var profileBinding: ActivityProfileBinding
    private var userData : UserData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }


    fun gotoLoginForm(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra(SIGN_STATE, SIGN_IN)
        intent.putExtra(USER_DATA, userData)
        startActivityForResult(intent, LOGIN_REQUEST_CODE)
    }


    fun gotoRegistrationForm(view: View) {
        val intent = Intent(this, LoginActivity::class.java)//intent -  намерение
        intent.putExtra(SIGN_STATE, SIGN_UP)
        startActivityForResult(intent, REGISTRATION_REQUEST_CODE)
    }


    private fun setProfileActivity(userData: UserData?) {
        if (userData != null) {
            profileBinding.nameText.text = "name: ${userData.name}"
            profileBinding.nameText.visibility = View.VISIBLE
            profileBinding.descriptionText.text = "description: ${userData.description}"
            profileBinding.descriptionText.visibility = View.VISIBLE
            profileBinding.emailText.text = "your email: ${userData.email}"
            profileBinding.emailText.visibility = View.VISIBLE
        } else {
            profileBinding.descriptionText.text = "no data, lul, wtf"
        }
    }

    fun exitBtn(view: View){
        setContentView(bindingClass.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_OK -> {
                when (requestCode) {
                    LOGIN_REQUEST_CODE -> {
                        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
                        setContentView(profileBinding.root)
                        setProfileActivity(userData)
                    }
                    REGISTRATION_REQUEST_CODE -> {
                        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
                        setContentView(profileBinding.root)
                        userData = data?.getParcelableExtra(USER_DATA)
                        setProfileActivity(userData)
                    }
                }
            }
            else -> {

            }
        }
    }


}
