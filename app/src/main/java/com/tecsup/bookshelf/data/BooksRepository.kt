package com.tecsup.bookshelf.data

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
}

class NetworkBooksRepository(private val bookService: BookService) : BooksRepository {

    override suspend fun getBooks(query: String): List<Book> {
        // 1) Buscar libros y quedarse con los id
        val ids = bookService.searchBooks(query).items.orEmpty().map { it.id }
        // 2) Pedir cada libro uno tras otro en la misma corrutina
        return ids.map { id -> bookService.getBook(id).toBook() }
    }
}

private fun VolumeResponse.toBook() = Book(
    id = id,
    title = volumeInfo?.title ?: "Sin título",
    // La API devuelve http; hay que cambiarlo a https para que se vea la imagen
    thumbnailUrl = volumeInfo?.imageLinks?.thumbnail?.replace("http://", "https://")
)
