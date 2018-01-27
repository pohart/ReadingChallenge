package com.example.phart.readingchallange.dummy;

import com.google.common.base.Splitter;

import net.jcip.annotations.Immutable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import static java.time.LocalDate.parse;

/**
 * Created by PHART on 1/26/2018.
 */

@Immutable
public class Book {
    private final String title;
    private final String author;
    private final LocalDate dateRead;

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

    public static Book parseBook(String strBook) {
        Iterator<String> bookParts = Splitter.on('\0').split(strBook).iterator();
        return new Book(bookParts.next(), bookParts.next(), parse(bookParts.next(), DateTimeFormatter.ISO_LOCAL_DATE));


    }

}
