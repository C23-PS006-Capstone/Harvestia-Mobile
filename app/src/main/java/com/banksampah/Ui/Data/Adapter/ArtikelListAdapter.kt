package com.banksampah.Ui.Data.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.banksampah.R
import com.banksampah.databinding.ItemArtikelBinding
import com.banksampah.Ui.Data.DataLocal.ArtikelDetail

class ArtikelListAdapter (private val listArtikel: List<ArtikelDetail>) :
    RecyclerView.Adapter<ArtikelListAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemCLickCallback

    fun setOnItemClickCallback(onItemCLickCallback: OnItemCLickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemCLickCallback {
        fun onItemClicked(detail: ArtikelDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemArtikelBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listArtikel.size ?:0

    override fun onBindViewHolder(holder: ListViewHolder, position: Int){
        listArtikel?.get(position)?.let {
            artikelDetail ->
            holder.bind(listArtikel[position])
            holder.itemView.setOnClickListener{
                onItemClickCallback.onItemClicked(listArtikel[holder.adapterPosition])
            }
        }
    }

    class ListViewHolder(private val binding: ItemArtikelBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: ArtikelDetail){
            binding.executePendingBindings()
        }
    }

    @BindingAdapter("setPhoto")
    fun setPhoto(imageView: ImageView, url: String){
        imageView.load(url){
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_foreground)
            fallback(R.drawable.ic_launcher_foreground)
        }
    }
}
