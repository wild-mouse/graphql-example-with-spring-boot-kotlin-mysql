package com.wildmouse.calender.mutators

import com.wildmouse.calender.domain.entity.Category
import com.wildmouse.calender.mapper.CategoriesMapper
import graphql.schema.DataFetcher
import org.springframework.stereotype.Component

@Component
class CategoryMutators(
        private val categoriesMapper: CategoriesMapper
) {
    internal val addCategory: DataFetcher<Category> =
            DataFetcher { dataFetchingEnvironment ->
                val name = dataFetchingEnvironment.getArgument<String>("name")
                val category = Category(name)
                categoriesMapper.addCategory(category)
                category
            }
}