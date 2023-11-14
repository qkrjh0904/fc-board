package com.fc.fcboard.service.dto

import com.fc.fcboard.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

data class PostSummaryRsDto(
    val id: Long,
    val title: String,
    val createdBy: String,
    val createdAt: String,
)

fun Page<Post>.toSummaryRsDto() = PageImpl(
    this.content.map { it.toSummaryRsDto() },
    this.pageable,
    this.totalElements,
)

fun Post.toSummaryRsDto() = PostSummaryRsDto(
    id = id,
    title = title,
    createdBy = createdBy,
    createdAt = createdAt.toString(),
)
