package com.fc.fcboard.controller.model

import com.fc.fcboard.service.dto.PostCreateRqDto

data class PostCreateRq(
    val title: String,
    val content: String,
    val createdBy: String,
)

fun PostCreateRq.toDto() = PostCreateRqDto(
    title = title,
    content = content,
    createdBy = createdBy,
)
