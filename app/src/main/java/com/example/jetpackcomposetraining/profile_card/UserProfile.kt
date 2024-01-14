package com.example.jetpackcomposetraining.profile_card

import com.example.jetpackcomposetraining.R

class UserProfile(
    val userName: String,
    val status: Status,
    val iconId: Int
)

enum class Status {
    ONLINE,
    OFFLINE
}

val userProfileList = arrayListOf(
    UserProfile(
        userName = "User_1",
        status = Status.ONLINE,
        iconId = R.drawable.profile_picture
    ),
    UserProfile(
        userName = "User_2",
        status = Status.OFFLINE,
        iconId = R.drawable.profile_picture2
    )
)