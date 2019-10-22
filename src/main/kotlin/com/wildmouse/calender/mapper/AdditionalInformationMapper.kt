package com.wildmouse.calender.mapper

import com.wildmouse.calender.entity.AdditionalInformation
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface AdditionalInformationMapper {

    @Select("SELECT * FROM additional_information")
    fun getAdditionalInformationList(): List<AdditionalInformation>

}