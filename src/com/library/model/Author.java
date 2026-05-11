package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private List<Book> books;

    public Author(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public void newBook(Book book) {
        books.add(book);
        System.out.println("✅ Yazar '" + name + "' yeni kitap ekledi: " + book.getName());
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("Bu yazara ait kitap yok.");
            return;
        }
        System.out.println("--- " + name + " adlı yazarın kitapları ---");
        books.forEach(b -> System.out.println("  " + b.display()));
    }

    @Override
    public String whoyouare() {
        return "Yazar";
    }

    public List<Book> getBooks() { return books; }
}