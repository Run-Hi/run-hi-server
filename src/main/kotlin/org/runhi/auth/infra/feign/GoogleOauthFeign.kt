package org.runhi.auth.infra.feign

import org.runhi.auth.infra.feign.dto.GoogleUserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "GoogleOauthFeign", url = "https://www.googleapis.com/oauth2/v1/userinfo")
interface GoogleOauthFeign {
    @GetMapping
    fun getInfo(
        @RequestParam(name = "access_token") accessToken: String,
    ): GoogleUserInfoResponse
}
