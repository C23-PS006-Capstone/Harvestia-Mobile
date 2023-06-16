package com.banksampah.Ui.Data.Respons

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "userBankSampah")
@Parcelize
data class UserR(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    @ColumnInfo(name = "berat") var weight: Int = 0,
    @ColumnInfo(name = "harga") var price: Int = 0,
    val luasTanah: Int,
    val tanaman: String,
    val jenisPupuk: String,
    val _username: String
) : Parcelable