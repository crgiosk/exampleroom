package com.example.ingresoandroid.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ingresoandroid.App
import com.example.ingresoandroid.R
import com.example.ingresoandroid.model.data.User
import com.example.ingresoandroid.model.data.UserBind
import com.example.ingresoandroid.view.adapters.ListUsersAdapter
import com.example.ingresoandroid.view.dialogs.LoadingDialog
import com.example.ingresoandroid.viewmodel.UIState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empty_view.*

class MainActivity : AppCompatActivity() {

    // Adapters
    private lateinit var listUsersAdapter: ListUsersAdapter

    // Inject
    val userViewModel = App.injectUserViewModel()

    // Dialogs
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupHandlers()
        setupListUsersAdapter()
        setupListenerSearchView()
    }

    override fun onResume() {
        super.onResume()
        getUsers()
    }

    private fun setupHandlers() {
        userViewModel.getUserInfoLiveData().observe(this, Observer { status ->
            hideLoading()
            when (status) {
                is UIState.Loading -> {
                    showLoading(getString(R.string.title_loading_dialog))
                }
                is UIState.Success -> {
                    val data = status.data as MutableList<UserBind>
                    listUsersAdapter.clearData()
                    listUsersAdapter.setData(data)
                }
                is UIState.Error -> {
                    listUsersAdapter.clearData()
                    Toast.makeText(this, status.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupListUsersAdapter() {
        listUsersAdapter = ListUsersAdapter(clickUser = { user ->
            launchPostActivity(user)
        })
        recyclerViewUsers.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = listUsersAdapter
        }
    }

    private fun setupListenerSearchView() {
        searchViewUsers.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(filterList(listUsersAdapter.getData(), newText).isNullOrEmpty()) {
                    recyclerViewUsers.visibility = View.GONE
                    messageTextView.text = getString(R.string.title_main_message_list_empty)
                    containerMessageView.visibility = View.VISIBLE
                } else {
                    recyclerViewUsers.visibility = View.VISIBLE
                    containerMessageView.visibility = View.GONE
                }
                listUsersAdapter.animateTo(filterList(listUsersAdapter.getData(), newText))
                recyclerViewUsers.scrollToPosition(0)
                return true
            }
        })
    }

    private fun filterList(listUsers: MutableList<UserBind>, newText: String): MutableList<UserBind> {
        val arrayQuery = newText.toLowerCase().trim()
        val wordsArray = arrayQuery.split(" ")
        val filterList: MutableSet<UserBind> = mutableSetOf()
        listUsers.forEach { user ->
            wordsArray.forEach { word ->
                if (user.name.toLowerCase().contains(word)) {
                    filterList.add(user)
                }
            }
        }
        return filterList.toMutableList()
    }

    private fun getUsers() {
        userViewModel.getUsers()
    }

    private fun launchPostActivity(user: UserBind) {
        Intent(this, PostActivity::class.java).run {
            putExtra("user", user)
            startActivity(this)
        }
    }

    private fun showLoading(message: String) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.newInstance(message)
            loadingDialog!!.show(this.supportFragmentManager, LoadingDialog.TAG)
        }
    }

    private fun hideLoading() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
    }
}
