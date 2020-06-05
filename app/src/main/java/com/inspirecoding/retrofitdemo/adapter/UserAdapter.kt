package com.inspirecoding.retrofitdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inspirecoding.retrofitdemo.R
import com.inspirecoding.retrofitdemo.databinding.ItemUserBinding
import com.inspirecoding.retrofitdemo.model.User

class UserAdapter(var context: Context): RecyclerView.Adapter<UserAdapter.UserViewHolder>()
{
    private var listOfUser = emptyList<User>()

    fun addAllUser(listOfUser: List<User>)
    {
        this.listOfUser = listOfUser
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder
    {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemUserBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_user, parent, false
        )

        return UserViewHolder(binding)
    }

    override fun getItemCount() = listOfUser.count()

    override fun onBindViewHolder(holder: UserViewHolder, position: Int)
    {
        holder.bindUser(listOfUser[position])
    }

    inner class UserViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bindUser(user: User)
        {
            binding.tvName.text = user.name
        }
    }
}