package org.runhi.auth.exception

class UserNotLoginException : RunHiException(org.springframework.http.HttpStatus.FORBIDDEN, "유저가 로그인하지 않았습니다.")
