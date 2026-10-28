package com.tecsup.bookshelf

import com.tecsup.bookshelf.data.BookItem
import com.tecsup.bookshelf.data.BookSearchResponse
import com.tecsup.bookshelf.data.BookService
import com.tecsup.bookshelf.data.ImageLinks
import com.tecsup.bookshelf.data.VolumeInfo
import com.tecsup.bookshelf.data.VolumeResponse

class FakeBookService : BookService {
    override suspend fun searchBooks(query: String, maxResults: Int) =
        BookSearchResponse(items = listOf(BookItem("1"), BookItem("2")))

    override suspend fun getBook(id: String) = VolumeResponse(
        id = id,
        volumeInfo = VolumeInfo(
            title = "Libro $id",
            imageLinks = ImageLinks(thumbnail = "http://books.google.com/img$id")
        )
    )
}
