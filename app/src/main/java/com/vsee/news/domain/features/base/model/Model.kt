package com.vsee.news.domain.features.base.model

interface Model {
    fun toLocalDto(): Dto
    fun toRemoteDto(): Dto
}