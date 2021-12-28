package com.avh.jewelbook


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                CNAME_COL + " TEXT," +
                MNO_COL + " TEXT," +
                PWD_COL + " TEXT," +
                WNO_COL + " TEXT," +
                EML_COL + " TEXT," +
                WEB_COL + " TEXT," +
                ADDRE_COL + " TEXT" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }


    fun addName(
        name: String,
        ccname: String,
        mobileno: String,
        pass: String,
        wtno: String,
        mail: String,
        websitee: String,
        addrress: String
    ) {

        val values = ContentValues()

        values.put(NAME_COl, name)
        values.put(CNAME_COL, ccname)
        values.put(MNO_COL, mobileno)
        values.put(PWD_COL, pass)
        values.put(WNO_COL, wtno)
        values.put(EML_COL, mail)
        values.put(WEB_COL, websitee)
        values.put(ADDRE_COL, addrress)


        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()
    }


    fun getUser(mbo: String, pas: String): Boolean {

        var selectQuery = "select * from  " + TABLE_NAME.toString() + " where " + MNO_COL.toString() + " = " + "'" + mbo + "'" + " and " + PWD_COL.toString() + " = " + "'" + pas + "'"
       // var qurys = "SELECT * FROM logggin"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        cursor.moveToFirst()
        if (cursor.count > 0) {
            return true
        }
        cursor.close()
        db.close()
        return false
    }


    companion object {

        private val DATABASE_NAME = "JEWELBOOK"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "logggin"
        val ID_COL = "id"
        val NAME_COl = "name"
        val CNAME_COL = "ccname"
        val MNO_COL = "mobileno"
        val PWD_COL = "pass"
        val WNO_COL = "wtno"
        val EML_COL = "mail"
        val WEB_COL = "websitee"
        val ADDRE_COL = "addrress"


    }

}


