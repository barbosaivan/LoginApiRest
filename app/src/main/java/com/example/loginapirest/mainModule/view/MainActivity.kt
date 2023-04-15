package com.example.loginapirest.mainModule.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.example.loginapirest.R
import com.example.loginapirest.common.utils.Constants
import com.example.loginapirest.userModule.view.UsersActivity
import com.example.loginapirest.databinding.ActivityMainBinding
import com.example.loginapirest.mainModule.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var checked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpListener()
    }

    private fun setUpListener() {
        mBinding.switchType.setOnCheckedChangeListener { button, checked ->
            button.text =
                if (checked) getString(R.string.main_type_login) else getString(R.string.main_type_register)
            mBinding.btnLogin.text = button.text
            this.checked = checked
        }

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mBinding.btnLogin.setOnClickListener {
            mainViewModel.getResults(
                checked,
                mBinding.etEmail.text.toString(),
                mBinding.etPassword.text.toString()
            ).observe(this) {
                updateUI(it)
            }
        }

        mBinding.btnInto.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateUI(result: String) {
        mBinding.tvResult.visibility = View.VISIBLE
        mBinding.tvResult.text = result
        if (result != Constants.ERROR_INTERNET && result != Constants.ERROR_SERVER && result.isNotEmpty()) {
            mBinding.btnInto.visibility = View.VISIBLE
        }
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mBinding.root.windowToken, 0)
    }


}