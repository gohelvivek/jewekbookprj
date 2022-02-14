package com.avh.jewelbook


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper2(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val query =
            ("create table Account(ID INTEGER PRIMARY KEY,NAME TEXT,NUMBER TEXT,PASSWORD TEXT)")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + Account)
        onCreate(db)
    }

    //Insert Data in database
    fun addAccount(name: String, mobileno: String, pass: String) {

        val values = ContentValues()

        values.put("NAME", name)
        values.put("NUMBER", mobileno)
        values.put("PASSWORD", pass)
        val db = this.writableDatabase
        db.insert("Account", null, values)
        db.close()
    }

    //Update Data
    fun updateNumber(name: String, mobileno: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("NAME", name)
        db.update("Account", contentValues, "NUMBER = '$mobileno'", null)
        return true
    }

    //Check Number
    fun getNumber(mbo: String): Cursor {
        var selectQuery = "select NUMBER from Account where NUMBER = " + "'" + mbo + "'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        return cursor
    }


    //Create Object For Database
    companion object {
        val DATABASE_NAME = "JEWELBOOKACCOUNT"
        val DATABASE_VERSION = 1
    }

}



