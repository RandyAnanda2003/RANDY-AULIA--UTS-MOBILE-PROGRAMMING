package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter (private val ListBerita: ArrayList<Berita>  ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mlistener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mlistener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView,mlistener)
    }

    override fun getItemCount(): Int {
        return ListBerita.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = ListBerita[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.heading.text = currentItem.heading
        holder.deskripsi.text = currentItem.deskripsi
    }

    class MyViewHolder (itemView: View, listener:onItemClickListener ) : RecyclerView.ViewHolder(itemView){
        val titleImage : ShapeableImageView = itemView.findViewById(R.id.gambar_berita)
        val heading :   TextView = itemView.findViewById(R.id.judul_berita)
        val deskripsi : TextView = itemView.findViewById(R.id.deskripsi_berita)
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}