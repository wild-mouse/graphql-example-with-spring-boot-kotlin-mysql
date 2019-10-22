package com.wildmouse.calender.mapper

import com.wildmouse.calender.entity.Category
import com.wildmouse.calender.entity.Schedule
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface CategoriesMapper {

    @Select("SELECT * FROM categories")
    fun getCategories(): List<Category>

}