package com.wildmouse.calender.controller

import com.wildmouse.calender.entity.AdditionalInformation
import com.wildmouse.calender.entity.Category
import com.wildmouse.calender.entity.Schedule
import com.wildmouse.calender.mapper.AdditionalInformationMapper
import com.wildmouse.calender.mapper.CategoriesMapper
import com.wildmouse.calender.mapper.SchedulesMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(
        private val additionalInformationMapper: AdditionalInformationMapper,
        private val categoriesMapper: CategoriesMapper,
        private val schedulesMapper: SchedulesMapper
) {

    @GetMapping("/schedules")
    fun getSchedules(): List<Schedule> = schedulesMapper.getSchedules()

    @GetMapping("/additional-information")
    fun getAdditionalInformation(): List<AdditionalInformation> =
            additionalInformationMapper.getAdditionalInformationList()

    @GetMapping("/categories")
    fun getCategories(): List<Category> = categoriesMapper.getCategories()
}