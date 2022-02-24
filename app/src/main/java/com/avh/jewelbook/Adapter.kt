package com.avh.jewelbook

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class CustomerAdapter(mCtx: Context, val customers: ArrayList<Customer>) :
    RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    val context = mCtx
    val db = DBHelper(context, null)

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var Name: TextView
        lateinit var Number: TextView
        lateinit var Password: TextView
        lateinit var Update: Button
        lateinit var Delete: Button

        init {
            Name = itemView.findViewById(R.id.txtCustomerName)
            Number = itemView.findViewById(R.id.txtCustomerNumber)
            Password = itemView.findViewById(R.id.txtCustomerPassword)
            Update = itemView.findViewById(R.id.btnUpdate)
            Delete = itemView.findViewById(R.id.btnDelete)

            itemView.setOnClickListener {
                Toast.makeText(it.context, "CLICK", Toast.LENGTH_SHORT).show();
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerdesign, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: CustomerAdapter.ViewHolder, position: Int) {
        val customer: Customer = customers[position]
        holder.Name.text = customer.Name
        holder.Number.text = customer.Number
        holder.Password.text = customer.Password


        holder.Delete.setOnClickListener {
            val name = customer.Name

            val db = DBHelper(context, null)
            var alertDialog = AlertDialog.Builder(context)
                .setTitle("Warning")
                .setMessage("Are you sure delete : $name?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    if (db.delete(customer.Name)) {
                            customers.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, customers.size)
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
        return customers.size

    }

}