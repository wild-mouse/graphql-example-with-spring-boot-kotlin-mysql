package com.graphqljava.tutorial.bookdetails;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> objects = Arrays.asList(
            ImmutableMap.of("id", "object1",
                    "sid", "sid1",
                    "cid", "cid1",
                    "url", "https://example.com/111"
            ),
            ImmutableMap.of("id", "object2",
                    "sid", "sid1",
                    "cid", "cid2",
                    "url", "https://example.com/222"
            ),
            ImmutableMap.of("id", "object3",
                    "sid", "sid1",
                    "cid", "cid3",
                    "url", "https://example.com/333"
            ),
            ImmutableMap.of("id", "object4",
                    "sid", "sid2",
                    "cid", "cid4",
                    "url", "https://example.com/444"
            ),
            ImmutableMap.of("id", "object5",
                    "sid", "sid3",
                    "cid", "cid5",
                    "url", "https://example.com/555"
            )
    );

    private static List<Map<String, String>> attributes = Arrays.asList(
            ImmutableMap.of("id", "attributes1",
                    "object_id", "object1",
                    "name", "hoge_name",
                    "value", "hoge_value"
            ),
            ImmutableMap.of("id", "attributes2",
                    "object_id", "object1",
                    "name", "bar_name",
                    "value", "bar_value"
            ),
            ImmutableMap.of("id", "attributes2",
                    "object_id", "object2",
                    "name", "foo_name",
                    "value", "foo_value"
            )
    );

    private static List<Map<String, String>> tags = Arrays.asList(
            ImmutableMap.of("id", "tag1",
                    "object_id", "object1",
                    "name", "hoge_tag"
            ),
            ImmutableMap.of("id", "tag2",
                    "object_id", "object1",
                    "name", "foo_tag"
            ),
            ImmutableMap.of("id", "tag3",
                    "object_id", "object1",
                    "name", "bar_tag"
            ),
            ImmutableMap.of("id", "tag1",
                    "object_id", "object2",
                    "name", "hoge_tag"
            ),
            ImmutableMap.of("id", "tag2",
                    "object_id", "object2",
                    "name", "foo_tag"
            ),
            ImmutableMap.of("id", "tag3",
                    "object_id", "object3",
                    "name", "bar_tag"
            )
    );

    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );

    DataFetcher getObjectsDataFetcher() {
        return dataFetchingEnvironment -> {
            String sid = dataFetchingEnvironment.getArgument("sid");
            String cid = dataFetchingEnvironment.getArgument("cid");
            return objects
                    .stream()
                    .filter(object -> {
                        if (sid == null) {
                            return true;
                        }
                        return object.get("sid").equals(sid);
                    })
                    .filter(object -> {
                        if (cid == null) {
                            return true;
                        }
                        return object.get("cid").equals(cid);
                    })
                    .collect(Collectors.toList());
        } ;
    }

    DataFetcher getAttributesDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String objectId = book.get("id");
            return attributes
                    .stream()
                    .filter(attribute -> attribute.get("object_id").equals(objectId))
                    .collect(Collectors.toList());
        };
    }

    DataFetcher getTagsDataFetcher() {
        return dataFetchingEnvironment -> tags;
    }

    DataFetcher getTagsByObjectIdDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> object = dataFetchingEnvironment.getSource();
            String objectId = object.get("id");
            return tags
                    .stream()
                    .filter(tag -> tag.get("object_id").equals(objectId))
                    .collect(Collectors.toList());
        };
    }

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }
}
