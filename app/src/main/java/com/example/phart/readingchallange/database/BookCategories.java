package com.example.phart.readingchallange.database;

import net.jcip.annotations.Immutable;

import java.util.Collection;

/**
 * Created by PHART on 1/26/2018.
 */

@Immutable
public class BookCategories {
    private final Book book;
    private final SetList<Category> categories;

    public BookCategories(final Book book, final Collection<Category> categories) {
        this.book = book;
        this.categories = new SetList<>(categories);
    }

    public Book getBook() {
        return book;
    }

    public SetList<Category> getCategories() {
        return categories;
    }
}
