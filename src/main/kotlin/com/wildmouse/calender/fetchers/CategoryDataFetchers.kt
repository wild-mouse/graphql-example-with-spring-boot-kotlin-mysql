package com.wildmouse.calender.fetchers

import com.google.common.collect.ImmutableMap
import com.wildmouse.calender.entity.Category
import com.wildmouse.calender.entity.Schedule
import com.wildmouse.calender.repository.CategoryRepository
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

import java.util.Arrays
import java.util.stream.Collectors

@Component
class CategoryDataFetchers (
        private val categoryRepository: CategoryRepository
){
    internal val categoriesDataFetcher: DataFetcher<List<Category>> =
            DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                categoryRepository.findAll()
             }

    internal val categoriesByScheduleIdDataFetcher: DataFetcher<*> =
            DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                val schedule = dataFetchingEnvironment.getSource<Schedule>()
                val scheduleId = schedule.id
                categories
                        .stream()
                        .filter { category -> category["scheduleId"] == scheduleId.toString() }
                        .collect(Collectors.toList())
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
