package com.example.loginapirest.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginapirest.mainModule.model.MainModel

/* 
* Project: Login API REST
* From: com.example.loginapirest.mainModule.viewModel
* Create by Ivan Barbosa on 15/04/2023 at 4:17 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class MainViewModel() : ViewModel() {
    private var mainModel: MainModel = MainModel()
    private var msgResult: String

    init {
        mainModel = MainModel()
        msgResult = ""
    }

    private val results: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getResults(isCheck: Boolean, email: String, pass: String): LiveData<String> {
        return results.also { load(isCheck, email, pass) }
    }

    private fun load(isCheck: Boolean, email: String, pass: String) {
        mainModel.login(isCheck, email, pass) {
            msgResult = it
            results.value = it
        }
    }
}