package com.fc.fcboard.controller

import com.fc.fcboard.controller.model.PostCreateRq
import com.fc.fcboard.controller.model.PostDetailRs
import com.fc.fcboard.controller.model.PostSearchRq
import com.fc.fcboard.controller.model.PostSummaryRs
import com.fc.fcboard.controller.model.PostUpdateRq
import com.fc.fcboard.controller.model.toDto
import com.fc.fcboard.service.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class PostController (
    private val postService: PostService,
){

    @PostMapping("/posts")
    fun createPost(@RequestBody postCreateRq: PostCreateRq): Long {
        return postService.createPost(postCreateRq.toDto())
    }

    @PutMapping("/posts/{id}")
    fun updatePost(
        @PathVariable id: Long,
        @RequestBody postCreateRq: PostUpdateRq,
    ): Long {
        return id
    }

    @DeleteMapping("/posts/{id}")
    fun deletePost(
        @PathVariable id: Long,
        @RequestParam createdBy: String,
    ): Long {
        println(createdBy)
        return id
    }

    @GetMapping("/posts/{id}")
    fun getPost(@PathVariable id: Long): PostDetailRs {
        return PostDetailRs(id, "title", "content", "createdBy", LocalDateTime.now())
    }

    @GetMapping("/posts")
    fun getPosts(
        page: Pageable,
        postSearchRq: PostSearchRq,
    ): Page<PostSummaryRs> {
        return Page.empty()
    }
}
