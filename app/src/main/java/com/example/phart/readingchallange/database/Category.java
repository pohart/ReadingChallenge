package com.example.phart.readingchallange.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.common.base.Splitter;

import net.jcip.annotations.Immutable;

import java.util.List;

/**
 * Created by PHART on 1/26/2018.
 */

@Entity
public class Category implements Comparable<Category> {
    @PrimaryKey private int categoryId;
    private int number;
    private String category;

    public Category(final int number, final String categroy) {
        this.number = number;
        this.category = categroy;
    }

    public int getNumber() { return number; }
    public void setNumber(final int number) { this.number = number;}

    public String getCategory() { return category;}
    public void setCategory(final String category) {this.category = category;}

    /*    @Override
                    public String toString() {
                        return "" + categoryNumber + '|' + categoryString;
                    }

                    public Category parse(String strCategory) {
                        List<String> lstCategory = Splitter.on('|').splitToList(strCategory);
                        return new Category(Integer.parseInt(lstCategory.get(0)), lstCategory.get(1));
                    }
                */
    @Override
    public int compareTo(@NonNull final Category o) {
        return number - o.number;
    }

  /*  @org.jetbrains.annotations.Contract("_ -> !null")
    static Category parseCategory(String str) {
        return new Category(
                Integer.parseInt(str.substring(0, str.indexOf('|'))),
                str.substring(str.indexOf('|') + 1).trim());
    }
*/

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Category)) {
            return false;
        } else if (this.number == ((Category) obj).number
                && this.category.equals(((Category) obj).category)) {
            return true;
        } else if ((this.number == ((Category) obj).number)
                != (this.category.equals(((Category) obj).category))) {
            throw new RuntimeException("Inconsistent Requirements, '" + this + "' and '" + obj + "'");
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * category.hashCode() + Integer.hashCode(number);
    }

    public String getCategoryString() {
        return category;
    }
}
