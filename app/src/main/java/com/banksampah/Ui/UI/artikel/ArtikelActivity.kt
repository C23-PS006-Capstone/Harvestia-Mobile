package com.banksampah.Ui.UI.artikel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.banksampah.R
import com.banksampah.Ui.Data.Adapter.ArtikelListAdapter
import com.banksampah.Ui.Data.model.ArtikelViewModel
import com.banksampah.databinding.ActivityArtikelBinding
class ArtikelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtikelBinding
    private lateinit var artikelListAdapter: ArtikelListAdapter

    //private val ArtikelViewModel : ArtikelViewModel by lazy {
        //ViewModelProvider(this)[ArtikelViewModel::class.java]
    //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = resources.getString(R.string.home)

        artikelListAdapter = ArtikelListAdapter(emptyList())
        binding.rvArtikel.apply {
            layoutManager = LinearLayoutManager(this@ArtikelActivity)
            adapter = artikelListAdapter
        }

        //val layoutManager = LinearLayoutManager(this)
        //binding.rvArtikel.layoutManager = layoutManager
        //val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        //binding.rvArtikel.addItemDecoration(itemDecoration)


    }



}