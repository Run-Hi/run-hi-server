package org.runhi.marathon.domain

import org.springframework.web.bind.annotation.RequestParam

class Filter(
    @RequestParam val accepting: Boolean?,
    @RequestParam val location: String?,
    @RequestParam val filtering: Filtering?,
)
