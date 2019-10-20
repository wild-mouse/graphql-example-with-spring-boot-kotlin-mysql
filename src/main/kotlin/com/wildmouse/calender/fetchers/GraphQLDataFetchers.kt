package com.wildmouse.calender.fetchers

import com.google.common.collect.ImmutableMap
import com.wildmouse.calender.entity.Category
import com.wildmouse.calender.repository.CategoryRepository
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

import java.util.Arrays
import java.util.stream.Collectors

@Component
class GraphQLDataFetchers (
        private val categoryRepository: CategoryRepository
){

    internal val schedulesDataFetcher: DataFetcher<*> =
            DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                val name = dataFetchingEnvironment.getArgument<String>("name")
                val allDay = dataFetchingEnvironment.getArgument<String>("allDay")
                schedules
                        .stream()
                        .filter { schedule ->
                            if (name == null) true else schedule["name"] == name
                        }
                        .filter { schedule ->
                            if (allDay == null) true
                            else schedule["allDay"] == allDay
                        }
                        .collect(Collectors.toList())
            }

    internal val additionalInformationByScheduleIdDataFetcher: DataFetcher<*> =
            DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                val schedule = dataFetchingEnvironment.getSource<Map<String, String>>()
                val scheduleId = schedule.get("id")
                additionalInformation
                        .stream()
                        .filter { additionalInfo -> additionalInfo["scheduleId"] == scheduleId }
                        .collect(Collectors.toList())
            }

    internal val categoriesDataFetcher: DataFetcher<List<Category>> =
            DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                categoryRepository.findAll()
             }

    internal val categoriesByScheduleIdDataFetcher: DataFetcher<*> =
            DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                val schedule = dataFetchingEnvironment.getSource<Map<String, String>>()
                val scheduleId = schedule.get("id")
                categories
                        .stream()
                        .filter { category -> category["scheduleId"] == scheduleId }
                        .collect(Collectors.toList())
            }

    companion object {

        private val schedules = Arrays.asList<Map<String, String>>(
                ImmutableMap.of("id", "schedule1",
                        "name", "hoge",
                        "date", "20191020",
                        "allDay", "true"
                ),
                ImmutableMap.of("id", "schedule2",
                        "name", "foo",
                        "date", "20191022",
                        "allDay", "false"
                ),
                ImmutableMap.of("id", "schedule3",
                        "name", "bar",
                        "date", "20191025",
                        "allDay", "true"
                ),
                ImmutableMap.of("id", "schedule4",
                        "name", "fuga",
                        "date", "20101025",
                        "allDay", "false"
                ),
                ImmutableMap.of("id", "schedule5",
                        "name", "piyo",
                        "date", "20191024",
                        "allDay", "true"
                )
        )

        private val additionalInformation = Arrays.asList<Map<String, String>>(
                ImmutableMap.of("id", "additional1",
                        "scheduleId", "schedule1",
                        "name", "url",
                        "value", "https://example.com/hoge"
                ),
                ImmutableMap.of("id", "additional2",
                        "scheduleId", "schedule1",
                        "name", "location",
                        "value", "Tokyo"
                ),
                ImmutableMap.of("id", "additional3",
                        "scheduleId", "schedule2",
                        "name", "location",
                        "value", "Osaka"
                )
        )

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
