package com.wildmouse.calender.mapper

import com.wildmouse.calender.entity.Schedule
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface SchedulesMapper {

    @Select("SELECT * FROM schedules")
    fun getSchedules(): List<Schedule>

}