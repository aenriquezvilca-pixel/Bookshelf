package com.tecsup.bookshelf.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 12
    ): BookSearchResponse

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): VolumeResponse
}
