package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(Book book) {
        if (book instanceof SchoolBook) {
            if (null != authorService.findByFullName(((SchoolBook) book).getAuthorName(),
                    ((SchoolBook) book).getAuthorLastName())) {
                return schoolBookBookRepository.save((SchoolBook) book);
            }
        }
        return false;
    }

    @Override
    public Book[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        Book[] books = findByName(name);
        if (books != null && books.length > 0 && books[0] instanceof SchoolBook) {
            return authorService.findByFullName(((SchoolBook) books[0]).getAuthorName(),
                    ((SchoolBook) books[0]).getAuthorLastName());
        }
        return null;
    }
}