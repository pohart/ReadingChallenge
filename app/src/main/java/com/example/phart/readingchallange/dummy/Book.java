package com.example.phart.readingchallange.dummy;

import android.support.annotation.NonNull;

import com.google.common.base.Splitter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


/**
 * Created by PHART on 1/26/2018.
 */
public class Book implements Comparable<Book> {
    public String title;
    public String author;
    public LocalDate dateRead;

    public Book(final String title, final String author, final LocalDate dateRead) {
        this.title = title;
        this.author = author;
        this.dateRead = dateRead;
    }

    public LocalDate getDateRead() {
        return dateRead;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @NonNull
    public static Book parseBook(String strBook) {
        List<String> bookParts = Splitter.on('\0').splitToList(strBook);
        return new Book(bookParts.get(0), bookParts.get(1), LocalDate.parse(bookParts.get(2), DateTimeFormatter.BASIC_ISO_DATE));
    }

    @Override
    public String toString() {
        return title + '\0' + author + '\0' + dateRead.format(DateTimeFormatter.BASIC_ISO_DATE);
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
    public int compareTo(@NonNull final Book o) {
        return this.dateRead.compareTo(o.dateRead);
    }
}
