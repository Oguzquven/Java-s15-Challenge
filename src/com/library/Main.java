package com.library;

import com.library.model.*;
import com.library.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        // Kütüphane oluştur
        Library library = new Library();

        // Kütüphaneci
        Librarian librarian = new Librarian("Mehmet Bey", "lib123");

        // Yazarlar
        Author author1 = new Author("Orhan Pamuk");
        Author author2 = new Author("Franz Kafka");
        Author author3 = new Author("George Orwell");

        // Kitaplar (polymorphism - farklı türler)
        Book b1 = new StudyBooks("B001", author1, "Kar", 85.0,
                "3. Baskı", "2020-01-15", "Edebiyat", "Lisans");
        Book b2 = new Journals("B002", author2, "Dönüşüm Analizi", 120.0,
                "1. Baskı", "2019-06-10", "ISSN-001", "Felsefe");
        Book b3 = new Magazines("B003", author3, "Distopya Dergisi", 45.0,
                "5. Baskı", "2021-03-22", "Penguin", 42);
        Book b4 = new StudyBooks("B004", author3, "1984 İncelemesi", 95.0,
                "2. Baskı", "2018-11-01", "Siyaset", "Lisansüstü");

        // Kitapları kütüphaneye ekle
        library.newBook(b1);
        library.newBook(b2);
        library.newBook(b3);
        library.newBook(b4);

        // Yazarın kitap kaydı
        author1.newBook(b1);
        author2.newBook(b2);
        author3.newBook(b3);
        author3.newBook(b4);

        // Üyeler
        Student student1 = new Student("S001", "2024-09-01",
                "Ahmet Yılmaz", "Ankara", "05551112233",
                "20240001", "Bilgisayar Mühendisliği");

        Faculty faculty1 = new Faculty("F001", "2020-03-15",
                "Dr. Ayşe Kaya", "İstanbul", "05552223344",
                "FAC001", "Türk Dili ve Edebiyatı", "Doç. Dr.");

        // Reader'lar
        Reader reader1 = new Reader("Ahmet Yılmaz");
        Reader reader2 = new Reader("Dr. Ayşe Kaya");
        library.addReader(reader1);
        library.addReader(reader2);

        // ConsoleUI'yi başlat
        ConsoleUI ui = new ConsoleUI(library, librarian, author1, author2, author3,
                student1, faculty1, reader1, reader2);
        ui.start();
    }
}