package com.wildmouse.calender.entity

import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name="schedules")
data class Schedule(
        var name: String = "",
        var date: String = "",
        var allDay: String = "",
        @Transient
        var categories: List<Category> = mutableListOf(),
        @Id
        var id: Long = 0L
)