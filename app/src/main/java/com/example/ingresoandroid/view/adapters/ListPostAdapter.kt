package com.example.ingresoandroid.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ingresoandroid.R
import com.example.ingresoandroid.model.data.PostBind
import kotlinx.android.synthetic.main.post_list_item.view.*

class ListPostAdapter : RecyclerView.Adapter<ListPostAdapter.ViewHolder>() {

    // Data
    private var dataItems: MutableList<PostBind> = mutableListOf()

    fun setData(data: MutableList<PostBind>) {
        this.dataItems.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.dataItems.clear()
        notifyDataSetChanged()
    }

    fun getData(): MutableList<PostBind> {
        return this.dataItems
    }

    override fun getItemCount(): Int {
        return dataItems.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = dataItems[position]
        holder.bind(user)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostBind){
            itemView.titlePost.text = post.title
            itemView.bodyPost.text = post.body
        }
    }


}