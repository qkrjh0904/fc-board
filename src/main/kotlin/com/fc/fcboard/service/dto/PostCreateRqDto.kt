package com.fc.fcboard.service.dto

import com.fc.fcboard.domain.Post

data class PostCreateRqDto(
    val title: String,
    val content: String,
    val createdBy: String,
)

fun PostCreateRqDto.toEntity() = Post(
    title = title,
    content = content,
    createdBy = createdBy,
)
