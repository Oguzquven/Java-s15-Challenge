package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> books;

    public Reader(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public boolean purchaseBook(Book book) {
        books.add(book);
        System.out.println("✅ " + name + " kitabı satın aldı: " + book.getName());
        return true;
    }

    public boolean borrowBook(Book book) {
        if (!book.isAvailable()) {
            System.out.println("❌ Kitap müsait değil: " + book.getName());
            return false;
        }
        books.add(book);
        book.setStatus("ödünçte");
        System.out.println("✅ " + name + " kitabı ödünç aldı: " + book.getName());
        return true;
    }

    public boolean returnBook(Book book) {
        if (books.remove(book)) {
            book.setStatus("mevcut");
            System.out.println("✅ " + name + " kitabı iade etti: " + book.getName());
            return true;
        }
        System.out.println("❌ Bu kitap bu okuyucuda kayıtlı değil.");
        return false;
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println(name + " adlı okuyucunun kitabı yok.");
            return;
        }
        System.out.println("--- " + name + " adlı okuyucunun kitapları ---");
        books.forEach(b -> System.out.println("  " + b.display()));
    }

    @Override
    public String whoyouare() {
        return "Okuyucu";
    }

    public List<Book> getBooks() { return books; }
}