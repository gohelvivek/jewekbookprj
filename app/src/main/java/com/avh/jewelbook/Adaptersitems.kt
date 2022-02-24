package com.avh.jewelbook

import android.app.AlertDialog
import android.content.ComponentCallbacks
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class Adaptersitems(mitem: Context, val Modelitem: ArrayList<Modelsitem>) : RecyclerView.Adapter<Adaptersitems.ViewHolder>() {
/*
    val mitem = mitem*/
    val context = mitem
    private var onClickDeleteItems:((Modelsitem)->Unit)?=null

    private lateinit var mList : CustomerAdapter.onItemClickListener

    interface onItemClickListener : CustomerAdapter.onItemClickListener {
        override fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener){
        mList = listener
    }
    /*fun onClickDeleteItems(callback:(Modelsitem)->Unit) {
        this.onClickDeleteItems=callback

    }*/

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var title: TextView
        lateinit var btndelete: Button


        init {
            title = itemView.findViewById(R.id.title)
            btndelete = itemView.findViewById(R.id.idelete)

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
        holder.btndelete.setOnClickListener { onClickDeleteItems?.invoke(items) }


        holder.itemView

        holder.btndelete.setOnClickListener {
            val name = items.item

            val db = DBHelper1(context, null)
            AlertDialog.Builder(context)
                .setTitle("Warning")
                .setMessage("Are you sure delete : $name?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    if (db.deleteitem(items.item)) {
                        Modelitem.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, Modelitem.size)
                        Toast.makeText(context, "Customer $name Deleted", Toast.LENGTH_SHORT)
                            .show()
                    }else
                        Toast.makeText(context,"Error Deleting",Toast.LENGTH_SHORT).show()
                })
                .setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->  })
                .setIcon(R.drawable.ic_baseline_warning_24)
                .show()
        }

    }

    override fun getItemCount(): Int {
        return Modelitem.size

    }
}