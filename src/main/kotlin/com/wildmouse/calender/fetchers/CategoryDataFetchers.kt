package com.wildmouse.calender.fetchers

import com.google.common.collect.ImmutableMap
import com.wildmouse.calender.entity.Category
import com.wildmouse.calender.entity.Schedule
import com.wildmouse.calender.mapper.CategoriesMapper
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

import java.util.Arrays
import java.util.stream.Collectors

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

    companion object {
        private val categories = Arrays.asList<Map<String, String>>(
                ImmutableMap.of("id", "category1",
                        "scheduleId", "schedule1",
                        "name", "routine"
                ),
                ImmutableMap.of("id", "category2",
                        "scheduleId", "schedule1",
                        "name", "work"
                ),
                ImmutableMap.of("id", "category3",
                        "scheduleId", "schedule2",
                        "name", "private"
                )
        )
    }
}
