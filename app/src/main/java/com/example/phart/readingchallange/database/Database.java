package com.example.phart.readingchallange.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by PHART on 2/8/2018.
 */
@android.arch.persistence.room.Database(
        version = 1,
        entities = {Book.class, Category.class, BookCategory.class}
)
@TypeConverters({Database.class})
public abstract class Database extends RoomDatabase {
    abstract public BookDao bookdDao();
    abstract public CategoryDao categroyDao();
    abstract public BookCategoryDao bookCategoryDao();

    @Dao
    public interface BookDao{
        @Insert(onConflict = OnConflictStrategy.ROLLBACK)
        void insertBooks(Book ... books);
        @Delete
        int deleteBooks(Book ... books);
        @Update
        void updateBooks(Book ... books);

        @Query("Select * from Book")
        List<Book> loadBooks();
        @Query("Select * from Book where bookId = :bookId")
        Book loadBook(int bookId);


    }

    @Dao
    public interface CategoryDao{
        @Insert(onConflict = OnConflictStrategy.ROLLBACK)
        void insertCategories(Category ... Categorys);
        @Delete
        int deleteCategories(Category ... Categorys);
        @Update
        void updateCategories(Category ... Categorys);

        @Query("Select * from Category")
        List<Category> loadCategories();
        @Query("Select * from Category where CategoryId = :categoryId")
        Category loadCategory(int categoryId);

        @Query( "Select Category.* " +
                "  from Category" +
                "  join BookCategory on BookCategory.categoryId = Category.categoryId" +
                " where BookCategory.bookId = :bookId")
        List<Category> loadCategoriesByBookId(int bookId);
    }

    @Dao
    public interface BookCategoryDao{
        @Insert(onConflict = OnConflictStrategy.ROLLBACK)
        void insertBookCategories(BookCategory ... BookCategorys);
        @Delete
        int deleteBookCategories(BookCategory ... BookCategorys);
        @Update
        void updateBookCategoroes(BookCategory ... BookCategorys);

        @Query("Delete from BookCategory")
        void deleteBookCategories();
    }


    @TypeConverter
    public String toString(LocalDate value) {
        return DateTimeFormatter.BASIC_ISO_DATE.format(value);
    }
    @TypeConverter
    public LocalDate toLocalDate(String value) {
        return LocalDate.from(DateTimeFormatter.BASIC_ISO_DATE.parse(value));
    }

}
