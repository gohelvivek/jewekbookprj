package com.avh.jewelbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptersitems(
    mitem: Context,
    val Modelitem: ArrayList<Modelsitem>,
    private val listener: OnItemClickListener?
) :
    RecyclerView.Adapter<Adaptersitems.ViewHolder>() {

    val mitem = mitem

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        //        lateinit var listener :View.OnClickListener
        lateinit var title: TextView

        init {
            title = itemView.findViewById(R.id.title)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener?.onItemClick(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
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