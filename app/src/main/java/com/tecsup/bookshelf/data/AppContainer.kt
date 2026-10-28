package com.tecsup.bookshelf.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val booksRepository: BooksRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val bookService: BookService by lazy { retrofit.create(BookService::class.java) }

    override val booksRepository: BooksRepository by lazy { NetworkBooksRepository(bookService) }
}
