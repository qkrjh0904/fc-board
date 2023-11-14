package com.fc.fcboard.service

import com.fc.fcboard.controller.model.PostSummaryRs
import com.fc.fcboard.exception.PostNotFoundException
import com.fc.fcboard.repository.PostRepository
import com.fc.fcboard.service.dto.PostCreateRqDto
import com.fc.fcboard.service.dto.PostSummaryRsDto
import com.fc.fcboard.service.dto.PostUpdateRqDto
import com.fc.fcboard.service.dto.toEntity
import com.fc.fcboard.service.dto.toSummaryRsDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
) {

    @Transactional
    fun createPost(postCreateRqDto: PostCreateRqDto): Long {
        val post = postCreateRqDto.toEntity()
        return postRepository.save(post).id
    }

    @Transactional
    fun updatePost(dto: PostUpdateRqDto) {
        val post = postRepository.findByIdOrNull(dto.id) ?: throw PostNotFoundException()
        post.update(dto.title, dto.content, dto.updatedBy)
    }

    @Transactional
    fun deletePost(postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw PostNotFoundException()
        postRepository.delete(post)
    }

    fun findPageBy(pageRequest: Pageable): Page<PostSummaryRsDto> {
        return postRepository.findAll(pageRequest).toSummaryRsDto()
    }
}
