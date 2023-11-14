package com.fc.fcboard.service

import com.fc.fcboard.exception.CannotFindPostException
import com.fc.fcboard.repository.PostRepository
import com.fc.fcboard.service.dto.PostCreateRqDto
import com.fc.fcboard.service.dto.PostUpdateRqDto
import com.fc.fcboard.service.dto.toEntity
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
        val post = postRepository.findById(dto.id).orElseThrow()
        post?.update(dto.title, dto.content, dto.updatedBy)
    }
}
