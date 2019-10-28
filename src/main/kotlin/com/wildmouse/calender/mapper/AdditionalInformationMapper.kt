package com.wildmouse.calender.mapper

import com.wildmouse.calender.domain.entity.AdditionalInformation
import org.apache.ibatis.annotations.Mapper

@Mapper
interface AdditionalInformationMapper {

    fun getAdditionalInformationList(): List<AdditionalInformation>

    fun getAdditionalInformationListByScheduleId(scheduleId: Long): List<AdditionalInformation>

}