package com.fc.fcboard.service

import com.fc.fcboard.domain.Post
import com.fc.fcboard.exception.PostNotFoundException
import com.fc.fcboard.repository.PostRepository
import com.fc.fcboard.service.dto.PostCreateRqDto
import com.fc.fcboard.service.dto.PostUpdateRqDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class PostServiceTest(
    private val postService: PostService,
    private val postRepository: PostRepository,
) : BehaviorSpec({

    beforeSpec {
        postRepository.saveAll(
            listOf(
                Post(createdBy = "createdBy", title = "title1", content = "content1"),
                Post(createdBy = "createdBy", title = "title2", content = "content2"),
                Post(createdBy = "createdBy", title = "title3", content = "content3"),
                Post(createdBy = "createdBy", title = "title4", content = "content4"),
                Post(createdBy = "createdBy", title = "title5", content = "content5"),
                Post(createdBy = "createdBy", title = "title6", content = "content6"),
                Post(createdBy = "createdBy", title = "title7", content = "content7"),
                Post(createdBy = "createdBy", title = "title8", content = "content8"),
                Post(createdBy = "createdBy", title = "title9", content = "content9"),
                Post(createdBy = "createdBy", title = "title10", content = "content10"),
                Post(createdBy = "createdBy", title = "title11", content = "content11"),
                Post(createdBy = "createdBy", title = "title12", content = "content12"),
                Post(createdBy = "createdBy", title = "title13", content = "content13"),
                Post(createdBy = "createdBy", title = "title14", content = "content14"),
                Post(createdBy = "createdBy", title = "title15", content = "content15"),
                Post(createdBy = "createdBy", title = "title16", content = "content16"),
            )
        )
    }

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
                shouldThrow<PostNotFoundException> {
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

    given("게시글 삭제시") {
        val postId = postRepository.save(
            Post(
                createdBy = "createdBy",
                title = "title",
                content = "content",
            )
        ).id

        When("정상 삭제시") {
            postService.deletePost(postId)
            then("게시글이 정상적으로 삭제됨을 확인한다.") {
                val post = postRepository.findByIdOrNull(postId)
                post shouldBe null
            }
        }

        When("게시글이 없을 때") {
            then("게시글이 없다는 에러가 발생한다.") {
                shouldThrow<PostNotFoundException> {
                    postService.deletePost(9999L)
                }
            }
        }
    }

    given("게시글 목록 조회시") {
        When("정상 조회시") {
            val postpage = postService.findPageBy(PageRequest.of(0, 5))
            then("게시글 페이지가 반환된다.") {
                postpage.number shouldBe 0
                postpage.size shouldBe 5
                postpage.content.size shouldBe 5
                postpage.content[0].title shouldContain "title"
                postpage.content[0].createdBy shouldContain "createdBy"
            }
        }
    }
})
