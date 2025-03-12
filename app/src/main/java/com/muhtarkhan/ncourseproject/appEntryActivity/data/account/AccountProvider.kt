package com.muhtarkhan.ncourseproject.appEntryActivity.data.account



interface AccountProvider {

    fun saveToken(token: String)

    fun getToken(): String?

    fun clearToken()

    fun isLoggedIn(): Boolean
}