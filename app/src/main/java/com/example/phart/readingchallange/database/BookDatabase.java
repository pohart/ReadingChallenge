package com.example.phart.readingchallange.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;
import android.content.Context;

import com.example.phart.readingchallange.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by PHART on 2/8/2018.
 */
@android.arch.persistence.room.Database(
        version = 2,
        entities = {Book.class, Category.class, BookCategory.class}
)

@TypeConverters({BookDatabase.TypeConverter.class})
public abstract class BookDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "book-database";
    private static volatile BookDatabase sDatabase;

    abstract public BookDao bookdDao();
    abstract public CategoryDao categroyDao();
    abstract public BookCategoryDao bookCategoryDao();

    public static BookDatabase getDatabase(Context ctx) {
        if (sDatabase == null) {
            synchronized (BookDatabase.class) {
                if (sDatabase == null) {
                    sDatabase
                            = Room.databaseBuilder(ctx, BookDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
//                            .addMigrations(new Migration(1,2) {
//                                @Override
//                                public void migrate(@NonNull final SupportSQLiteDatabase database) {
//                                    database.up
//                                }
//                            })
                            .build();
                }
            }
        }
        return sDatabase;
    }

    @Dao
    public interface BookDao{
        @Insert(onConflict = OnConflictStrategy.ROLLBACK)
        long insertBooks(Book books);


        @Delete
        @Transaction
        int deleteBooks(Book ... books);
        @Update
        @Transaction
        void updateBooks(Book ... books);

        @Query("Select * from Book order by dateCompleted")
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
        LiveData<List<Category>> loadCategories();
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

    public static class TypeConverter {
        @android.arch.persistence.room.TypeConverter
        public String toString(LocalDate value) {
            return DateTimeFormatter.BASIC_ISO_DATE.format(java.time.LocalDate.of(value.getYear(), value.getMonth(), value.getDay()));
        }

        @android.arch.persistence.room.TypeConverter
        public LocalDate toLocalDate(String value) {
            java.time.LocalDate localdate = java.time.LocalDate.from(DateTimeFormatter.BASIC_ISO_DATE.parse(value));
            LocalDate myLocalDate = new LocalDate(localdate.getYear(), localdate.getMonthValue(), localdate.getDayOfMonth());
            return myLocalDate;
        }
    }



}
