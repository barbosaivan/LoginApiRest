package com.example.loginapirest.mainModule.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.android.volley.toolbox.JsonObjectRequest
import com.example.loginapirest.R
import com.example.loginapirest.userModule.view.UsersActivity
import com.example.loginapirest.common.utils.Constants
import com.example.loginapirest.databinding.ActivityMainBinding
import com.example.loginapirest.mainModule.viewModel.LoginApplication
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.switchType.setOnCheckedChangeListener { button, checked ->
            button.text =
                if (checked) getString(R.string.main_type_login) else getString(R.string.main_type_register)
            mBinding.btnLogin.text = button.text
        }

        mBinding.btnLogin.setOnClickListener {
            login()
        }

        mBinding.btnInto.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(){

        val typeMethod = if (mBinding.switchType.isChecked) Constants.API_PATH_LOGIN else Constants.API_PATH_REGISTER
        val url = Constants.BASE_URL + typeMethod

        val email = mBinding.etEmail.text.toString().trim()
        val password = mBinding.etPassword.text.toString().trim()
        val jsonParams = JSONObject()

        if (email.isNotEmpty()){
            jsonParams.put(Constants.EMAIL_PARAM, email)
        }
        if (password.isNotEmpty()){
            jsonParams.put(Constants.PASSWORD_PARAM, password)
        }

        val jsonObjectRequest = object : JsonObjectRequest(Method.POST, url, jsonParams,{response ->
            Log.i("Response", response.toString())

            val id = response.optString(Constants.ID_PROPERTY, Constants.ERROR_VALUE)
            val token = response.optString(Constants.TOKEN_PROPERTY, Constants.ERROR_VALUE)

            val  result= if (id.equals(Constants.ERROR_VALUE)) "${Constants.TOKEN_PROPERTY}: $token"
            else "${Constants.ID_PROPERTY}: $id, ${Constants.TOKEN_PROPERTY}: $token"

            updateUI(result)
            mBinding.btnInto.visibility = View.VISIBLE

        },{
            it.printStackTrace()
            try {
                if (it.networkResponse.statusCode == 400){
                    updateUI(getString(R.string.main_error_server))
                    mBinding.btnInto.visibility = View.GONE
                }
            }catch (e: NullPointerException){
                updateUI(getString(R.string.main_error_internet))
                mBinding.btnInto.visibility = View.GONE
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

    private fun updateUI(result: String) {
        mBinding.tvResult.visibility = View.VISIBLE
        mBinding.tvResult.text = result
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mBinding.root.windowToken, 0)
    }

}