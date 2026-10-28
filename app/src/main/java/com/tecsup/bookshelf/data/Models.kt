package com.tecsup.bookshelf.data

// Respuesta de Gson: GET volumes?q=...
data class BookSearchResponse(val items: List<BookItem>? = null)
data class BookItem(val id: String)

// Respuesta de Gson: GET volumes/{id}
data class VolumeResponse(val id: String, val volumeInfo: VolumeInfo? = null)
data class VolumeInfo(val title: String? = null, val imageLinks: ImageLinks? = null)
data class ImageLinks(val thumbnail: String? = null)

// Modelo que usa la IU
data class Book(val id: String, val title: String, val thumbnailUrl: String?)
