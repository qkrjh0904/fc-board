package com.fc.fcboard.controller.model

import org.springframework.web.bind.annotation.RequestParam

data class PostSearchRq(
    @RequestParam
    val title: String?,
    @RequestParam
    val createdBy: String?,
)
