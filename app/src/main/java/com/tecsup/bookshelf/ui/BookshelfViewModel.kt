package com.tecsup.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tecsup.bookshelf.BookshelfApplication
import com.tecsup.bookshelf.data.Book
import com.tecsup.bookshelf.data.BooksRepository
import com.google.gson.JsonParseException
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val books: List<Book>) : BookshelfUiState
    object Loading : BookshelfUiState
    object Error : BookshelfUiState
}

class BookshelfViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    var uiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            uiState = BookshelfUiState.Loading
            uiState = try {
                BookshelfUiState.Success(booksRepository.getBooks(QUERY))
            } catch (e: IOException) {
                BookshelfUiState.Error
            } catch (e: HttpException) {
                BookshelfUiState.Error
            } catch (e: JsonParseException) {
                BookshelfUiState.Error
            }
        }
    }

    companion object {
        const val QUERY = "jazz history"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as BookshelfApplication
                BookshelfViewModel(application.container.booksRepository)
            }
        }
    }
}
