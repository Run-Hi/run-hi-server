package org.runhi.user.presentation

import org.runhi.auth.annotation.LoginRequired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController {
    @GetMapping
    @LoginRequired
    fun readByCurrent() {
    }
}
