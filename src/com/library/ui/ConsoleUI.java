package com.library.ui;

import com.library.model.*;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final Library library;
    private final Librarian librarian;
    private final Author[] authors;
    private final member_Record[] members;
    private final Reader[] readers;
    private final Scanner scanner;

    public ConsoleUI(Library library, Librarian librarian,
                     Author a1, Author a2, Author a3,
                     member_Record m1, member_Record m2,
                     Reader r1, Reader r2) {
        this.library = library;
        this.librarian = librarian;
        this.authors = new Author[]{a1, a2, a3};
        this.members = new member_Record[]{m1, m2};
        this.readers = new Reader[]{r1, r2};
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        printBanner();
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Seçiminiz: ");
            switch (choice) {
                case 1 -> kitapMenu();
                case 2 -> oduncMenu();
                case 3 -> uyeMenu();
                case 4 -> yazarMenu();
                case 5 -> kutuphanecimenu();
                case 0 -> { System.out.println("👋 Güle güle!"); running = false; }
                default -> System.out.println("⚠️ Geçersiz seçim!");
            }
        }
    }

    // ── ANA MENÜ ──────────────────────────────────
    private void printMainMenu() {
        System.out.println("""

                ╔══════════════════════════════╗
                ║  📚 KÜTÜPHANE YÖNETİM SİSTEMİ ║
                ╠══════════════════════════════╣
                ║  1. Kitap İşlemleri           ║
                ║  2. Ödünç / İade İşlemleri   ║
                ║  3. Üye İşlemleri             ║
                ║  4. Yazar İşlemleri           ║
                ║  5. Kütüphaneci İşlemleri     ║
                ║  0. Çıkış                     ║
                ╚══════════════════════════════╝""");
    }

    // ── KİTAP MENÜSÜ ──────────────────────────────
    private void kitapMenu() {
        System.out.println("""

                --- KİTAP İŞLEMLERİ ---
                1. Tüm Kitapları Listele
                2. Kitap Ara (ID ile)
                3. Yazar Adıyla Ara
                4. Yeni Kitap Ekle
                0. Geri""");

        switch (readInt("Seçim: ")) {
            case 1 -> library.showBooks();
            case 2 -> {
                String id = readString("Kitap ID: ");
                Book b = library.findBookById(id);
                if (b != null) System.out.println(b.display());
                else System.out.println("❌ Bulunamadı.");
            }
            case 3 -> {
                String ad = readString("Yazar adı: ");
                List<Book> sonuc = library.findByAuthor(ad);
                if (sonuc.isEmpty()) System.out.println("❌ Sonuç yok.");
                else sonuc.forEach(b -> System.out.println("  " + b.display()));
            }
            case 4 -> kitapEkle();
            case 0 -> {}
            default -> System.out.println("⚠️ Geçersiz seçim!");
        }
    }

    private void kitapEkle() {
        System.out.println("\n-- Kitap Türü Seç --");
        System.out.println("1. Ders Kitabı (StudyBooks)");
        System.out.println("2. Akademik Dergi (Journals)");
        System.out.println("3. Magazin (Magazines)");
        int tur = readInt("Tür: ");

        yazarlariListele();
        int aIdx = readInt("Yazar numarası (1/" + authors.length + "): ") - 1;
        if (aIdx < 0 || aIdx >= authors.length) {
            System.out.println("❌ Geçersiz seçim."); return;
        }
        Author author = authors[aIdx];

        String id   = readString("Kitap ID: ");
        String ad   = readString("Kitap Adı: ");
        double fiy  = readDouble("Fiyat: ");
        String bask = readString("Baskı: ");
        String tar  = readString("Satın Alma Tarihi (yyyy-aa-gg): ");

        Book yeniKitap = switch (tur) {
            case 1 -> {
                String ders = readString("Ders: ");
                String sev  = readString("Seviye: ");
                yield new StudyBooks(id, author, ad, fiy, bask, tar, ders, sev);
            }
            case 2 -> {
                String issn = readString("ISSN: ");
                String alan = readString("Alan: ");
                yield new Journals(id, author, ad, fiy, bask, tar, issn, alan);
            }
            case 3 -> {
                String yay  = readString("Yayıncı: ");
                int    sayi = readInt("Sayı No: ");
                yield new Magazines(id, author, ad, fiy, bask, tar, yay, sayi);
            }
            default -> { System.out.println("❌ Geçersiz tür."); yield null; }
        };

        if (yeniKitap != null) {
            library.newBook(yeniKitap);
            author.newBook(yeniKitap);
        }
    }

    // ── ÖDÜNÇ / İADE ──────────────────────────────
    private void oduncMenu() {
        System.out.println("""

                --- ÖDÜNÇ / İADE İŞLEMLERİ ---
                1. Kitap Ödünç Ver (Librarian → Üye)
                2. Kitap İade Al
                3. Ceza Hesapla
                4. Fatura Oluştur
                0. Geri""");

        switch (readInt("Seçim: ")) {
            case 1 -> {
                library.showBooks();
                String bid = readString("Kitap ID: ");
                Book book = library.findBookById(bid);
                if (book == null) { System.out.println("❌ Kitap bulunamadı."); return; }
                uyeleriListele();
                int mIdx = readInt("Üye no: ") - 1;
                if (mIdx < 0 || mIdx >= members.length) { System.out.println("❌ Geçersiz."); return; }
                librarian.issueBook(book, members[mIdx]);
            }
            case 2 -> {
                String bid = readString("İade edilecek Kitap ID: ");
                Book book = library.findBookById(bid);
                if (book == null) { System.out.println("❌ Kitap bulunamadı."); return; }
                uyeleriListele();
                int mIdx = readInt("Üye no: ") - 1;
                if (mIdx < 0 || mIdx >= members.length) { System.out.println("❌ Geçersiz."); return; }
                librarian.returnBook(book, members[mIdx]);
            }
            case 3 -> {
                int gun = readInt("Gecikme (gün): ");
                librarian.calculateFine(gun);
            }
            case 4 -> {
                uyeleriListele();
                int mIdx = readInt("Üye no: ") - 1;
                if (mIdx < 0 || mIdx >= members.length) { System.out.println("❌ Geçersiz."); return; }
                double tutar = readDouble("Tutar: ");
                librarian.createBill(members[mIdx], tutar);
            }
            case 0 -> {}
            default -> System.out.println("⚠️ Geçersiz seçim!");
        }
    }

    // ── ÜYE MENÜSÜ ────────────────────────────────
    private void uyeMenu() {
        System.out.println("""

                --- ÜYE İŞLEMLERİ ---
                1. Üyeleri Listele
                2. Üye Bilgisi Göster
                0. Geri""");

        switch (readInt("Seçim: ")) {
            case 1 -> uyeleriListele();
            case 2 -> {
                uyeleriListele();
                int idx = readInt("Üye no: ") - 1;
                if (idx >= 0 && idx < members.length)
                    System.out.println(members[idx].getMember());
                else System.out.println("❌ Geçersiz.");
            }
            case 0 -> {}
        }
    }

    // ── YAZAR MENÜSÜ ──────────────────────────────
    private void yazarMenu() {
        System.out.println("""

                --- YAZAR İŞLEMLERİ ---
                1. Yazarları Listele
                2. Yazarın Kitaplarını Göster
                0. Geri""");

        switch (readInt("Seçim: ")) {
            case 1 -> yazarlariListele();
            case 2 -> {
                yazarlariListele();
                int idx = readInt("Yazar no: ") - 1;
                if (idx >= 0 && idx < authors.length)
                    authors[idx].showBooks();
                else System.out.println("❌ Geçersiz.");
            }
            case 0 -> {}
        }
    }

    // ── KÜTÜPHANECİ MENÜSÜ ───────────────────────
    private void kutuphanecimenu() {
        System.out.println("""

                --- KÜTÜPHANECİ İŞLEMLERİ ---
                1. Kitap Ara
                2. Üye Doğrula
                0. Geri""");

        switch (readInt("Seçim: ")) {
            case 1 -> {
                String kw = readString("Arama kelimesi: ");
                librarian.searchBook(library.getBooks(), kw);
            }
            case 2 -> {
                uyeleriListele();
                int idx = readInt("Üye no: ") - 1;
                if (idx >= 0 && idx < members.length)
                    librarian.verifyMember(members[idx]);
                else System.out.println("❌ Geçersiz.");
            }
            case 0 -> {}
        }
    }

    // ── YARDIMCI METODLAR ─────────────────────────
    private void yazarlariListele() {
        System.out.println("\n--- YAZARLAR ---");
        for (int i = 0; i < authors.length; i++)
            System.out.println("  " + (i + 1) + ". " + authors[i].getName());
    }

    private void uyeleriListele() {
        System.out.println("\n--- ÜYELER ---");
        for (int i = 0; i < members.length; i++)
            System.out.println("  " + (i + 1) + ". [" + members[i].getMemberType()
                    + "] " + members[i].getName()
                    + " (" + members[i].getNoBooksIssued()
                    + "/" + members[i].getMaxBookLimit() + ")");
    }

    private void printBanner() {
        System.out.println("""
                ╔══════════════════════════════════════════╗
                ║  📚 KÜTÜPHANE YÖNETİM SİSTEMİ v2.0      ║
                ║     UML Diyagramına Uygun — Workintech   ║
                ╚══════════════════════════════════════════╝""");
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Geçerli bir sayı girin.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Geçerli bir sayı girin.");
            }
        }
    }
}