package com.example.ingresoandroid

import android.app.Application
import com.example.ingresoandroid.model.PostRepository
import com.example.ingresoandroid.model.UserRepository
import com.example.ingresoandroid.model.api.PostApi
import com.example.ingresoandroid.model.api.UserApi
import com.example.ingresoandroid.services.Connection
import com.example.ingresoandroid.viewmodel.PostViewModel
import com.example.ingresoandroid.viewmodel.UserViewModel

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class App: Application() {

    companion object {
        // Connection
        private lateinit var connection: Connection
        lateinit var client : OkHttpClient

        // Users
        private lateinit var userViewModel: UserViewModel
        private lateinit var userRepository: UserRepository

        // Posts
        private lateinit var postViewModel: PostViewModel
        private lateinit var postRepository: PostRepository

        //private lateinit var appDatabase: AppDatabase

        fun injectUserViewModel() = userViewModel
        fun injectPostViewModel() = postViewModel
        //fun injectUserDao() = appDatabase.userDao()
    }

    override fun onCreate() {
        super.onCreate()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()

        // Connection
        connection = Connection

        // Users
        userRepository = UserRepository(connection)
        userViewModel = UserViewModel(userRepository)

        // Posts
        postRepository = PostRepository(connection)
        postViewModel = PostViewModel(postRepository)
    }
}