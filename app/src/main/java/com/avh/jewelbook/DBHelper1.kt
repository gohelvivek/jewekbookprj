package com.avh.jewelbook

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class DBHelper1(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DBHelper1.DATABASE_NAME, factory, DBHelper1.DATABASE_VERSION) {

    override fun onCreate(dbb: SQLiteDatabase?) {

        dbb!!.execSQL("create table itnm(NAME TEXT)")

    }

    override fun onUpgrade(dbb: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(items: String?): Boolean {
        val dbb = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("NAME", items)
        val res = dbb.insert("itnm", null, contentValues)
        if (res.equals(-1))
            return false
        else
            return true
    }

    companion object {

        val DATABASE_NAME = "item"
        val DATABASE_VERSION = 1
//        val TABLE_NAME = "items"
//        val COL = "itemname"

    }

    @SuppressLint("Range")
    fun getallitem(item: Context): ArrayList<Modelsitem> {
        val qry = "Select * From itnm"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val item1 = ArrayList<Modelsitem>()

        if (cursor.count == 0) {
            Toast.makeText(item, "No Records Found", Toast.LENGTH_SHORT).show()
        } else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast()) {
                val item2 = Modelsitem()
                item2.item = cursor.getString(cursor.getColumnIndex("NAME"))
                item1.add(item2)
                cursor.moveToNext()
            }
            Toast.makeText(item, "${cursor.count.toString()}Record Found", Toast.LENGTH_SHORT)
                .show()
        }
        cursor.close()
        db.close()
        return item1
    }
    fun deleteitem():Boolean{
       // val qyr="Delete From $"
        val db=this.writableDatabase
        var result:Boolean=false
        try {
           // val cursor =db.delete()
            //val cursor=db.execSQL(qyr)
            result=true
        }catch (e:Exception){
            Log.e(ContentValues.TAG,"Error Deleting")
        }
        db.close()
        return result
    }

}