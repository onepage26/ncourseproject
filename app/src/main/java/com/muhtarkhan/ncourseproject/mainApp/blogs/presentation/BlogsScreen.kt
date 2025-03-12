package com.muhtarkhan.ncourseproject.mainApp.blogs.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.mainApp.BottomNavBar.BottomNavBar
import com.muhtarkhan.ncourseproject.mainApp.blogs.domain.model.Blogs
import com.muhtarkhan.ncourseproject.ui.theme.LocalColors
import com.muhtarkhan.ncourseproject.ui.theme.LocalTypography

@Composable
fun BlogsScreen(
    state: BlogsUIState,
    onHome: () -> Unit,
    onCourse: () -> Unit,
    onReport: () -> Unit,
    onBlog: () -> Unit,
    onEvent: (BlogsEvent) -> Unit
) {
    var selectedTab by remember { mutableStateOf("blog") }
    Scaffold(
        topBar = { Header(onEvent) },
        bottomBar = { BottomNavBar(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            onHome = onHome,
            onCourse = onCourse,
            onReport = onReport,
            onBlog = onBlog
        ) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                when {
                    state.isLoading -> CircularProgressIndicator()
                    state.error != null -> Text(
                        text = "Error: ${state.error}",
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp),
                        style = LocalTypography.current.bodyLarge
                    )
                    state.blogsData?.isNotEmpty() == true -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(state.blogsData, key = { it.id }) { blog ->
                                BlogsItem(blog)
                            }
                        }
                    }
                    else -> Text(
                        text = "No Blogs Available",
                        modifier = Modifier.padding(16.dp),
                        style = LocalTypography.current.bodyLarge
                    )
                }
            }
        }
    )
}

@Composable
fun BlogsItem(blog: Blogs) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = blog.imageUrl,
                contentDescription = "Blog Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = blog.title, style = LocalTypography.current.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = blog.shortDescription, style = LocalTypography.current.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = blog.fullText, style = LocalTypography.current.bodyMedium)
        }
    }
}

@Composable
fun Header(onEvent: (BlogsEvent) -> Unit){
    Row(
        modifier = Modifier
            .background(LocalColors.current.white)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Image(
            painter = painterResource(R.drawable.logout),
            contentDescription = "logo",
            modifier = Modifier.size(40.dp)
                .clickable { onEvent(BlogsEvent.onLogOut) }
        )
    }
}
