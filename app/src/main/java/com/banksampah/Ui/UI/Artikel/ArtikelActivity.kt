package com.banksampah.Ui.UI.Artikel

import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.banksampah.Ui.API.APIConfig
import com.banksampah.Ui.Adapter.ArtikelAdapter
import com.banksampah.Ui.ArtikelItemResponse
import com.banksampah.Ui.ArtikelResponse
import com.banksampah.databinding.ActivityArtikelBinding
import com.banksampah.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_artikel.*
import retrofit2.Call
import retrofit2.Response

class ArtikelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtikelBinding
    private val adapter by lazy {
        ArtikelAdapter(this@ArtikelActivity, arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getArtikel()

        setStatusBar()
        setToolbar()

    }

    fun getArtikel(){
        val api = APIConfig.getAPIService().getArticles()
        api.enqueue(object : retrofit2.Callback<ArtikelResponse>{
            override fun onResponse(
                call: Call<ArtikelResponse>,
                response: Response<ArtikelResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()?.listArticles
                    data?.let {
                        setDataToAdapter(it)
                    }
                }
            }

            override fun onFailure(call: Call<ArtikelResponse>, t: Throwable) {
                Log.d("Error","" + t.stackTraceToString())
            }

        })
    }

    fun setDataToAdapter(data: List<ArtikelItemResponse>){
        adapter.setData(data)
        binding.rvArtikel.adapter = adapter
        binding.rvArtikel.layoutManager = LinearLayoutManager(this)
    }

    private fun setToolbar() {
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

}