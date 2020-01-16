package com.example.ingresoandroid.model

import com.example.ingresoandroid.core.Constants
import com.example.ingresoandroid.model.data.Post
import com.example.ingresoandroid.services.Connection
import com.example.ingresoandroid.services.ConnectionResponse
import com.example.ingresoandroid.services.Endpoint
import com.example.ingresoandroid.services.ModelResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class PostRepository(val connection: Connection) {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    enum class PostsAction : Endpoint {
        GET_POSTS {
            override val base: String get() = Constants.BASE_URL
            override val path: String get() = "/posts?userId="
        }
    }

    fun getPost(userId : Int, completion: (ModelResponse) -> Unit) {
        connection.send(PostsAction.GET_POSTS, userId) { response ->
            when(response) {
                is ConnectionResponse.OnFailure -> {
                    //completion(ModelResponse.OnError(response))
                }
                is ConnectionResponse.OnSuccess -> {
                    val type = Types.newParameterizedType(List::class.java, Post::class.java)
                    val adapter: JsonAdapter<List<Any>> = moshi.adapter(type)
                    val list: List<Any> = adapter.fromJson(response.result)!!
                    completion(ModelResponse.OnSuccess(list))
                }
            }
        }
    }
}