package com.banksampah.Ui.API

import com.banksampah.Ui.ArtikelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface APIService {
    @GET("/articles")
    fun getArticles(

    ): Call<ArtikelResponse>
}