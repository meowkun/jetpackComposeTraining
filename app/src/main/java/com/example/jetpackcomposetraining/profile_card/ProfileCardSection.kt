package com.example.jetpackcomposetraining.profile_card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jetpackcomposetraining.profile_card.Status.*
import com.example.jetpackcomposetraining.ui.theme.ProfileCardTheme
import com.example.jetpackcomposetraining.ui.theme.lightGreen
import com.example.jetpackcomposetraining.ui.theme.shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCardMainScreen() {
    ProfileCardTheme {
        Scaffold(topBar = { ProfileCardAppBar() }) { padding ->
            Surface(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                LazyColumn {
                    items(userProfileList) {userprofile ->
                        ProfileCard(userProfile = userprofile)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCardAppBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home button"
                )
            }
        },
        title = { Text(text = "Messaging Application Users") },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile) {
    Card(
        modifier = Modifier
            .padding(
                top = 8.dp,
                bottom = 4.dp,
                start = 16.dp,
                end = 16.dp
            )
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        shape = shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(
                imageUrl = userProfile.imageUrl,
                status = userProfile.status
            )
            ProfileContent(
                userName = userProfile.userName,
                status = userProfile.status
            )
        }

    }
}

@Composable
fun ProfilePicture(imageUrl: String, status: Status) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = when (status) {
                ONLINE -> MaterialTheme.colorScheme.lightGreen
                OFFLINE -> MaterialTheme.colorScheme.error
            }
        ),
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            modifier = Modifier.size(72.dp),
            contentDescription = "Profile picture description",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileContent(userName: String, status: Status) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(
                alpha = when (status) {
                    ONLINE -> 1f
                    OFFLINE -> .5f
                }
            )
        ) {
            Text(
                text = userName,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.secondary) {
            Text(
                text = when (status) {
                    ONLINE -> "Active now"
                    OFFLINE -> "Offline"
                },
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardSectionPreview() {
    ProfileCardMainScreen()
}