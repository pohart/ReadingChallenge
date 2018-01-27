package com.example.phart.readingchallange.dummy;

import net.jcip.annotations.Immutable;

import java.util.Collection;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by PHART on 1/26/2018.
 */

@Immutable
public class BookRequirements {
    private final Book book;
    private final SortedSet<Requirement> requirements;

    public BookRequirements(final Book book, final Collection<Requirement> requirements) {
        this.book = book;
        this.requirements = new ConcurrentSkipListSet<>(requirements);
    }

    public Book getBook() {
        return book;
    }

    public SortedSet<Requirement> getRequirements() {
        return requirements;
    }
}
