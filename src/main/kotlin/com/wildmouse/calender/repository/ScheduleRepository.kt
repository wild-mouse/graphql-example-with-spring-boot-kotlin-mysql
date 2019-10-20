package com.wildmouse.calender.repository

import com.wildmouse.calender.entity.Schedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScheduleRepository: JpaRepository<Schedule, Long>
