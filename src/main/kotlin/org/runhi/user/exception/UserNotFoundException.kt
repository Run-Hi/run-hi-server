package org.runhi.user.exception

import org.runhi.auth.exception.RunHiException
import org.springframework.http.HttpStatus

class UserNotFoundException : RunHiException {
    constructor(email: String) : super(HttpStatus.NOT_FOUND, email + "이 이메일인 학생이 없습니다.")

    constructor(id: Long) : super(HttpStatus.NOT_FOUND, id.toString() + "인 아이디가 학생이 없습니다.")
}
