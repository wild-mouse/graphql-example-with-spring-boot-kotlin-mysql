package com.wildmouse.calender.fetchers

import com.wildmouse.calender.entity.Schedule
import com.wildmouse.calender.mapper.SchedulesMapper
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

import java.util.stream.Collectors

@Component
class ScheduleDataFetchers(
        private val schedulesMapper: SchedulesMapper
) {
    internal val schedulesDataFetcher: DataFetcher<List<Schedule>> =
            DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                val name = dataFetchingEnvironment.getArgument<String>("name")
                val allDay = dataFetchingEnvironment.getArgument<String>("allDay")
                val hasAdditionalInformationList = dataFetchingEnvironment.getArgument<List<String>?>("hasAdditionalInformationList")
                        ?.map {
                            // TODO: Fix for url contains colon
                            val keyValue = it.split(":")
                            // TODO: Trim spaces
                            Pair(keyValue.first(), keyValue.last())
                        }
                val hasCategoryNames = dataFetchingEnvironment.getArgument<List<String>?>("hasCategoryNames")

                schedulesMapper.getSchedules()
                        .stream()
                        .filter { schedule ->
                            if (name == null) true else schedule.name == name
                        }
                        .filter { schedule ->
                            if (allDay == null) true
                            else schedule.allDay == allDay
                        }
                        .filter { schedule ->
                            isAllInfoContained(hasAdditionalInformationList, schedule)
                        }
                        .filter { schedule ->
                            hasCategoryNames?.all { categoryName ->
                                schedule.categories.any { category ->
                                    category.name == categoryName
                                }
                            } ?: true
                        }
                        .collect(Collectors.toList())
            }

    fun isAllInfoContained(
            hasAdditionalInformationList: List<Pair<String, String>>?,
            schedule: Schedule
    ): Boolean = hasAdditionalInformationList?.all { target ->
        schedule.additionalInformationList
                .find { it.name == target.first }
                .let {
                    if (it == null) false
                    else it.value == target.second
                }
    } ?: true
}
