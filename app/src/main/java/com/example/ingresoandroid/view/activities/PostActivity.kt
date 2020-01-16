package com.example.ingresoandroid.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ingresoandroid.App
import com.example.ingresoandroid.R
import com.example.ingresoandroid.model.data.PostBind
import com.example.ingresoandroid.model.data.UserBind
import com.example.ingresoandroid.view.adapters.ListPostAdapter
import com.example.ingresoandroid.view.adapters.ListUsersAdapter
import com.example.ingresoandroid.view.dialogs.LoadingDialog
import com.example.ingresoandroid.viewmodel.UIState
import kotlinx.android.synthetic.main.activity_post.*
import java.io.Serializable

class PostActivity : AppCompatActivity() {

    // Adapters
    private lateinit var listPostAdapter: ListPostAdapter

    // Inject
    val postViewModel = App.injectPostViewModel()

    // Dialogs
    private var loadingDialog: LoadingDialog? = null

    // Variables
    var user = UserBind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setupHandlers()
        setupListPostsAdapter()
        obtainData()
        setDataTextView()
    }

    override fun onResume() {
        super.onResume()
        getPosts(this.user.id)
    }

    private fun obtainData() {
        this.user = intent.getSerializableExtra("user") as UserBind
    }

    private fun setupHandlers() {
        postViewModel.getPostInfoLiveData().observe(this, Observer { status ->
            hideLoading()
            when (status) {
                is UIState.Loading -> {
                    showLoading(getString(R.string.title_loading_dialog))
                }
                is UIState.Success -> {
                    val data = status.data as MutableList<PostBind>
                    listPostAdapter.clearData()
                    listPostAdapter.setData(data)
                }
                is UIState.Error -> {
                    listPostAdapter.clearData()
                    Toast.makeText(this, status.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupListPostsAdapter() {
        listPostAdapter = ListPostAdapter()
        recyclerViewPosts.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = listPostAdapter
        }
    }

    private fun getPosts(userId : Int) {
        postViewModel.getPosts(userId)
    }

    private fun setDataTextView() {
        textNameUser.text = user.name
        textEmailUser.text = user.email
        textPhoneUser.text = user.phone
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
