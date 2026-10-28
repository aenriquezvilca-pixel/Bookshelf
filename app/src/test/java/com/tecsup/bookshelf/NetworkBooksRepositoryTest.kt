package com.tecsup.bookshelf

import com.tecsup.bookshelf.data.Book
import com.tecsup.bookshelf.data.NetworkBooksRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBooksRepositoryTest {

    @Test
    fun getBooks_devuelveLibrosConUrlHttps() = runTest {
        val repository = NetworkBooksRepository(FakeBookService())

        val books = repository.getBooks("jazz history")

        assertEquals(
            listOf(
                Book("1", "Libro 1", "https://books.google.com/img1"),
                Book("2", "Libro 2", "https://books.google.com/img2")
            ),
            books
        )
    }
}
