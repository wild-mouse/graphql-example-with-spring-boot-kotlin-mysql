package com.wildmouse.calender.fetchers

import com.wildmouse.calender.entity.Schedule
import com.wildmouse.calender.mapper.SchedulesMapper
import com.wildmouse.calender.repository.ScheduleRepository
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

                schedulesMapper.getSchedules()
                        .stream()
                        .filter { schedule ->
                            if (name == null) true else schedule.name == name
                        }
                        .filter { schedule ->
                            if (allDay == null) true
                            else schedule.allDay == allDay
                        }
                        .collect(Collectors.toList())
            }
}
