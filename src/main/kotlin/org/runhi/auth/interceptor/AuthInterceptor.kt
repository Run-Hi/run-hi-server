package org.runhi.auth.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.runhi.auth.annotation.AdminOnly
import org.runhi.auth.annotation.LoginOrNot
import org.runhi.auth.annotation.LoginRequired
import org.runhi.auth.exception.TokenNotExistException
import org.runhi.auth.exception.UserIsNotAdminException
import org.runhi.auth.service.implementation.AuthReader
import org.runhi.auth.service.implementation.AuthUpdater
import org.runhi.auth.util.BearerTokenExtractor
import org.runhi.auth.util.JwtParser
import org.runhi.user.domain.User
import org.runhi.user.domain.role.Role
import org.runhi.user.service.implementation.UserReader
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Configuration
class AuthInterceptor(
    private val jwtParser: JwtParser,
    private val authUpdater: AuthUpdater,
    private val authReader: AuthReader,
    private val userReader: UserReader,
) : HandlerInterceptor {
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        if (handler is HandlerMethod) {
            if (handler.hasMethodAnnotation(LoginOrNot::class.java)) {
                val bearer: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

                if (bearer != null) {
                    val jwt: String = BearerTokenExtractor.extract(bearer)
                    val userId: Long = jwtParser.getIdFromJwt(jwt)

                    val user: User = userReader.readUser(userId)

                    authUpdater.updateCurrentUser(user)
                }
            }

            if (handler.hasMethodAnnotation(LoginRequired::class.java)) {
                if (authReader.nullableCurrentUser == null) {
                    throw TokenNotExistException()
                }
            }
            if (handler.hasMethodAnnotation(AdminOnly::class.java)) {
                val currentUser: User = authReader.currentUser
                shouldUserAdmin(currentUser)
            }
        }
        return true
    }

    companion object {
        private fun shouldUserAdmin(currentUser: User) {
            if (currentUser.role !== Role.ADMIN) {
                throw UserIsNotAdminException()
            }
        }
    }
}
