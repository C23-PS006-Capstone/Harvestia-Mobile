package com.banksampah.Ui.Data.DataLocal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ArticleResponse(
    var error: String,
    var message: String,
    var listArtikel: List<ArtikelDetail>
)

@Parcelize
data class ArtikelDetail(
    var id: String,
    var judul: String,
    var description: String,
    var photoUrl: String,
): Parcelable