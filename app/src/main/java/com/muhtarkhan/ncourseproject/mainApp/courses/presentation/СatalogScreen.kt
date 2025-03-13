package com.muhtarkhan.ncourseproject.mainApp.courses.presentation

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.muhtarkhan.ncourseproject.R
import com.muhtarkhan.ncourseproject.mainApp.BottomNavBar.BottomNavBar
import com.muhtarkhan.ncourseproject.mainApp.courses.domain.model.Course
import com.muhtarkhan.ncourseproject.ui.theme.LocalColors
import com.muhtarkhan.ncourseproject.ui.theme.LocalTypography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    state: CatalogUiState,
    onHome: () -> Unit,
    onCourse: () -> Unit,
    onReport: () -> Unit,
    onBlog: () -> Unit,
    onLogOut: (CatalogEvent) -> Unit,
    onEvent: (CatalogEvent) -> Unit
) {
    var selectedTab by remember { mutableStateOf("course") }
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { Header(onLogOut) },
        bottomBar = {
            BottomNavBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                onHome = onHome,
                onCourse = onCourse,
                onReport = onReport,
                onBlog = onBlog
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                FilterRow(
                    selectedFilter = state.selectedFilter,
                    onFilterSelected = { onEvent(CatalogEvent.OnFilterSelected(it)) }
                )

                Box(modifier = Modifier.weight(1f)) {
                    when {
                        state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        state.error != null -> Text(
                            text = "Error: ${state.error}",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp),
                            style = LocalTypography.current.bodyLarge
                        )
                        state.catalogData.isNotEmpty() -> {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(state.catalogData, key = { it.id }) { course ->
                                    CourseItem(course)
                                }
                            }
                        }
                        else -> Text(
                            text = "No Courses Available",
                            modifier = Modifier.padding(16.dp),
                            style = LocalTypography.current.bodyLarge
                        )
                    }
                }
                CTAButton(onClick = { isSheetOpen = true })

                if (state.isConsultationSent) {
                    Text("Запрос успешно отправлен!", color = LocalColors.current.primary, modifier = Modifier.padding(16.dp))
                }
            }
        }
    )

    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen = false },
            sheetState = sheetState
        ) {
            BottomSheetContent(
                onClose = { isSheetOpen = false },
                onEvent = onEvent
            )
        }
    }
}

@Composable
fun BottomSheetContent(onClose: () -> Unit, onEvent: (CatalogEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Оформление заявки", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Имя") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Телефон") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onEvent(CatalogEvent.SendConsultation(name, email, phone))
                onClose()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Отправить")
        }
    }
}

@Composable
fun FilterRow(selectedFilter: String, onFilterSelected: (String) -> Unit) {
    LazyRow(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        val filters = listOf("Все", "С нуля", "С базой в программировании")
        items(filters) { filter ->
            FilterChip(
                label = filter,
                isSelected = filter == selectedFilter,
                onClick = { onFilterSelected(filter) }
            )
        }
    }
}

@Composable
fun FilterChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = label,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(
                color = if (isSelected) Color.Red else Color.LightGray,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        color = if (isSelected) Color.White else Color.Black
    )
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
fun CTAButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Оформить заявку", color = Color.White)
        }
    }
}

@Composable
fun Header(onEvent: (CatalogEvent) -> Unit){
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
                .clickable { onEvent(CatalogEvent.OnLogOut) }
        )
    }
}