package com.banksampah.Ui.Data.Api

import com.banksampah.Ui.Data.DataLocal.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {

    @GET("/articles")
    fun getArticles(
        @Query("keyword") keyword: String,
        @Query("judularticle") judularticle: String,
    ): Call<ArticleResponse>

}