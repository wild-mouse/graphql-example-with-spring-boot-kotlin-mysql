package com.wildmouse.calender.domain.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="additional_information")
data class AdditionalInformation(
        var scheduleId: Long = 0L,
        var name: String = "",
        var value: String = "",
        @Id
        var id: Long = 0L
)