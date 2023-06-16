package com.banksampah.Ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.banksampah.R
import com.banksampah.Ui.ArtikelItemResponse
import com.banksampah.Ui.ArtikelResponse

class ArtikelAdapter(
    private val context: Context,
    private val dataList: MutableList<ArtikelItemResponse>
): RecyclerView.Adapter<ArtikelAdapter.MyViewHolder>(){

    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){
        var tv_judul_artikel = view.findViewById<TextView>(R.id.tv_judul_artikel)
        var tv_desc_artikel = view.findViewById<TextView>(R.id.tv_desc_artikel)
        var cv_main = view.findViewById<CardView>(R.id.cv_main)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_artikel,parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_judul_artikel.text = dataList[position].title
        holder.tv_desc_artikel.text = dataList[position].content
        holder.cv_main.setOnClickListener{
            Toast.makeText(context, ""+ dataList[position].title, Toast.LENGTH_SHORT).show()
        }


    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data:List<ArtikelItemResponse>){
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

}