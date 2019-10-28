package com.wildmouse.calender.fetchers

import com.wildmouse.calender.domain.entity.AdditionalInformation
import com.wildmouse.calender.domain.entity.Schedule
import com.wildmouse.calender.mapper.AdditionalInformationMapper
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class AdditionalInformationDataFetchers(
        private val additionalInformationMapper: AdditionalInformationMapper
) {
    internal val additionalInformationByScheduleIdDataFetcher: DataFetcher<List<AdditionalInformation>> =
            DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                val schedule = dataFetchingEnvironment.getSource<Schedule>()
                val scheduleId = schedule.id
                additionalInformationMapper.getAdditionalInformationListByScheduleId(scheduleId)
            }
}
