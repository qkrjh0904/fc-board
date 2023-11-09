package com.fc.fcboard.controller.model

import java.time.LocalDateTime

data class PostSummaryRs(
    val id: Long,
    val title: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
)
