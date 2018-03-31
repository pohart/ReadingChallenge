package com.example.phart.readingchallange.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

/**
 * Created by PHART on 2/8/2018.
 */
@Entity(
        primaryKeys = {"bookId", "categoryId"},
        indices = {
                @Index("bookId"),
                @Index("categoryId")},
        foreignKeys = {
                @ForeignKey(entity = Book.class,parentColumns = "bookId", childColumns = "bookId"),
                @ForeignKey(entity = Category.class,parentColumns = "categoryId", childColumns = "categoryId")
        })
public class BookCategory {
    private int bookId;
    private int categoryId;

    public int getBookId() {return bookId;}
    public void setBookId(final int bookId) {this.bookId = bookId;}

    public int getCategoryId() {return categoryId;}
    public void setCategoryId(final int categoryId) {this.categoryId = categoryId;}
}
