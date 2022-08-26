package com.dallens.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dallens.posts.databinding.ListItemsBinding


class AdapterComments(var comment: List<comment>) : RecyclerView.Adapter<RetrofitViewsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitViewsHolder {
            var binding=ListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return  RetrofitViewsHolder(binding)
        }
        override fun onBindViewHolder(holder: RetrofitViewsHolder, position: Int) {
            var currentcomment=comment.get(position)
            with(holder.binding){
                tvBody.text=currentcomment.name.toString()
                tvTitle.text=currentcomment.body.toString()

            }
        }

        override fun getItemCount(): Int {
            return comment.size
        }

    }

    class RetrofitViewsHolder(var binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

