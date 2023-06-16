package com.banksampah.Ui.UI.Kalkulator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.banksampah.Ui.Data.Client.ClientDB.Companion.getInstance
import com.banksampah.Ui.Data.Dao.BankDao
import com.banksampah.Ui.Data.Respons.UserR
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class kalkulatorViewModel (application: Application) : AndroidViewModel(application) {

    var _bankDao: BankDao?

    fun addDataSampah(
        _username: String,
        _jenisPupuk: String,
        _tanaman: String,
        _luasTanah: Int
    ) {
        Completable.fromAction {
            val _userR = UserR(
                luasTanah = _luasTanah,
                tanaman = _tanaman,
                jenisPupuk = _jenisPupuk,
                _username = _username,
            )
            _bankDao?.insertData(_userR)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        _bankDao = getInstance(application)?.appDatabase?.bankDao()
    }

}