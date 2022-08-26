package com.dallens.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dallens.posts.databinding.ActivityCommentsBinding
import com.dallens.posts.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommentsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentsBinding
    var postId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        getPostById()
        fetchComments()


    }

    fun obtainPostId() {
        postId = intent.extras?.getInt("POST_ID") ?: 0
    }

    fun getPostById() {
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getPostById(postId)
        request.enqueue(object : Callback<PostClient> {
            override fun onResponse(call: Call<PostClient>, response: Response<PostClient>) {
                if (response.isSuccessful) {
                    var post = response.body()

                    binding.textView.text = post?.title
                    binding.textView2.text = post?.body

                }
            }

            override fun onFailure(call: Call<PostClient>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
    fun fetchComments() {
            var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
            var request = apiClient.getcomments()
            request.enqueue(object : Callback<List<comment>> {
                override fun onResponse(
                    call: Call<List<comment>>,
                    response: Response<List<comment>>
                ) {
                    if (response.isSuccessful) {
                        var comment = response.body()
                        Toast.makeText(
                            baseContext,
                            "fetched ${comment!!.size} comment",
                            Toast.LENGTH_LONG
                        ).show()
                        binding.rvComments.adapter = AdapterComments(comment)
                        binding.rvComments.layoutManager = LinearLayoutManager(baseContext)
                    }
                }

                override fun onFailure(call: Call<List<comment>>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }

}


