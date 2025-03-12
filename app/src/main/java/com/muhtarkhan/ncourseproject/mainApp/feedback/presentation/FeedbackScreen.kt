package com.muhtarkhan.ncourseproject.mainApp.feedback.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.muhtarkhan.ncourseproject.mainApp.courses.presentation.CatalogEvent
import com.muhtarkhan.ncourseproject.mainApp.feedback.domain.model.Feedback

import com.muhtarkhan.ncourseproject.mainApp.home.presentation.Header
import com.muhtarkhan.ncourseproject.mainApp.home.presentation.HomeEvent
import com.muhtarkhan.ncourseproject.ui.theme.LocalColors
import com.muhtarkhan.ncourseproject.ui.theme.LocalTypography

@Composable
fun FeedbackScreen(
    state: FeedbackUiState,
    onHome: () -> Unit,
    onCourse: () -> Unit,
    onReport: () -> Unit,
    onBlog: () -> Unit,
    onLogOut: (FeedbackEvent) -> Unit

    ) {
    var selectedTab by remember { mutableStateOf("feedback") }
    Scaffold(
        topBar = {
            Header(onLogOut)
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                when {
                    state.isLoading -> CircularProgressIndicator()
                    state.error != null -> Text(text = "Error: ${state.error}", color = Color.Red)
                    state.feedbackData != null -> {
                        val feedbacks = state.feedbackData.feedbacks

                        LazyColumn {
                            items(feedbacks, key = { it.id }) { feedback ->
                                FeedbackItem(feedback)
                            }
                        }
                    }
                    else -> Text(text = "No feedback available")
                }
            }
        },

        bottomBar = { BottomNavBar(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            onHome = onHome,
            onCourse = onCourse,
            onReport = onReport,
            onBlog = onBlog
        ) }
    )
}

@Composable
fun FeedbackItem(feedback: Feedback) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = feedback.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = feedback.name, style = LocalTypography.current.bodyLarge)
            Text(text = "From: ${feedback.previousOccupation}", style = LocalTypography.current.bodyMedium)
            Text(text = "To: ${feedback.currentOccupation}", style = LocalTypography.current.bodyMedium)
            Text(text = "Course: ${feedback.course}", style = LocalTypography.current.bodySmall)
            Text(text = feedback.feedback, style = LocalTypography.current.bodyMedium)
        }
    }
}

@Composable
fun Header(onEvent: (FeedbackEvent) -> Unit){
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
                .clickable { onEvent(FeedbackEvent.onLogOut) }
        )
    }
}