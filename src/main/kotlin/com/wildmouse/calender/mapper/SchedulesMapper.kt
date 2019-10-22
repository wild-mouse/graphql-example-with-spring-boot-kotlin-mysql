package com.wildmouse.calender.mapper

import com.wildmouse.calender.entity.Schedule
import org.apache.ibatis.annotations.Mapper

@Mapper
interface SchedulesMapper {

    fun getSchedules(): List<Schedule>
}