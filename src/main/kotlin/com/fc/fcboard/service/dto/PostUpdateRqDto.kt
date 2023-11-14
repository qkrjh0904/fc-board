package com.fc.fcboard.service.dto

data class PostUpdateRqDto(
    val id: Long,
    val title: String,
    val content: String,
    val updatedBy: String,
)
