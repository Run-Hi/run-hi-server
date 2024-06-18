package org.runhi.marathon.domain.repository

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.runhi.marathon.MarathonAndAverageStar
import org.runhi.marathon.QMarathonAndAverageStar
import org.runhi.marathon.domain.Filter
import org.runhi.marathon.domain.Filtering
import org.runhi.marathon.domain.Filtering.EASY
import org.runhi.marathon.domain.Filtering.HARD
import org.runhi.marathon.domain.Filtering.STAR_HIGH
import org.runhi.marathon.domain.Filtering.STAR_LOW
import org.runhi.marathon.domain.QMarathon.marathon
import org.runhi.review.domain.QAverageStar.averageStar
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class CustomMarathonRepository(
    val jpaQueryFactory: JPAQueryFactory,
) {
    fun findAllByNameIncluded(name: String): MutableList<MarathonAndAverageStar> {
        return jpaQueryFactory.select(
            QMarathonAndAverageStar(
                marathon,
                averageStar,
            ),
        )
            .from(averageStar)
            .rightJoin(averageStar.marathon, marathon)
            .where(marathon.name.like("%$name%"))
            .fetch()
    }

    fun findAllByFilter(filter: Filter): MutableList<MarathonAndAverageStar> {
        return jpaQueryFactory.select(
            QMarathonAndAverageStar(
                marathon,
                averageStar,
            ),
        )
            .from(averageStar)
            .leftJoin(averageStar.marathon, marathon)
            .where(
                acceptingFilter(filter.accepting),
                locationFilter(filter.location),
            )
            .orderBy(filtering(filter.filtering))
            .fetch()
    }

    private fun acceptingFilter(accepting: Boolean?): BooleanExpression? {
        if (accepting == true) {
            return marathon.startAcceptingDate.loe(LocalDate.now())
                .and(marathon.endAcceptingDate.goe(LocalDate.now()))
        }
        return null
    }

    private fun locationFilter(location: String?): BooleanExpression? {
        if (!location.isNullOrEmpty() && location != "전체") {
            return marathon.location.eq(location)
        }
        return null
    }

    private fun filtering(filtering: Filtering?): OrderSpecifier<*>? {
        return when (filtering) {
            STAR_HIGH -> averageStar.total.desc()
            STAR_LOW -> averageStar.total.asc()
            HARD -> averageStar.level.desc()
            EASY -> averageStar.level.asc()
            else -> marathon.conductDate.desc()
        }
    }

    fun findById(id: Long): MarathonAndAverageStar {
        val marathon =
            jpaQueryFactory.select(
                QMarathonAndAverageStar(
                    marathon,
                    averageStar,
                ),
            )
                .from(averageStar)
                .leftJoin(averageStar.marathon, marathon)
                .where(marathon.id.eq(id))
                .fetch()

        if (marathon.isEmpty()) {
            throw RuntimeException("존재하지 않습니다!")
        }

        return marathon[0]
    }
}
