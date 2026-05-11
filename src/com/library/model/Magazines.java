package com.library.model;

public class Magazines extends Book {
    private String publisher;
    private int issueNumber;

    public Magazines(String bookId, Author author, String name,
                     double price, String edition, String dateOfPurchase,
                     String publisher, int issueNumber) {
        super(bookId, author, name, price, edition, dateOfPurchase);
        this.publisher = publisher;
        this.issueNumber = issueNumber;
    }

    @Override
    public String getBookType() { return "Magazin"; }

    public String getPublisher() { return publisher; }
    public int getIssueNumber() { return issueNumber; }
}