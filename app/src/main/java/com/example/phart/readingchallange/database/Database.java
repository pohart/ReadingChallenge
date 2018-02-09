package com.example.phart.readingchallange.database;

import android.arch.persistence.room.RoomDatabase;

import com.example.phart.readingchallange.database.dao.BookDao;

/**
 * Created by PHART on 2/8/2018.
 */
@android.arch.persistence.room.Database(
        version = 1,
        entities = {Book.class, Category.class, BookCategory.class}
)
public abstract class Database extends RoomDatabase {
    abstract public BookDao bookdDao();
    abstract public CategoryDao categroyDao();
    abstract public BookCategoryDao bookCategoryDao();

}
