package com.graphqljava.tutorial.bookdetails

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BookDetailsApplication

fun main(args: Array<String>) {
    SpringApplication.run(BookDetailsApplication::class.java, *args)
}

