package com.e.spaceflight.model

import java.io.Serializable

data class Article(
    val id: String,
    val title: String,
    val url: String,
    val imageUrl: String,
    val newsSite: String,
    val summary: String,
    val publishedAt: String,
    val updatedAt: String,
    val featured: Boolean
) : Serializable