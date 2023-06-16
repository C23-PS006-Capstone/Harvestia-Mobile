package com.banksampah.Ui.Data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.banksampah.Ui.Data.Api.APIConfig
import com.banksampah.Ui.Data.DataLocal.ArticleResponse
import com.banksampah.Ui.Data.DataLocal.ArtikelDetail
import retrofit2.Call
import retrofit2.Response

class ArtikelViewModel : ViewModel() {
    var artikel: List<ArtikelDetail> = listOf()

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading

    var isError: Boolean = false

    private lateinit var keyword: String

    fun getArtikel(keyword: String, judularticle: String){
        _isloading.value = true
        val api = APIConfig.getAPIService().getArticles("Bearer $keyword", "Bearer $judularticle")
        api.enqueue(object : retrofit2.Callback<ArticleResponse>{
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                _isloading.value = false
                if (response.isSuccessful){
                    isError = false
                    val responseBody = response.body()
                    if (responseBody != null){
                        artikel = responseBody.listArtikel
                    }
                    _message.value = responseBody?.message.toString()
                } else{
                    isError = true
                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                _isloading.value = false
                isError = true
                _message.value = t.message.toString()
            }

        })
    }
}