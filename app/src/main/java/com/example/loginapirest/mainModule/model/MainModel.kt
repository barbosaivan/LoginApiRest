package com.example.loginapirest.mainModule.model

import android.util.Log
import com.android.volley.toolbox.JsonObjectRequest
import com.example.loginapirest.common.utils.Constants
import com.example.loginapirest.LoginApplication
import org.json.JSONObject

/* 
* Project: Login API REST
* From: com.example.loginapirest.mainModule.model
* Create by Ivan Barbosa on 4/04/2023 at 6:30 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*
* */

class MainModel() {


    fun login(isCheck: Boolean, email: String, pass: String, callback: (String) -> Unit) {

        val typeMethod = if (isCheck) Constants.API_PATH_LOGIN else Constants.API_PATH_REGISTER
        val url = Constants.BASE_URL + typeMethod
        val jsonParams = JSONObject()

        if (email.isNotEmpty()) {
            jsonParams.put(Constants.EMAIL_PARAM, email)
        }
        if (pass.isNotEmpty()) {
            jsonParams.put(Constants.PASSWORD_PARAM, pass)
        }

        val jsonObjectRequest =
            object : JsonObjectRequest(Method.POST, url, jsonParams, { response ->
                Log.i("Response", response.toString())

                val id = response.optString(Constants.ID_PROPERTY, Constants.ERROR_VALUE)
                val token = response.optString(Constants.TOKEN_PROPERTY, Constants.ERROR_VALUE)

                val result =
                    if (id.equals(Constants.ERROR_VALUE)) "${Constants.TOKEN_PROPERTY}: $token"
                    else "${Constants.ID_PROPERTY}: $id, ${Constants.TOKEN_PROPERTY}: $token"
                callback(result)

            }, {
                it.printStackTrace()
                try {
                    if (it.networkResponse.statusCode == 400) {
                        callback(Constants.ERROR_SERVER)
                    }
                } catch (e: NullPointerException) {
                    callback(Constants.ERROR_INTERNET)
                }
            }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["Content-Type"] = "application/json"
                    return params
                }
            }

        LoginApplication.reqResAPI.addToRequestQueue(jsonObjectRequest)

    }
}