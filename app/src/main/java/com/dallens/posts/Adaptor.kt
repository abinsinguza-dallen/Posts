package com.dallens.posts

    import android.content.Context
    import android.content.Intent
    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import com.dallens.posts.databinding.ListItemsBinding
    import org.w3c.dom.Comment
    import retrofit2.Retrofit

class Adapter( var post: List<PostClient>): RecyclerView.Adapter<RetrofitViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitViewHolder {
            var binding=ListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return RetrofitViewHolder(binding)
        }
        override fun onBindViewHolder(holder: RetrofitViewHolder, position: Int) {
            var current = post.get(position)
            holder.binding.tvid.text = current.id.toString()
            holder.binding.tvUserId.text = current.userId.toString()
            holder.binding.tvTitle.text = current.title
            holder.binding.tvBody.text = current.body

            val context = holder.itemView.context
            holder.binding.cvPosts.setOnClickListener {
                val intent = Intent(context,CommentsActivity::class.java)
                intent.putExtra("POST_ID", current.id)
                context.startActivity(intent)
            }
        }
        override fun getItemCount(): Int {
            return post.size
        }
    }
    class RetrofitViewHolder(var binding: ListItemsBinding): RecyclerView.ViewHolder(binding.root)
