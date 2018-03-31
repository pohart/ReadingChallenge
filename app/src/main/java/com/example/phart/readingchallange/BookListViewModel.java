package com.example.phart.readingchallange;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.phart.readingchallange.database.Book;
import com.example.phart.readingchallange.database.BookDatabase;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by PHART on 3/1/2018.
 */

public class BookListViewModel extends AndroidViewModel {
    private final BookDatabase bookDatabase;
    private List<Book> books;

    public BookListViewModel(@NonNull final Application application) {
        super(application);
        bookDatabase = BookDatabase.getDatabase(application.getApplicationContext());
        loadBooksAsync();
    }

    public BookDatabase getBookDatabase() {
        return bookDatabase;
    }

    public List<Book> getBooks() {
        return books;
    }

    private void loadBooksAsync() {
        try {
            books = new BookDataAsyncTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class BookDataAsyncTask extends AsyncTask<Void, Void, List<Book>> {
        WeakReference<BookListViewModel> viewModel;

        public BookDataAsyncTask(final BookListViewModel bookListViewModel) {
            viewModel = new WeakReference<>(bookListViewModel);
        }

        @Override
        protected List<Book> doInBackground(final Void... voids) {
            BookListViewModel viewModel = this.viewModel.get();
            if (viewModel != null)
                return viewModel.bookDatabase.bookdDao().loadBooks();

            return null;
        }

        @Override
        protected void onPostExecute(final List<Book> booksLiveData) {
            BookListViewModel viewModel = this.viewModel.get();
            if (viewModel != null && booksLiveData != null)
                viewModel.books = booksLiveData;
        }
    }
}
