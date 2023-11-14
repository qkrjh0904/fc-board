package com.fc.fcboard.service

import com.fc.fcboard.domain.Post
import com.fc.fcboard.repository.PostRepository
import com.fc.fcboard.service.dto.PostCreateRqDto
import com.fc.fcboard.service.dto.PostUpdateRqDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class PostServiceTest(
    private val postService: PostService,
    private val postRepository: PostRepository,
) : BehaviorSpec({

    given("게시글 생성시") {
        When("게시글 인풋이 정상적으로 들어오면") {
            val postId = postService.createPost(
                PostCreateRqDto(
                    title = "title",
                    content = "content",
                    createdBy = "createdBy",
                )
            )
            then("게시글이 정상적으로 생성됨을 확인한다.") {
                postId shouldBeGreaterThan 0
                val post = postRepository.findByIdOrNull(postId)
                post shouldNotBe null

                post?.title shouldBe "title"
                post?.content shouldBe "content"
                post?.createdBy shouldBe "createdBy"
            }
        }
    }

    given("게시글 수정시") {
        val postId = postRepository.save(
            Post(
                createdBy = "createdBy",
                title = "title",
                content = "content",
            )
        ).id

        When("게시글 인풋이 정상적으로 들어오면") {
            postService.updatePost(
                PostUpdateRqDto(
                    id = postId,
                    title = "title2",
                    content = "content2",
                    updatedBy = "updatedBy",
                )
            )
            then("게시글이 정상적으로 수정됨을 확인한다.") {
                val post = postRepository.findByIdOrNull(postId)
                post shouldNotBe null

                post?.title shouldBe "title2"
                post?.content shouldBe "content2"
                post?.updatedBy shouldBe "updatedBy"
            }
        }

        When("게시글이 없을 때") {
            then("게시글이 없다는 에러가 발생한다.") {
                shouldThrow<NoSuchElementException> {
                    postService.updatePost(
                        PostUpdateRqDto(
                            id = 9999L,
                            title = "title2",
                            content = "content2",
                            updatedBy = "updatedBy",
                        )
                    )
                }
            }
        }
    }
})
