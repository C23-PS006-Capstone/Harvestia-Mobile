package com.banksampah.Ui

data class ArtikelResponse(
    var listArticles: List<ArtikelItemResponse>
)

data class ArtikelItemResponse(
    var ArticleId: String,
    var content: String,
    var publishedAt: String,
    var title: String,
    var url: String,
    var urlToImage: String,
)