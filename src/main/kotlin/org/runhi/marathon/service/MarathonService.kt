package org.runhi.marathon.service

import org.runhi.marathon.MarathonAndAverageStar
import org.runhi.marathon.domain.Filter
import org.runhi.marathon.domain.repository.CustomMarathonRepository
import org.runhi.marathon.domain.repository.MarathonRepository
import org.springframework.stereotype.Service

@Service
class MarathonService(
    private val customMarathonRepository: CustomMarathonRepository,
    private val marathonRepository: MarathonRepository,
) {
    fun getListBySearching(name: String): MutableList<MarathonAndAverageStar> {
        return customMarathonRepository.findAllByNameIncluded(name)
    }

    fun getListByFiltering(filter: Filter): MutableList<MarathonAndAverageStar> {
        return customMarathonRepository.findAllByFilter(filter)
    }

    fun getOneById(id: Long): MarathonAndAverageStar {
        return customMarathonRepository.findById(id)
    }

    fun getMarathonNameById(id: Long): String {
        val findById =
            marathonRepository.findById(id)
                .orElseThrow { RuntimeException("Id Not Found") }

        return findById.name
    }
}
