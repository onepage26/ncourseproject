package com.muhtarkhan.ncourseproject.stubs

import com.muhtarkhan.ncourseproject.appEntryActivity.data.account.AccountProvider


class AccountProviderStub : AccountProvider {

    private var savedToken: String? = null

    override fun saveToken(token: String) {
        savedToken = token
    }

    override fun getToken(): String? {
        return savedToken
    }

    override fun clearToken() {
        savedToken = null
    }

    override fun isLoggedIn(): Boolean {
        return savedToken != null
    }
}