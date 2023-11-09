package com.fc.fcboard.controller.model

import java.time.LocalDateTime

data class PostDetailRs(
    val id: Long,
    val title: String,
    val content: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
)
