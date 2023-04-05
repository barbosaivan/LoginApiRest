package com.example.loginapirest.userModule.view

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.example.loginapirest.R
import com.example.loginapirest.common.entities.Users
import com.example.loginapirest.common.utils.Constants
import com.example.loginapirest.databinding.ActivityUsersBinding
import com.example.loginapirest.mainModule.viewModel.LoginApplication
import com.example.loginapirest.userModule.view.adapters.UserAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/* Project: Login API REST
* From: com.example.loginapirest.mainModule.model
* Create by Ivan Barbosa on 4/04/2023 at 6:30 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding
    private lateinit var userAdapter: UserAdapter
    private var page = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestApi()
        upRecycler()
    }

    private fun requestApi() {
        val urlBuilder = Uri.parse("${Constants.BASE_URL}${Constants.API_PATH_USERS}").buildUpon()
        urlBuilder.appendQueryParameter(Constants.PAGE_USER, page.toString())
        val url = urlBuilder.build().toString()

        val jsonObjectRequest = object : JsonObjectRequest(Method.GET, url, null, { response ->
            val listUsers: MutableList<Users>
            val jsonList = response.getJSONArray("data").toString()
            val data = object : TypeToken<MutableList<Users>>(){}.type
            listUsers = Gson().fromJson(jsonList, data)
            userAdapter.setUsers(listUsers)
        },{
            it.printStackTrace()
            if (it.networkResponse.statusCode == 400){
                Snackbar.make(binding.root, R.string.main_error_server, Snackbar.LENGTH_LONG).show()
            }
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["Content-Type"] = "application/json"
                return params
            }
        }

        LoginApplication.reqResAPI.addToRequestQueue(jsonObjectRequest)
    }

    private fun upRecycler() {
        userAdapter = UserAdapter(mutableListOf())
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@UsersActivity)
            adapter = this@UsersActivity.userAdapter
        }
    }
}