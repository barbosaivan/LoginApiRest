package com.example.loginapirest

import android.app.Application
import com.example.loginapirest.common.database.ReqResAPI

/* 
* Project: Stores
* From: com.example.loginapirest
* Create by Ivan Barbosa on 3/02/2023 at 1:51 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class LoginApplication : Application() {
    companion object{
        lateinit var reqResAPI: ReqResAPI
    }

    override fun onCreate() {
        super.onCreate()
        //Volley
        reqResAPI = ReqResAPI.getInstance(this)
    }
}