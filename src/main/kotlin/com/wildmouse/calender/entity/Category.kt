package com.wildmouse.calender.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="categories")
data class Category(
        var name: String = "",
        @Id
        var id: Long = 0L
)