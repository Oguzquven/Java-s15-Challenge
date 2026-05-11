package com.library.model;

import java.util.List;

public class Librarian {
    private String name;
    private String password;

    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Book searchBook(List<Book> books, String keyword) {
        System.out.println("🔍 Kitap aranıyor: " + keyword);
        for (Book b : books) {
            if (b.getName().toLowerCase().contains(keyword.toLowerCase())
                    || b.getAuthor().getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("✅ Bulundu: " + b.display());
                return b;
            }
        }
        System.out.println("❌ Kitap bulunamadı: " + keyword);
        return null;
    }

    public boolean verifyMember(member_Record member) {
        System.out.println("🔎 Üye doğrulanıyor: " + member.getName());
        boolean valid = member.getMemberId() != null && !member.getMemberId().isEmpty();
        System.out.println(valid ? "✅ Üye doğrulandı." : "❌ Üye doğrulanamadı.");
        return valid;
    }

    public boolean issueBook(Book book, member_Record member) {
        System.out.println("\n📖 Kitap verme işlemi başlıyor...");
        if (!verifyMember(member)) return false;
        if (!book.isAvailable()) {
            System.out.println("❌ Kitap mevcut değil: " + book.getName());
            return false;
        }
        if (!member.incBookIssued()) return false;

        book.setStatus("ödünçte");
        System.out.println("✅ Kitap verildi → " + book.getName() + " → " + member.getName());
        return true;
    }

    public double calculateFine(int daysLate) {
        double finePerDay = 1.50;
        double fine = daysLate * finePerDay;
        System.out.printf("💰 %d gün gecikme → Ceza: %.2f TL%n", daysLate, fine);
        return fine;
    }

    public void createBill(member_Record member, double amount) {
        System.out.printf("🧾 Fatura oluşturuldu | Üye: %s | Tutar: %.2f TL%n",
                member.getName(), amount);
        member.payBill(amount);
    }

    public boolean returnBook(Book book, member_Record member) {
        if (!book.getStatus().equals("ödünçte")) {
            System.out.println("❌ Bu kitap zaten kütüphanede mevcut.");
            return false;
        }
        book.setStatus("mevcut");
        member.decBookIssued();
        System.out.println("✅ Kitap iade alındı: " + book.getName()
                + " ← " + member.getName());
        return true;
    }

    public String getName() { return name; }
}