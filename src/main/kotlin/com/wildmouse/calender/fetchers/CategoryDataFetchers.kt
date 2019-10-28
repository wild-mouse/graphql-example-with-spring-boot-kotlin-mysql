package com.wildmouse.calender.fetchers

import com.wildmouse.calender.domain.entity.Category
import com.wildmouse.calender.domain.entity.Schedule
import com.wildmouse.calender.mapper.CategoriesMapper
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class CategoryDataFetchers(
        private val categoriesMapper: CategoriesMapper
) {
    internal val categoriesDataFetcher: DataFetcher<List<Category>> =
            DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                val names = dataFetchingEnvironment.getArgument<List<String>?>("names")

                categoriesMapper.getCategories()
                        .filter { names?.contains(it.name) ?: true }
                        .toList()
            }

    internal val categoriesByScheduleIdDataFetcher: DataFetcher<*> =
            DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                val schedule = dataFetchingEnvironment.getSource<Schedule>()
                val scheduleId = schedule.id
                categoriesMapper.getCategoriesByScheduleId(scheduleId)
            }
}
