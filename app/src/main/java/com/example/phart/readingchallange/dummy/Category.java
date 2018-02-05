package com.example.phart.readingchallange.dummy;

import android.support.annotation.NonNull;

import com.google.common.base.Splitter;

import net.jcip.annotations.Immutable;

import java.util.List;

/**
 * Created by PHART on 1/26/2018.
 */

@Immutable
public class Category implements Comparable<Category> {
    private int categoryNumber;
    private String categoryString;

    public Category(final int categoryNumber, final String categoryString) {
        this.categoryNumber = categoryNumber;
        this.categoryString = categoryString;
    }

    @Override
    public String toString() {
        return "" + categoryNumber + '|' + categoryString;
    }

    public Category parse(String strCategory) {
        List<String> lstCategory = Splitter.on('|').splitToList(strCategory);
        return new Category(Integer.parseInt(lstCategory.get(0)), lstCategory.get(1));
    }

    @Override
    public int compareTo(@NonNull final Category o) {
        return categoryNumber - o.categoryNumber;
    }

    @org.jetbrains.annotations.Contract("_ -> !null")
    static Category parseCategory(String str) {
        return new Category(
                Integer.parseInt(str.substring(0, str.indexOf('|'))),
                str.substring(str.indexOf('|') + 1).trim());
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Category)) {
            return false;
        } else if (obj instanceof Category
                && this.categoryNumber == ((Category) obj).categoryNumber
                && this.categoryString.equals(((Category) obj).categoryString)) {
            return true;
        } else if (obj instanceof Category
                && ((this.categoryNumber == ((Category) obj).categoryNumber)
                != (this.categoryString.equals(((Category) obj).categoryString)))) {
            throw new RuntimeException("Inconsistent Requirements, '" + this + "' and '" + obj + "'");
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * categoryString.hashCode() + Integer.hashCode(categoryNumber);
    }

    public String getCategoryString() {
        return categoryString;
    }
}
