package com.avh.jewelbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class CustomerAdapter(mCtx: Context, val customers: ArrayList<Customer>):RecyclerView.Adapter<CustomerAdapter.ViewHolder>(){

    val context = mCtx
    val db = DBHelper(mCtx, null)

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        lateinit var Name : TextView
        lateinit var Number : TextView
        lateinit var Password : TextView
        lateinit var Update : Button
        lateinit var Delete : Button
        init {
                Name = itemView.findViewById(R.id.txtCustomerName)
                Number = itemView.findViewById(R.id.txtCustomerNumber)
                Password = itemView.findViewById(R.id.txtCustomerPassword)
                Update = itemView.findViewById(R.id.btnUpdate)
                Delete = itemView.findViewById(R.id.btnDelete)

            itemView.setOnClickListener {
                Toast.makeText(it.context,"CLICK", Toast.LENGTH_SHORT).show();
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerdesign,parent,false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: CustomerAdapter.ViewHolder, position: Int) {
        val customer : Customer = customers[position]
        holder.Name.text = customer.Name
        holder.Number.text=customer.Number
        holder.Password.text=customer.Password

    }

    override fun getItemCount(): Int{
        return customers.size

    }

}