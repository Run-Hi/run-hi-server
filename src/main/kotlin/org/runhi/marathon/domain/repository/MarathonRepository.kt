package org.runhi.marathon.domain.repository

import org.runhi.marathon.domain.Marathon
import org.springframework.data.jpa.repository.JpaRepository

interface MarathonRepository : JpaRepository<Marathon, Long>
