package com.example.phart.readingchallange;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phart.readingchallange.database.MPV_Main;

/**
 * An activity representing a list of Books. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link BookDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class BookListActivity extends AppCompatActivity
        implements View.OnClickListener, MPV_Main.RequiredViewOps  {

    private MPV_Main.ProvidedPresenterOps mPresenter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    public void onClick(final View v) {
        switch(v.getId()) {
            case R.id.fab:
                mPresenter.clickNewBook();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        recyclerView = findViewById(R.id.book_list);
        recyclerView.setHasFixedSize(true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        BookListViewModel model = ViewModelProviders.of(this).get(BookListViewModel.class);
//        model.getBooks().observe(this, books -> {
//
//                }
//        );

        FloatingActionButton fab = findViewById(R.id.fab_add_book);
        fab.setOnClickListener(view -> {
            Intent i = new Intent(this, BookDetailActivity.class);
            startActivity(i);
        });

        View recyclerView = findViewById(R.id.book_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }


    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BookListViewModel bookListViewModel = new BookListViewModel(getApplication());

        Toast.makeText(this, "books: " + bookListViewModel.getBooks(), Toast.LENGTH_LONG).show();
//        recyclerView.setAdapter(new BookRecyclerAdapter(books));
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void notifyItemInserted(final int layoutPosition) {
        
    }

    @Override
    public void notifyItemRangeChanged(final int positionStart, final int itemCount) {

    }

    private class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter {
        public SimpleItemRecyclerViewAdapter(final BookListActivity bookListActivity, final Object p1, final boolean mTwoPane) {
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
