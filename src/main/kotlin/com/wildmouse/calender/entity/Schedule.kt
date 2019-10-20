package com.wildmouse.calender.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="schedules")
data class Schedule(
        var name: String = "",
        var date: String = "",
        var allDay: String = "",
        @Id
        var id: Long = 0L
)