package com.avh.jewelbook

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

public class sessionmanager {

    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var con: Context
    var PRIVATE_MODE: Int = 0

    constructor(con: Context) {
        this.con = con
        pref = con.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }


    companion object {
        val PREF_NAME: String = "KotlinDemo"
        val IS_LOGIN: String = "IsLogedIn"
        val KEY_NAME: String = "name"
        val KEY_EMAIL: String = "email"
        val KEY_NUMBER: String = "number"

    }

    fun createLoginSession(name: String?, email: String, username:String) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_NUMBER, username)
        editor.commit()
    }

    fun chackLogin() {
        if (!this.isLoggedIn()) {
            var i: Intent = Intent(con, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            con.startActivity(i)
        }
    }

    fun getUerDetails(): HashMap<String, String> {
        var user: Map<String, String> = hashMapOf<String, String>()
        (user as HashMap).put(KEY_NAME, pref.getString(KEY_NAME, null).toString())
        (user as HashMap).put(KEY_NUMBER, pref.getString(KEY_NUMBER, null).toString())
        (user as HashMap).put(KEY_EMAIL, pref.getString(KEY_EMAIL, null).toString())
        return user

    }
    fun logoutUser(){
        editor.clear()
        editor.commit()

        var i: Intent = Intent(con, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        con.startActivity(i)
    }

    fun  isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN,false)
    }


}