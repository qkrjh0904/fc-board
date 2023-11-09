package com.fc.fcboard.controller.model

data class PostCreateRq(
    val title: String,
    val content: String,
    val createdBy: String,
)
