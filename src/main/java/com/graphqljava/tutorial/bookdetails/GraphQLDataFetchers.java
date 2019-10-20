package com.graphqljava.tutorial.bookdetails;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> schedules = Arrays.asList(
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
    );

    private static List<Map<String, String>> additionalInformation= Arrays.asList(
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
    );

    private static List<Map<String, String>> categories = Arrays.asList(
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
    );

    DataFetcher getSchedulesDataFetcher() {
        return dataFetchingEnvironment -> {
            String name = dataFetchingEnvironment.getArgument("name");
            String allDay = dataFetchingEnvironment.getArgument("allDay");
            return schedules
                    .stream()
                    .filter(schedule -> {
                        if (name == null) {
                            return true;
                        }
                        return schedule.get("name").equals(name);
                    })
                    .filter(schedule -> {
                        if (allDay == null) {
                            return true;
                        }
                        return schedule.get("allDay").equals(allDay);
                    })
                    .collect(Collectors.toList());
        } ;
    }

    DataFetcher getAdditionalInformationByScheduleIdDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> schedule = dataFetchingEnvironment.getSource();
            String scheduleId = schedule.get("id");
            return additionalInformation
                    .stream()
                    .filter(additionalInfo -> additionalInfo.get("scheduleId").equals(scheduleId))
                    .collect(Collectors.toList());
        };
    }

    DataFetcher getCategoriesDataFetcher() {
        return dataFetchingEnvironment -> categories;
    }

    DataFetcher getCategoriesByScheduleIdDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> schedule = dataFetchingEnvironment.getSource();
            String scheduleId = schedule.get("id");
            return categories
                    .stream()
                    .filter(category -> category.get("scheduleId").equals(scheduleId))
                    .collect(Collectors.toList());
        };
    }
}
