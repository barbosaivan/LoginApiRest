package com.example.loginapirest.userModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginapirest.common.entities.Users
import com.example.loginapirest.userModule.model.UserModel

/* Project: Login API REST
* From: com.example.loginapirest.mainModule.model
* Create by Ivan Barbosa on 4/04/2023 at 6:30 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class UserViewModel: ViewModel() {
    private var userList: MutableList<Users>
    private var userModel: UserModel
    private val snackbarMsg = MutableLiveData<Int>()

    init {
        userList = mutableListOf()
        userModel = UserModel()
    }

    fun getSnackbarMsg() = snackbarMsg

    private val users: MutableLiveData<MutableList<Users>> by lazy{
        MutableLiveData<MutableList<Users>>()
    }

    fun getUsers(): LiveData<MutableList<Users>>{
        return users.also { loadUsers() }
    }

    private fun loadUsers() {
            userModel.requestApi(1){store, e ->
                users.value = store
                userList = store
                snackbarMsg.value = e
            }
    }

}