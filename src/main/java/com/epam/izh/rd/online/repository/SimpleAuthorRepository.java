package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = {};

    @Override
    public boolean save(Author author) {
        Author authorInArray = findByFullName(author.getName(), author.getLastName());
        if (authorInArray == null) {
            authors = new Author[authors.length + 1];
            authors[authors.length - 1] = author;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(java.lang.String name, java.lang.String lastname) {
        for (Author authorObj : authors) {
            if (authorObj.getName().equals(name) && authorObj.getLastName().equals(lastname)) {
                return authorObj;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].equals(author)) {
                // Переставляем последний элемент массива в точку совпадения
                authors[i] = authors[authors.length - 1];
                // cоздаём новый массив на элемент короче
                authors = Arrays.copyOf(authors, authors.length - 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
