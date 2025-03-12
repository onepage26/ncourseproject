package com.muhtarkhan.ncourseproject.mainApp.BottomNavBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.muhtarkhan.ncourseproject.R

@Composable
fun BottomNavBar(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    onHome: () -> Unit,
    onCourse: () -> Unit,
    onReport: () -> Unit,
    onBlog: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var tabs = listOf("Home", "course", "feedback", "blog")

        tabs.forEach { tab ->
            var icon = when (tab) {
                "Home" -> if (selectedTab == "Home") R.drawable.ic_home_red else R.drawable.ic_home
                "course" -> if (selectedTab == "course") R.drawable.ic_course_red else R.drawable.ic_course
                "feedback" -> if (selectedTab == "feedback") R.drawable.ic_report_red else R.drawable.ic_report
                "blog" -> if (selectedTab == "blog") R.drawable.ic_blog_red else R.drawable.ic_blog
                else -> R.drawable.ic_home
            }

            NavBarItem(
                iconRes = icon,
                label = tab,
                onClick = {
                    onTabSelected(tab)
                    when (tab) {
                        "Home" -> onHome()
                        "course" -> onCourse()
                        "feedback" -> onReport()
                        "blog" -> onBlog()
                    }
                }
            )
        }
    }
}


@Composable
fun NavBarItem(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Text(text = label, style = MaterialTheme.typography.bodySmall)
    }
}