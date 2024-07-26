package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter2(var dataAlumni: ArrayList<Datalist>) : RecyclerView.Adapter<MyAdapter2.MyViewHolder>() {

    private var mlistener: onItemClickListener? = null

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mlistener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item2, parent, false)
        return MyViewHolder(itemView, mlistener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataAlumni[position]
        holder.npm.text = currentItem.npm
        holder.nama.text = currentItem.nama
    }

    override fun getItemCount(): Int {
        return dataAlumni.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        val npm: TextView = itemView.findViewById(R.id.npm)
        val nama: TextView = itemView.findViewById(R.id.nama)

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }
}
