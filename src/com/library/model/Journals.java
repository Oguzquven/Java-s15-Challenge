package com.library.model;

public class Journals extends Book {
    private String issn;
    private String field;

    public Journals(String bookId, Author author, String name,
                    double price, String edition, String dateOfPurchase,
                    String issn, String field) {
        super(bookId, author, name, price, edition, dateOfPurchase);
        this.issn = issn;
        this.field = field;
    }

    @Override
    public String getBookType() { return "Dergi/Akademik"; }

    public String getIssn() { return issn; }
    public String getField() { return field; }
}