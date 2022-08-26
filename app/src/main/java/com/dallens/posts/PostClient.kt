package com.dallens.posts

data class PostClient(
var  userId:String,
var id:Int,
var title:String,
var body:String

)
data class comment(
    var postId:Int,
    var id:String,
    var name:String,
    var email:String,
    var body:String
)

