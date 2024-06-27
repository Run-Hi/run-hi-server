package org.runhi.auth.util

import org.runhi.auth.exception.TokenInvalidException
import org.runhi.auth.exception.TokenMissingException

object BearerTokenExtractor {
    private const val BEARER_TYPE = "Bearer "
    private const val BEARER_JWT_REGEX = "^Bearer [A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$"

    fun extract(bearer: String): String {
        validate(bearer)
        return bearer.replace(BEARER_TYPE, "").trim { it <= ' ' }
    }

    private fun validate(authorization: String?) {
        if (authorization == null) {
            throw TokenMissingException()
        }
        if (!authorization.matches(BEARER_JWT_REGEX.toRegex())) {
            throw TokenInvalidException()
        }
    }
}
