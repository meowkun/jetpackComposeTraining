package com.example.jetpackcomposetraining.profile_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcomposetraining.profile_card.ProfileCardAppBar
import com.example.jetpackcomposetraining.profile_card.ProfileContent
import com.example.jetpackcomposetraining.profile_card.ProfilePicture
import com.example.jetpackcomposetraining.profile_card.userProfileList
import com.example.jetpackcomposetraining.ui.theme.ProfileCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetailsScreen(
    userId: Int,
    navController: NavController?
) {
    val userProfile = userProfileList.first { it.userId == userId }
    ProfileCardTheme {
        Scaffold(topBar = {
            ProfileCardAppBar(
                title = "User Profile",
                icon = Icons.Default.ArrowBack
            ) {
                navController?.navigateUp()
            }
        }) { padding ->
            Surface(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfilePicture(
                        imageUrl = userProfile.imageUrl,
                        status = userProfile.status,
                        imageSize = 240.dp
                    )
                    ProfileContent(
                        userName = userProfile.userName,
                        status = userProfile.status
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDetailsScreenPreview() {
    ProfileDetailsScreen(
        userId = 0,
        navController = null
    )
}