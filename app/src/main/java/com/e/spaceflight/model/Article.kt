package com.e.spaceflight.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Article(

  val id : String,
 val title : String,
 val url : String,
  val imageUrl : String,
  val newsSite : String,
  val summary : String,
 val publishedAt : String,
  val updatedAt : String,
  val featured : Boolean,
 // val launches : List<String>,
 // val events : List<String>

// @SerializedName("id") val id : String,
//@SerializedName("title") val title : String,
//@SerializedName("url") val url : String,
//@SerializedName("imageUrl") val imageUrl : String,
//@SerializedName("newsSite") val newsSite : String,
//@SerializedName("summary") val summary : String,
//@SerializedName("publishedAt") val publishedAt : String,
//@SerializedName("updatedAt") val updatedAt : String,
//@SerializedName("featured") val featured : Boolean,
//@SerializedName("launches") val launches : List<String>,
//@SerializedName("events") val events : List<String>

)