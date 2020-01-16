package com.example.ingresoandroid.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ingresoandroid.R
import com.example.ingresoandroid.model.data.User
import com.example.ingresoandroid.model.data.UserBind
import kotlinx.android.synthetic.main.user_list_item.view.*

class ListUsersAdapter(val clickUser: (UserBind) -> Unit) : CustomAdapter<UserBind, ListUsersAdapter.ViewHolder>() {

    // Data
    private var dataItems: MutableList<UserBind> = mutableListOf()

    fun setData(data: MutableList<UserBind>) {
        this.dataItems.addAll(data)
        this.elements.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.dataItems.clear()
        this.elements.clear()
        notifyDataSetChanged()
    }

    fun getData(): MutableList<UserBind> {
        return this.dataItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = elements[position]
        holder.bind(user)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserBind){
            itemView.userName.text = user.name
            itemView.userPhone.text = user.phone
            itemView.userEmail.text = user.email

            itemView.showPosts.setOnClickListener {
                clickUser(user)
            }
        }
    }
}