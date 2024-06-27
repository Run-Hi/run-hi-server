package org.runhi.auth.exception

class TokenExpiredException : RunHiException(org.springframework.http.HttpStatus.FORBIDDEN, "토큰이 만료되었습니다.")
