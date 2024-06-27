package org.runhi.auth.exception

import org.springframework.http.HttpStatus

open class RunHiException(
    private val status: HttpStatus,
    override val message: String,
) : RuntimeException()
