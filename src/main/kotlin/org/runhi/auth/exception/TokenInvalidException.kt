package org.runhi.auth.exception

class TokenInvalidException : RunHiException(org.springframework.http.HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다.")
