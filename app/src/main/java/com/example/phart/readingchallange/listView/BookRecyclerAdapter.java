package com.example.phart.readingchallange.listView;

import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phart.readingchallange.R;
import com.example.phart.readingchallange.database.Book;

import java.util.List;

/**
 * Created by PHART on 3/29/2018.
 */

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {
    private LiveData<List<Book>> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView author;

        public ViewHolder(final LinearLayout itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitleList);
            author = itemView.findViewById(R.id.bookAuthorList);
        }
    }

    public BookRecyclerAdapter(LiveData<List<Book>> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_content, parent, false);
        ViewHolder vh = new ViewHolder(l);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        holder.author.setText(data.getValue().get(i).getAuthor());
        holder.title.setText(data.getValue().get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        if (data == null || data.getValue() == null) {
            return 0;
        }
        return data.getValue().size();
    }
}
