package com.example.loginapirest.common.database

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/* 
* Project: Stores
* From: com.example.stores.loginapirest.dataBase
* Create by Ivan Barbosa on 14/03/2023 at 2:07 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class ReqResAPI constructor(context: Context) {

    companion object{
        @Volatile
        private var INSTANCE: ReqResAPI? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this){
            INSTANCE ?: ReqResAPI(context).also { INSTANCE = it }
        }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun<T> addToRequestQueue(req: Request<T>){
        requestQueue.add(req)
    }

}