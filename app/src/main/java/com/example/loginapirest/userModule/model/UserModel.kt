package com.example.loginapirest.userModule.model

import android.net.Uri
import com.android.volley.toolbox.JsonObjectRequest
import com.example.loginapirest.R
import com.example.loginapirest.common.entities.Users
import com.example.loginapirest.common.utils.Constants
import com.example.loginapirest.mainModule.viewModel.LoginApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/* Project: Login API REST
* From: com.example.loginapirest.mainModule.model
* Create by Ivan Barbosa on 4/04/2023 at 6:30 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class UserModel {

    fun requestApi(page: Int, callback: (MutableList<Users>, Int, Boolean) -> Unit) {
        val urlBuilder = Uri.parse("${Constants.BASE_URL}${Constants.API_PATH_USERS}").buildUpon()
        urlBuilder.appendQueryParameter(Constants.PAGE_USER, page.toString())
        val url = urlBuilder.build().toString()
        var listUsers: MutableList<Users> = mutableListOf()

        val jsonObjectRequest = object : JsonObjectRequest(Method.GET, url, null, { response ->
            val jsonList = response.getJSONArray("data").toString()
            val data = object : TypeToken<MutableList<Users>>(){}.type
            listUsers = Gson().fromJson(jsonList, data)
            if (listUsers.isNotEmpty()){
                callback(listUsers, R.string.main_success, true)
            }
        },{
            try {
                it.printStackTrace()
                if (it.networkResponse.statusCode == 400){
                    callback(listUsers, R.string.main_error_server, true)
                }
            }catch (e: NullPointerException){
                callback(listUsers, R.string.main_error_internet,true)
            }
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["Content-Type"] = "application/json"
                return params
            }
        }
        LoginApplication.reqResAPI.requestQueue.cache.clear()
        LoginApplication.reqResAPI.addToRequestQueue(jsonObjectRequest)
    }

}