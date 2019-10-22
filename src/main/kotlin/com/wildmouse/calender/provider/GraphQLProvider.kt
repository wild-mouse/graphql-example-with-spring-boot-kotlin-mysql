package com.wildmouse.calender.provider

import com.google.common.base.Charsets
import com.google.common.io.Resources
import com.wildmouse.calender.fetchers.AdditionalInformationDataFetchers
import com.wildmouse.calender.fetchers.CategoryDataFetchers
import com.wildmouse.calender.fetchers.ScheduleDataFetchers
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import java.io.IOException

import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring

@Component
class GraphQLProvider(
        private val additionalInformationDataFetchers: AdditionalInformationDataFetchers,
        private val categoryDataFetchers: CategoryDataFetchers,
        private val scheduleDataFetchers: ScheduleDataFetchers
) {
    private var graphQL: GraphQL? = null

    @PostConstruct
    @Throws(IOException::class)
    fun init() {
        val url = Resources.getResource("schema.graphqls")
        val sdl = Resources.toString(url, Charsets.UTF_8)
        val graphQLSchema = buildSchema(sdl)
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build()
    }

    private fun buildSchema(sdl: String): GraphQLSchema {
        val typeRegistry = SchemaParser().parse(sdl)
        val runtimeWiring = buildWiring()
        val schemaGenerator = SchemaGenerator()
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    }

    private fun buildWiring(): RuntimeWiring {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("schedules", scheduleDataFetchers.schedulesDataFetcher)
                        .dataFetcher("categories", categoryDataFetchers.categoriesDataFetcher)
                )
                .type(newTypeWiring("Schedule")
                        .dataFetcher(
                                "additionalInformation",
                                additionalInformationDataFetchers.additionalInformationByScheduleIdDataFetcher)
                        .dataFetcher("categories", categoryDataFetchers.categoriesByScheduleIdDataFetcher)
                )
                .build()
    }

    @Bean
    fun graphQL(): GraphQL? {
        return graphQL
    }
}
