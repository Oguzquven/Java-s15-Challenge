package com.library.model;

public abstract class Book {
    private String bookId;
    private Author author;
    private String name;
    private double price;
    private String status; // "mevcut" | "ödünçte" | "satıldı"
    private String edition;
    private String dateOfPurchase;

    public Book(String bookId, Author author, String name,
                double price, String edition, String dateOfPurchase) {
        this.bookId = bookId;
        this.author = author;
        this.name = name;
        this.price = price;
        this.status = "mevcut";
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
    }

    // Abstract method - her alt sınıf kendini tanımlar
    public abstract String getBookType();

    public String getTitle() { return name; }

    public Author getAuthor() { return author; }

    public void changeOwner(Person newOwner) {
        System.out.println("📦 '" + name + "' kitabının sahibi değişti → " + newOwner.getName());
    }

    public Person getOwner() {
        // Sahibi döndür (basit implementasyon)
        return author;
    }

    public String display() {
        return String.format("[%s] %s | Yazar: %s | Fiyat: %.2f TL | Durum: %s | Baskı: %s",
                getBookType(), name, author.getName(), price, status, edition);
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("🔄 '" + name + "' durumu güncellendi: " + newStatus);
    }

    public boolean isAvailable() {
        return "mevcut".equalsIgnoreCase(status);
    }

    // Getters & Setters
    public String getBookId() { return bookId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getEdition() { return edition; }
    public void setEdition(String edition) { this.edition = edition; }
    public String getDateOfPurchase() { return dateOfPurchase; }
    public void setDateOfPurchase(String date) { this.dateOfPurchase = date; }
    public void setAuthor(Author author) { this.author = author; }
}