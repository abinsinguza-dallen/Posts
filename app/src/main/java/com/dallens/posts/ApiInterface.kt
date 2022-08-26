package com.dallens.posts

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    fun getPost(): Call<List<PostClient>>

    @GET("/posts/{postId}")
    fun getPostById(@Path("postId")postId: Int): Call<PostClient>

    @GET("/comments")
    fun getcomments():Call<List<comment>>
    @GET("/comments/{commentId}")
    fun getCommentById(@Path("commentId")commentId:Int):Call<comment>
}

