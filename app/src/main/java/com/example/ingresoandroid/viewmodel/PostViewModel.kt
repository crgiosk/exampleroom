package com.example.ingresoandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ingresoandroid.model.PostRepository
import com.example.ingresoandroid.model.data.Post
import com.example.ingresoandroid.model.data.PostBind
import com.example.ingresoandroid.model.data.User
import com.example.ingresoandroid.services.ModelResponse

class PostViewModel(private val postRepository: PostRepository) {

    // Variables Binding
    private val getPostInfoLiveData: MutableLiveData<UIState> = MutableLiveData()

    fun getPostInfoLiveData(): LiveData<UIState> = getPostInfoLiveData

    fun getPosts(userId: Int) {
        getPostInfoLiveData.value = UIState.Loading
        postRepository.getPost(userId) { response ->
            when(response) {
                is ModelResponse.OnSuccess -> {
                    getPostInfoLiveData.postValue(UIState.Success(mapList(response.result as ArrayList<Post>)))
                }
                is ModelResponse.OnError -> {
                    val messageError = response.error
                    getPostInfoLiveData.postValue(UIState.Error(messageError))
                }
            }
        }
    }

    private fun mapList(array : ArrayList<Post>): MutableList<PostBind> {
        val values: MutableList<PostBind>  = mutableListOf()
        array.map { postApi ->
            val postBind = PostBind()
            postBind.id = postApi.id
            postBind.title = postApi.title
            postBind.body = postApi.body
            values.add(postBind)
        }
        return values
    }
}