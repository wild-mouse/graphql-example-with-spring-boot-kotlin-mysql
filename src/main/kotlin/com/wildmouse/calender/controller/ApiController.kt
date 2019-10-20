package com.wildmouse.calender.controller

import com.wildmouse.calender.entity.Schedule
import com.wildmouse.calender.repository.ScheduleRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(val scheduleRepository: ScheduleRepository) {

    @GetMapping("/schedules")
    fun getSchedules(): List<Schedule> = scheduleRepository.findAll()

}