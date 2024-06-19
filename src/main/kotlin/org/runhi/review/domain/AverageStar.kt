package org.runhi.review.domain

import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import org.runhi.common.BaseGenerateId
import org.runhi.marathon.domain.Marathon

@Entity
class AverageStar(
    var total: Float,
    var level: Float,
    var track: Float,
    var operate: Float,
    var waterStation: Float,
    var souvenir: Float,
    var medal: Float,
    var count: Int,
    @OneToOne
    val marathon: Marathon,
) : BaseGenerateId() {
    fun updateReview(review: Review) {
        if (count == 0) {
            this.level = review.level.toFloat()
            this.track += review.track.toFloat()
            this.operate += review.operate.toFloat()
            this.waterStation += review.waterStation.toFloat()
            this.souvenir += review.souvenir.toFloat()
            this.medal += review.medal.toFloat()
        } else {
            this.level = (level * count + review.level) / (count + 1)
            this.track = (track * count + review.track) / (count + 1)
            this.operate = (operate * count + review.operate) / (count + 1)
            this.waterStation = (waterStation * count + review.waterStation) / (count + 1)
            this.souvenir = (souvenir * count + review.souvenir) / (count + 1)
            this.medal = (medal * count + review.medal) / (count + 1)
        }
        this.count += 1
        this.total = (track + operate + waterStation + souvenir + medal) / 5
    }
}
