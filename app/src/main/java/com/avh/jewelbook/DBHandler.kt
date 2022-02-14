package com.avh.jewelbook

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.widget.Toast

class DBHandler(context: Context,name : String?, factory:SQLiteDatabase.CursorFactory?,version:Int) : SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION){

    companion object{
        private val DATABASE_NAME = "MyData"
        private val DATABASE_VERSION = 1
        val CUSTOMERS_TABLE_NAME = "Customers"
        val COLUMN_CUSTOMERID = "CustomerId"
        val COLUMN_CUSTOMERNAME = "CustomerName"
        val COLUMN_CUSTOMERNUMBER = "CustomerNumber"
        val COLUMN_CUSTOMERPASSWORD = "CustomerPassword"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CUSTOMERS_TABLE = ("CREATE TABLE $CUSTOMERS_TABLE_NAME(" +
                "$COLUMN_CUSTOMERID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_CUSTOMERNAME TEXT," +
                "$COLUMN_CUSTOMERNUMBER TEXT," +
                "$COLUMN_CUSTOMERPASSWORD TEXT)")
        db?.execSQL(CREATE_CUSTOMERS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun InserAccount(id : String,CusName: String, CusNum: String, pwd:String){
        val values = ContentValues()

        values.put(COLUMN_CUSTOMERID, id)
        values.put(COLUMN_CUSTOMERNAME, CusName)
        values.put(COLUMN_CUSTOMERNUMBER, CusNum)
        values.put(COLUMN_CUSTOMERPASSWORD, pwd)

        val db = this.writableDatabase

        db.insert(DBHelper.TABLE_NAME, null, values)

        db.close()
    }

    @SuppressLint("Range")
    fun getCustomers(mCtx : Context) : ArrayList<Customer> {
        val qry = "Select * From $CUSTOMERS_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Customer>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No Records Found", Toast.LENGTH_SHORT).show() else {
            while (cursor.moveToNext()) {
                val customer = Customer()
                customer.customerID = cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMERID))
                customer.customerName = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMERNAME))
                customer.customerNumber =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMERNUMBER))
                customer.customerPassword =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMERPASSWORD))
                customers.add(customer)
            }
            Toast.makeText(mCtx, "${cursor.count.toString()} Records Found", Toast.LENGTH_SHORT)
                .show()
        }
        cursor.close()
        db.close()
        return customers
    }


}