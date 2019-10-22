package com.wildmouse.calender.mapper

import com.wildmouse.calender.entity.Category
import org.apache.ibatis.annotations.Mapper

@Mapper
interface CategoriesMapper {

    fun getCategories(): List<Category>

}