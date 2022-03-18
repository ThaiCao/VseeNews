package com.vsee.news.domain.features

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }

        fun isLoading(value: Status?): Boolean {
            return if (value == null) {
                true
            } else {
                value == Status.LOADING
            }
        }

        fun isNotLoading(value: Status?): Boolean {
            return value == Status.SUCCESS || value == Status.ERROR
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}