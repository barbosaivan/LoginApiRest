package com.example.loginapirest.userModule.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapirest.databinding.ActivityUsersBinding
import com.example.loginapirest.userModule.view.adapters.UserAdapter
import com.example.loginapirest.userModule.viewModel.UserViewModel
import com.google.android.material.snackbar.Snackbar

/* Project: Login API REST
* From: com.example.loginapirest.mainModule.model
* Create by Ivan Barbosa on 4/04/2023 at 6:30 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        upRecycler()
    }

        private fun setUpViewModel() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getUsers().observe(this) {users->
            userAdapter.setUsers(users)
        }
        userViewModel.getSnackbarMsg().observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
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