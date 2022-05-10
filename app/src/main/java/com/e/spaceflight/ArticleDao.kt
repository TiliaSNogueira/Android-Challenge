package com.e.spaceflight

import androidx.room.*
import com.e.spaceflight.model.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles_table ORDER BY id ASC")
     fun getAllArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun saveArticle(article: Article)

    @Delete
     suspend fun deleteArticle(article: Article)


}