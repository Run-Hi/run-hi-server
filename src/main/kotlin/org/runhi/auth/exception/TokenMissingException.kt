package org.runhi.auth.exception

class TokenMissingException : RunHiException(org.springframework.http.HttpStatus.UNAUTHORIZED, "토큰이 없습니다.")
