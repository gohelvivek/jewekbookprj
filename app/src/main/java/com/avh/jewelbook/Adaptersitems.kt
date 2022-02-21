package com.avh.jewelbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class Adaptersitems(mitem: Context, val Modelitem: ArrayList<Modelsitem>) : RecyclerView.Adapter<Adaptersitems.ViewHolder>() {
/*
    val mitem = mitem*/
    val context = mitem

    private lateinit var mList : CustomerAdapter.onItemClickListener

    interface onItemClickListener : CustomerAdapter.onItemClickListener {
        override fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener){
        mList = listener
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var title: TextView

        init {
            title = itemView.findViewById(R.id.title)

            itemView.setOnClickListener {
                Toast.makeText(it.context,"CLICK",Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptersitems.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: Adaptersitems.ViewHolder, position: Int) {
        val items: Modelsitem = Modelitem[position]
        holder.title.text = items.item

        holder.itemView

    }

    override fun getItemCount(): Int {
        return Modelitem.size

    }
}