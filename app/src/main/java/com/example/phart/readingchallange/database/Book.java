package com.example.phart.readingchallange.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.phart.readingchallange.LocalDate;

import java.util.Objects;


/**
 * Created by PHART on 1/26/2018.
 */
@Entity
public class Book implements Comparable<Book> {
    @PrimaryKey(autoGenerate = true)
    public int bookId;
    private String title;
    private String author;
    public LocalDate dateCompleted;

    public Book(final String title, final String author, final LocalDate dateCompleted) {
        this.title = title;
        this.author = author;
        this.dateCompleted = dateCompleted;

    }

    public LocalDate getDateRead() {
        return dateCompleted;
    }

    public void setDateCompleted(final LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public boolean equals(final Object obj) {
        return (obj instanceof Book)
                && Objects.equals(title, ((Book) obj).title)
                && Objects.equals(author, ((Book) obj).author);
    }

    @Override
    public int hashCode() {
        return title.hashCode() * 31 + author.hashCode();
    }

    @Override
    public int compareTo(@NonNull final Book o) { return this.dateCompleted.compareTo(o.dateCompleted); }

    @Override
    public String toString() {
        return "\"" + getTitle() + "\" By " + getAuthor();
    }
}

