package com.dallens.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dallens.posts.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPost()
    }

    fun getPost(){
        var retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = retrofit.getPost()

        request.enqueue(object : Callback<List<PostClient>> {
            override fun onResponse(call: Call<List<PostClient>>, response: Response<List<PostClient>>) {
                if (response.isSuccessful){
                    var post = response.body()
                    Toast.makeText(baseContext,"fetched ${post!!.size} posts",Toast.LENGTH_LONG).show()
                    var PostAdapter=Adapter(post)


                    binding.recyclerView.layoutManager= LinearLayoutManager(baseContext)
                    binding.recyclerView.adapter=PostAdapter


                }
            }

            override fun onFailure(call: Call<List<PostClient>>, t: Throwable) {
            }
        })

    }
}