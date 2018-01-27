package com.example.phart.readingchallange.dummy;

import android.content.SharedPreferences;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.StringReader;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by PHART on 1/26/2018.
 */

public class Model implements MPV_Main.ProvidedModelOps {
    private final SortedSet<Requirement> requirements = new ConcurrentSkipListSet<>();
    private final SortedMap<Book, BookRequirements> bookRequirements = new ConcurrentSkipListMap<>();

    public Model() {
    }

    public Model(final SortedSet<Requirement> requirements, final SortedSet<BookRequirements> bookRequirements) {
        this.requirements.addAll(requirements);
        this.bookRequirements.putAll(bookRequirements.stream().collect(Collectors.toMap(BookRequirements::getBook, Function.identity())));
    }

    @Override
    public SortedMap<Book, BookRequirements> getBookRequirements() {
        return bookRequirements;
    }

    public SortedSet<Requirement> getRequirements() {
        return requirements;
    }


    private Stream<String> loadRequirements(MPV_Main.RequiredPresenterOps presenter) {
        try {
            SharedPreferences challengePrefs = presenter.getActivityContext().getSharedPreferences("Challenge", MODE_PRIVATE);
            String csvRequirements = challengePrefs.getString("requirements", "");
            final CSVParser requirementsParser = CSVFormat.DEFAULT.parse(new StringReader(csvRequirements));
            return StreamSupport.stream(requirementsParser.getRecords().get(0).spliterator(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<String> loadBooks(MPV_Main.RequiredPresenterOps presenter) {
        try {
            SharedPreferences challengePrefs = presenter.getActivityContext().getSharedPreferences("Challenge", MODE_PRIVATE);
            String csvBooks = challengePrefs.getString("books", "");
            final CSVParser bookParser = CSVFormat.DEFAULT.parse(new StringReader(csvBooks));
            return StreamSupport.stream(bookParser.getRecords().get(0).spliterator(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean loadData() {
//        requirements.addAll(loadRequirements().map(str -> new Requirement(Integer.parseInt(str.)).collect(Collectors.toList()));
        return false;
    }

    @Override
    public boolean persistData() {
        return false;
    }
}
