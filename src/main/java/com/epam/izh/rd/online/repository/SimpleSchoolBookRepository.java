package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        if (book instanceof SchoolBook) {
            schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
            schoolBooks[schoolBooks.length - 1] = (SchoolBook) book;
            return true;
        }
        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books = {};
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                books = Arrays.copyOf(books, books.length + 1);
                books[books.length - 1] = schoolBooks[i];
            }
        }
        return books;
    }

    @Override
    public boolean removeByName(String name) {
        boolean isChange = false;
        for (int i = schoolBooks.length - 1; i >= 0; i--) {
            if (schoolBooks[i].getName().equals(name)) {
                // Если элемент не последний в массиве
                // переставляем на его место последний элемент массива
                if (i != schoolBooks.length - 1) {
                    schoolBooks[i] = schoolBooks[schoolBooks.length - 1];
                }
                // уменьшаем массив на 1 элемент
                schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);
                isChange = true;
            }
        }
        return isChange;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
