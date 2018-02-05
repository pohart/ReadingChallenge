package com.example.phart.readingchallange.dummy;

import net.jcip.annotations.Immutable;

import java.util.Collection;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by PHART on 1/26/2018.
 */

@Immutable
public class BookCategories {
    private final Book book;
    private final SortedSet<Category> categories;

    public BookCategories(final Book book, final Collection<Category> categories) {
        this.book = book;
        this.categories = new ConcurrentSkipListSet<>(categories);
    }

    public Book getBook() {
        return book;
    }

    public SortedSet<Category> getCategories() {
        return categories;
    }
}
