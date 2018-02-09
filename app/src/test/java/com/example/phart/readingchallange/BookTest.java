package com.example.phart.readingchallange;

import com.example.phart.readingchallange.database.Book;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BookTest {
    @Test
    public void testEquals_sameTitleAuthorDate() throws Exception {
        assertEquals(
                new Book("Title1", "Author1", LocalDate.of(2018, 1, 1)),
                new Book("Title1", "Author1", LocalDate.of(2018, 1, 1)));
    }

    @Test
    public void testEquals_sameTitleAuthorNotDate() throws Exception {
        assertEquals(
                new Book("Title1", "Author1", LocalDate.of(2018, 1, 27)),
                new Book("Title1", "Author1", LocalDate.of(2018, 1, 28)));
    }

    @Test
    public void testEquals_sameTitleDateNotAuthor() throws Exception {
        assertNotEquals(
                new Book("Title1", "Author1", LocalDate.of(2018, 1, 27)),
                new Book("Title1", "Author2", LocalDate.of(2018, 1, 27)));
    }

    @Test
    public void testEquals_sameAuthorDateNotTitle() throws Exception {
        assertNotEquals(
                new Book("Title1", "Author1", LocalDate.of(2018, 1, 27)),
                new Book("Title2", "Author1", LocalDate.of(2018, 1, 27)));
    }

    @Test
    public void testParse() {
        assertEquals(new Book("Title1", "Author1", LocalDate.of(2018, 1, 27)),
                Book.parseBook("Title1" + '\0' + "Author1" + '\0' + "20180127"));
    }

    @Test
    public void testParseToStringEqualsOriginalBook() {
        Book book1 = new Book("Title1", "Author1", LocalDate.of(2018, 1, 27));
        Book book2 = Book.parseBook(book1.toString());
        assertEquals(book1, book2);
        assertEquals(book1.getDateRead(), book2.getDateRead());
    }

    @Test
    public void testParseToStringisOriginalString() {
        Book book1 = new Book("Title1", "Author1", LocalDate.of(2018, 1, 27));
        Book book2 = Book.parseBook(book1.toString());
        assertEquals(book1.toString(), book2.toString());
    }

}