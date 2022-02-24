package com.avh.jewelbook


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    var logname = ""
    var logcname = ""

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

        val queryy = ("CREATE TABLE Account(ID INTEGER PRIMARY KEY,NAME TEXT,NUMBER TEXT,PASSWORD TEXT)")
        db.execSQL(queryy)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    //Insert Data in database
    fun addName(
        name: String,
        ccname: String,
        mobileno: String,
        pass: String,
        wtno: String,
        mail: String,
        websitee: String,
        addrress: String,
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


    //Check username and password
    fun getUser(mbo: String, pas: String): Boolean {

        var selectQuery =
            "select * from  " + TABLE_NAME.toString() + " where " + MNO_COL.toString() + " = " + "'" + mbo + "'" + " and " + PWD_COL.toString() + " = " + "'" + pas + "'"
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


    //Get Name
    fun getName(mb0o: String): Cursor? {
        var selectQuery =
            "select name, ccname from " + TABLE_NAME + " where " + MNO_COL + " = " + "'" + mb0o + "'"

        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        return cursor
    }

    //CHeck Number for PAssword reset
    fun getMno(mbopo: String): Boolean {

        var selectQuery =
            "select * from  " + TABLE_NAME + " where " + MNO_COL + " = " + "'" + mbopo + "'"
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

    //Get name and Company name
    fun getdata(mpo: String): Cursor? {

        var selectQuery =
            "select * from  " + TABLE_NAME.toString() + " where " + MNO_COL.toString() + " = " + "'" + mpo + "'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        return cursor
    }

    //All data for profile
    fun getAlldata(mpo: String): Cursor? {

        var selectQuery =
            "select * from  " + TABLE_NAME.toString() + " where " + MNO_COL.toString() + " = " + "'" + mpo + "'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        return cursor
    }


    //Change Password
    fun updateData(chpwd: String, mbopo: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("pass", chpwd)
        db.update(TABLE_NAME, contentValues, "mobileno = $mbopo", null)
        return true
    }

    //Change Password
    fun updateMainData(
        mbopo: String,
        name: String,
        ccname: String,
        mobileno: String,
        wtno: String,
        mail: String,
        websitee: String,
        addrress: String,
    ): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("ccname", ccname)
        contentValues.put("mobileno", mobileno)
        contentValues.put("wtno", wtno)
        contentValues.put("mail", mail)
        contentValues.put("websitee", websitee)
        contentValues.put("addrress", addrress)

        db.update(TABLE_NAME, contentValues, "mobileno = $mbopo", null)
        return true
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

    fun delete(name: String):Boolean{
       // val qry = "Delete from Account where NAME = $name"
        val db = this.writableDatabase
        val qry=db.delete("Account","NAME='$name'",null)

        var result:Boolean=false
        try {
            val cursor = db.execSQL(qry.toString())
            result = true
        }catch (e:Exception){
            Log.e(ContentValues.TAG,"Error Deleting")
        }
        db.close()
        return result
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
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        return cursor
    }
    @SuppressLint("Range")
    fun getCustomers(mCtx : Context) : ArrayList<Customer> {
        val qry = "Select * From Account "
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Customer>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No Records Found", Toast.LENGTH_SHORT).show() else {
            while (cursor.moveToNext()) {
                val customer = Customer()
                customer.ID = cursor.getInt(cursor.getColumnIndex("ID"))
                customer.Name = cursor.getString(cursor.getColumnIndex("NAME"))
                customer.Number =
                    cursor.getString(cursor.getColumnIndex("NUMBER"))
                customer.Password =
                    cursor.getString(cursor.getColumnIndex("PASSWORD"))
                customers.add(customer)
            }
            Toast.makeText(mCtx, "${cursor.count.toString()} Records Found", Toast.LENGTH_SHORT)
                .show()
        }
        cursor.close()
        db.close()
        return customers
    }

    //Create Object For Database
    companion object {

        val DATABASE_NAME = "JEWELBOOK"
        val DATABASE_VERSION = 1
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



