package com.muhtarkhan.ncourseproject.mainApp.home.presentation

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.muhtarkhan.ncourseproject.mainApp.BottomNavBar.BottomNavBar
import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.mainApp.courses.presentation.FilterRow
import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.Banner
import com.muhtarkhan.ncourseproject.mainApp.home.domain.model.Course
import com.muhtarkhan.ncourseproject.ui.theme.LocalColors
import com.muhtarkhan.ncourseproject.ui.theme.LocalTypography

@Composable
fun HomeScreen(
    state: HomeUiState,
    onHome: () -> Unit,
    onCourse: () -> Unit,
    onReport: () -> Unit,
    onBlog: () -> Unit,
    onEvent: (HomeEvent) -> Unit
) {
    var selectedTab by remember { mutableStateOf("Home") }
    Scaffold(
        topBar = { Header(onEvent) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Текстовый блок и кнопка
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LocalColors.current.gray800)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Помогаем каждому начать успешный путь в IT",
                        style = LocalTypography.current.titleLarge,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(containerColor = LocalColors.current.primary)
                    ) {
                        Text(text = "Оформить заявку", color = LocalColors.current.white)
                    }
                }


                when {
                    state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    state.error != null -> Text(
                        text = "Error: ${state.error}",
                        color = LocalColors.current.primary,
                        modifier = Modifier.align(Alignment.Center))
                    state.mainData != null -> {
                        val banners = state.mainData.banners
                        val courses = state.mainData.courses

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 200.dp)
                        ) {
                            items(banners, key = { it.imageUrl }) { banner ->
                                BannerItem(banner)
                            }
                            item {
                                FilterRow(
                                    selectedFilter = state.selectedFilter,
                                    onFilterSelected = { onEvent(HomeEvent.OnFilterSelected(it)) }
                                )
                            }
                            items(courses, key = { it.id }) { course ->
                                CourseItem(course)
                            }


                        }
                    }
                    else -> Text(
                        text = "No data available",
                        modifier = Modifier.align(Alignment.Center))
                }
            }
        },
        bottomBar = {
            BottomNavBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                onHome = onHome,
                onCourse = onCourse,
                onReport = onReport,
                onBlog = onBlog
            )
        }
    )
}

@Composable
fun BannerItem(banner: Banner) {

    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 12.dp).height(200.dp)) {
        AsyncImage(
            model = banner.imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = banner.text,
            style = LocalTypography.current.bodyLarge,
            modifier = Modifier.align(Alignment.BottomStart).padding(16.dp),
            color = LocalColors.current.white
        )
    }
}

@Composable
fun CourseItem(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Box(modifier = Modifier.height(220.dp)) {
            AsyncImage(
                model = course.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = course.name,
                    style = LocalTypography.current.bodyLarge.copy(color = Color.White),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "от ${course.price} ₸",
                    style = LocalTypography.current.bodyMedium.copy(color = Color.White)
                )
                Text(
                    text = "${course.duration} недель",
                    style = LocalTypography.current.bodySmall.copy(color = Color.White)
                )
            }
        }
    }
}



@Composable
fun Header(onEvent: (HomeEvent) -> Unit){
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
                .clickable { onEvent(HomeEvent.onLogOut) }
        )
    }
}

