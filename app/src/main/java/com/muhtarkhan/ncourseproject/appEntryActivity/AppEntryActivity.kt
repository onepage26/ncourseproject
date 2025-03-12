package com.muhtarkhan.ncourseproject.appEntryActivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.muhtarkhan.ncourseproject.R


class AppEntryActivity : FragmentActivity(R.layout.main_activity) {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController?.setGraph(R.navigation.app_graph)

        if (intent != null) {
            handleDeeplink(intent)
        }
    }

    private fun handleDeeplink(intent: Intent) {
        Handler(Looper.getMainLooper()).post {
            intent.data?.let { uri ->
                val path = uri.path ?: return@post
                Log.d("AppEntryActivity", "Deeplink received: $path")

                when (path) {
                    ROUTE_HOME -> navController?.navigate(R.id.homeFragment)
                    ROUTE_COURSE -> navController?.navigate(R.id.catalogFragment)
                    ROUTE_FEEDBACK -> navController?.navigate(R.id.feedback)
                    ROUTE_BLOGS -> navController?.navigate(R.id.blogsFragment)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeeplink(intent)
    }

    private companion object {
        const val ROUTE_HOME = "/home"
        const val ROUTE_COURSE = "/course"
        const val ROUTE_FEEDBACK = "/feedback"
        const val ROUTE_BLOGS = "/blogs"
    }
}




