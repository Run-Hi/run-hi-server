package org.runhi.auth.exception

import org.springframework.http.HttpStatus

class NotBssmEmailException(email: String) : RunHiException(HttpStatus.BAD_REQUEST, email + "은 Bssm 이메일이 아닙니다.")
