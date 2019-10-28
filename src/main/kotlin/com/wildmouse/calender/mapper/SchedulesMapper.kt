package com.wildmouse.calender.mapper

import com.wildmouse.calender.domain.entity.Schedule
import org.apache.ibatis.annotations.Mapper

@Mapper
interface SchedulesMapper {

    fun getSchedules(): List<Schedule>
}