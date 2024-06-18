package org.runhi.marathon.presentation

import org.runhi.marathon.domain.Filter
import org.runhi.marathon.presentation.dto.CardMarathonDto
import org.runhi.marathon.presentation.dto.DetailMarathonDto
import org.runhi.marathon.service.MarathonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("marathons")
class MarathonController(
    private val marathonService: MarathonService,
    service: MarathonService,
) {
    @GetMapping
    fun listCard(filter: Filter): List<CardMarathonDto> {
        return marathonService.getListByFiltering(filter)
            .map { (marathon, averageStar) -> CardMarathonDto(marathon, averageStar) }
    }

    @GetMapping("search")
    fun search(
        @RequestParam name: String,
    ): List<CardMarathonDto> {
        return marathonService.getListBySearching(name)
            .map { (marathon, averageStar) -> CardMarathonDto(marathon, averageStar) }
    }

    @GetMapping("/{id}")
    fun getDetailById(
        @PathVariable id: Long,
    ): DetailMarathonDto {
        val oneById = marathonService.getOneById(id)
        return DetailMarathonDto(oneById.marathon, oneById.averageStar)
    }

    @GetMapping("/name/{id}")
    fun getMarathonName(
        @PathVariable id: Long,
    ): String {
        return marathonService.getMarathonNameById(id)
    }
}
