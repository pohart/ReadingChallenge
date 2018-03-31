package com.example.phart.readingchallange;

import android.arch.persistence.room.Transaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.phart.readingchallange.database.Book;
import com.example.phart.readingchallange.database.BookDatabase;
import com.example.phart.readingchallange.databinding.BookDetailBinding;

import java.util.concurrent.ExecutionException;

/**
 * An activity representing a single Book detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link BookListActivity}.
 */
public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        BookDetailBinding binding = DataBindingUtil.bind(findViewById(R.id.book_detail));
//        BookDetailBinding.bind(findViewById(R.layout.book_detail));
        binding.setBook(new Book("theTitle", "theAuthor", new LocalDate(2013, 05, 01)));
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(BookDetailActivity.ARG_ITEM_ID,
//                    getIntent().getStringExtra(BookDetailActivity.ARG_ITEM_ID));
//            BookDetailFragment fragment = new BookDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.book_detail_container, fragment)
//                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            Book book = ((BookDetailBinding) DataBindingUtil.getBinding(findViewById(R.id.book_detail)))
                    .getBook();
            AsyncTask<Book, Void, Void> task = new AsyncTask<Book, Void, Void>() {
                @Override
                @Transaction
                protected Void doInBackground(final Book... books) {


                    BookDatabase database = BookDatabase.getDatabase(getApplicationContext());
                    try {
                        //database.beginTransaction();
                        database.bookdDao().insertBooks(books[0]);

                        return null;
                    } finally {
                        if (database.isOpen()) {
                            //database.endTransaction();
                        }
                    }

                }
            };
            task.execute(book);
            try {
                task.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            Toast.makeText(
                    getApplicationContext(),
                    book
                            .toString(),
                    5).show();
            navigateUpTo(new Intent(this, BookListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
