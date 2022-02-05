package com.avh.jewelbook

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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
}