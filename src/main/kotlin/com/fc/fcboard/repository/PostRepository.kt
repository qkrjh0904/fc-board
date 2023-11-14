package com.fc.fcboard.repository

import com.fc.fcboard.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
}
