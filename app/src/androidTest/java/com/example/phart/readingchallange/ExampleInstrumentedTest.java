package com.example.phart.readingchallange;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.phart.readingchallange.database.Book;
import com.example.phart.readingchallange.database.BookCategories;
import com.example.phart.readingchallange.database.Category;
import com.example.phart.readingchallange.database.MPV_Main;
import com.example.phart.readingchallange.database.Model;
import com.google.common.collect.ImmutableList;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.phart.readingchallange", appContext.getPackageName());

        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "A book with a female author."));
        categories.add(new Category(2, "A book from your childhood."));
        categories.add(new Category(3, "A book with a one word title."));
        categories.add(new Category(4, "A young adult book."));
        categories.add(new Category(5, "Another category."));
        Model m =
                new Model(categories, ImmutableList.of(
                        new BookCategories(new Book("Son", "Lois Lowry",
                                LocalDate.of(2018, 1, 7)),
                                ImmutableList.of(categories.get(0),
                                        categories.get(2),
                                        categories.get(3))),
                        new BookCategories(new Book("The Slave Dancer", "Lois Lowry2",
                                LocalDate.of(2018, 1, 8)),
                                ImmutableList.of(categories.get(0),
                                        categories.get(1),
                                        categories.get(3)))));
        MPV_Main.RequiredPresenterOps presenter = new MPV_Main.RequiredPresenterOps() {
            @Override
            public Context getAppContext() {
                return appContext;
            }

            @Override
            public Context getActivityContext() {
                return getAppContext();
            }
        };
        m.persistData(presenter);
        Model m2 = new Model(presenter);
        assert m.getCategories().equals(m2.getCategories());
        assert m.getBookCategories().equals(m2.getCategories());
    }
}
