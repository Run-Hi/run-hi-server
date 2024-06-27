package org.runhi.auth.exception

class UserIsNotAdminException : RunHiException(org.springframework.http.HttpStatus.UNAUTHORIZED, "사용자가 어드민이 아닙니다.")
