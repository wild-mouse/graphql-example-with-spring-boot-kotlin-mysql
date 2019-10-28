package com.wildmouse.calender.mapper

import com.wildmouse.calender.domain.entity.Category
import org.apache.ibatis.annotations.Mapper

@Mapper
interface CategoriesMapper {

    fun getCategories(): List<Category>

    fun getCategoriesByScheduleId(scheduleId: Long): List<Category>

    fun addCategory(category: Category)
}