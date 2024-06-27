package org.runhi.auth.exception

class TokenNotExistException : RunHiException(org.springframework.http.HttpStatus.FORBIDDEN, "토큰이 넘어오지 않았습니다.")
