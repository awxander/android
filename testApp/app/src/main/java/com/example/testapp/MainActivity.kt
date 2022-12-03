package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.testapp.RequestCodes.LOGIN_REQUEST_CODE
import com.example.testapp.RequestCodes.REGISTRATION_REQUEST_CODE
import com.example.testapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var bindingClass: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }


    fun gotoLoginForm(view : View){
        val intent  = Intent(this, LoginActivity :: class.java)
        intent.putExtra("operation", "login")
        startActivityForResult(intent,LOGIN_REQUEST_CODE )
    }




    fun gotoRegistrationForm(view : View){
        val intent  = Intent(this, LoginActivity :: class.java)
        intent.putExtra("operation", "registration")
        startActivityForResult(intent, REGISTRATION_REQUEST_CODE)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            RESULT_OK ->{
                when(requestCode){
                    LOGIN_REQUEST_CODE -> {

                    }
                    REGISTRATION_REQUEST_CODE -> {

                    }
                }
            }
            else -> {

            }
        }
    }


}
