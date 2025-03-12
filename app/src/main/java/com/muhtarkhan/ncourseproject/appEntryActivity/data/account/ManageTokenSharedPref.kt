package com.muhtarkhan.ncourseproject.appEntryActivity.data.account

import android.content.Context

class ManageTokenSharedPref(context: Context): AccountProvider {
    private val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    override fun saveToken(token: String){
        sharedPreferences.edit().putString("access_token", token).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString("access_token", null)
    }

    override fun clearToken() {
        sharedPreferences.edit().remove("access_token").apply()
    }

    override fun isLoggedIn(): Boolean {
        return getToken() != null
    }
}