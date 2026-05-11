package com.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public List<Book> getBooks() { return books; }

    public Reader getReader(String name) {
        return readers.stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void newBook(Book book) {
        books.add(book);
        System.out.println("📚 Kütüphaneye yeni kitap eklendi: " + book.getName());
    }

    public boolean lendBook(Book book, Reader reader) {
        if (!book.isAvailable()) {
            System.out.println("❌ Kitap ödünç verilemez, mevcut değil: " + book.getName());
            return false;
        }
        reader.borrowBook(book);
        System.out.println("📤 Ödünç verildi: " + book.getName() + " → " + reader.getName());
        return true;
    }

    public boolean takeBackBook(Book book, Reader reader) {
        boolean result = reader.returnBook(book);
        if (result) {
            System.out.println("📥 Kitap geri alındı: " + book.getName());
        }
        return result;
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("Kütüphanede kitap yok.");
            return;
        }
        System.out.println("\n=== KÜTÜPHANEDEKİ KİTAPLAR ===");
        books.forEach(b -> System.out.println("  " + b.display()));
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public List<Reader> getReaders() { return readers; }

    public Book findBookById(String id) {
        return books.stream()
                .filter(b -> b.getBookId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Book> findByAuthor(String authorName) {
        return books.stream()
                .filter(b -> b.getAuthor().getName().toLowerCase()
                        .contains(authorName.toLowerCase()))
                .collect(Collectors.toList());
    }
}