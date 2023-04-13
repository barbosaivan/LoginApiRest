package com.example.loginapirest.userModule.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapirest.common.entities.Users
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

    private var page = 0
    private var load = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        setUpViewModel()
        upRecycler()
        nextPage()
    }

        private fun setUpViewModel() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getUsers().observe(this) {users->
            userAdapter.setUsers(users)
            page += 1
        }
        userViewModel.getSnackbarMsg().observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        userViewModel.getNextLoad().observe(this){
            load = it
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

    private fun nextPage() {
        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                val currentPosition = layoutManager.findFirstVisibleItemPosition()
                if (load && lastVisibleItemPosition == totalItemCount - 1 && currentPosition >= 0) {
                    val usersLiveData = userViewModel.getNextUsers(page)
                    val usersObserver = Observer<MutableList<Users>> { users ->
                        userAdapter.setAddUsers(users)
                    }
                    usersLiveData.observe(this@UsersActivity, usersObserver)
                    usersLiveData.removeObserver(usersObserver)
                    page += 1
                    load = false
                }
            }
        })
    }
}