package com.wildmouse.calender.controller

import com.wildmouse.calender.entity.AdditionalInformation
import com.wildmouse.calender.entity.Category
import com.wildmouse.calender.entity.Schedule
import com.wildmouse.calender.repository.AdditionalInformationRepository
import com.wildmouse.calender.repository.CategoryRepository
import com.wildmouse.calender.repository.ScheduleRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(
        val additionalInformationRepository: AdditionalInformationRepository,
        val categoryRepository: CategoryRepository,
        val scheduleRepository: ScheduleRepository
) {

    @GetMapping("/schedules")
    fun getSchedules(): List<Schedule> = scheduleRepository.findAll()

    @GetMapping("/additional-information")
    fun getAdditionalInformation(): List<AdditionalInformation> = additionalInformationRepository.findAll()

    @GetMapping("/categories")
    fun getCategories(): List<Category> = categoryRepository.findAll()
}