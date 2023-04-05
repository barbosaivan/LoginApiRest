package com.example.loginapirest.common.entities

/*
* Project: Login API REST
* From: com.example.loginapirest.mainModule.model
* Create by Ivan Barbosa on 4/04/2023 at 6:30 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

data class Users(val id: Int,
                 val email: String,
                 val first_name: String,
                 val last_name: String,
                 val avatar: String
)
