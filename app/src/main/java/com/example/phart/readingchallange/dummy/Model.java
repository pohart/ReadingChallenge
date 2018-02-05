package com.example.phart.readingchallange.dummy;

import android.content.SharedPreferences;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by PHART on 1/26/2018.
 */

public class Model implements MPV_Main.ProvidedModelOps {
    private final SortedSet<Category> categories;
    private final SortedMap<Book, BookCategories> bookCategories;

    public Model(MPV_Main.RequiredPresenterOps presenter) {
        categories = new ConcurrentSkipListSet<>(loadCategories(presenter).collect(Collectors.toSet()));
        Map<Book, BookCategories> book2categories
                = loadBooks(presenter)
                .collect(Collectors.toMap(Function.identity(),
                        x -> new BookCategories(
                                x,
                                loadCategories(presenter, x.toString())
                                        .collect(Collectors.toSet()))));
        bookCategories = new ConcurrentSkipListMap<>(book2categories);
    }

    public Model(final Collection<Category> categories, final Collection<BookCategories> bookCategories) {
        SortedSet<Category> modifiableCategories = new ConcurrentSkipListSet<>();
        modifiableCategories.addAll(categories);
        SortedMap<Book, BookCategories> modifiableBooks = new ConcurrentSkipListMap<>();
        modifiableBooks.putAll(bookCategories.stream().collect(Collectors.toMap(BookCategories::getBook, Function.identity())));

        this.categories = modifiableCategories;
        this.bookCategories = modifiableBooks;
    }

    @Override
    public SortedMap<Book, BookCategories> getBookCategories() {
        return bookCategories;
    }

    public SortedSet<Category> getCategories() {
        return categories;
    }


    private Stream<Category> loadCategories(MPV_Main.RequiredPresenterOps presenter) {
        String sharedPreferenceCategory = "";
        return loadCategories(presenter, sharedPreferenceCategory);
    }

    private Stream<Category> loadCategories(final MPV_Main.RequiredPresenterOps presenter, final String book) {
        String sharedPreferenceName = "Categories" + (book == null || book.equals("") ? "" : ("|" + book));
        SharedPreferences challengePrefs =
                presenter.getActivityContext().getSharedPreferences("Model", MODE_PRIVATE);
        return challengePrefs.getStringSet(sharedPreferenceName, new HashSet<>())
                .stream()
                .map(x -> Category.parseCategory(x));
    }

    public static Stream<Book> loadBooks(MPV_Main.RequiredPresenterOps presenter) {
        SharedPreferences challengePrefs = presenter.getActivityContext().getSharedPreferences("Model", MODE_PRIVATE);
        return challengePrefs.getStringSet("Books", new HashSet<>())
                .stream()
                .map(x -> Book.parseBook(x));
    }
    @Override
    public void persistData(MPV_Main.RequiredPresenterOps presenter) {
        SharedPreferences.Editor editor = presenter.getActivityContext().getSharedPreferences("Model", MODE_PRIVATE).edit();
        editor.putStringSet("Categories", categories.stream().map(x -> x.toString()).collect(Collectors.toSet()));
        editor.putStringSet("Books", bookCategories.keySet().stream().map(x -> x.toString()).collect(Collectors.toSet()));
        bookCategories.entrySet().stream().forEach(bc -> editor.putStringSet("Categories|" + bc.getKey().toString(),
                bc.getValue().getCategories().stream().map(c -> c.toString()).collect(Collectors.toSet())));
        editor.commit();
    }

    public static void main(String[] args) {
        Collection<BookCategories> books = new ArrayList<>();
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

        System.out.println(new Gson().fromJson(new Gson().toJson(m), Model.class) == m);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(new Gson().toJson(m)).getAsJsonArray();
//        ObjectSer
    }
}
