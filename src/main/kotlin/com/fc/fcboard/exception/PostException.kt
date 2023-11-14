package com.fc.fcboard.exception

open class PostException(message: String) : RuntimeException(message)

class PostNotFoundException : PostException("게시글을 찾을 수 없습니다.")
