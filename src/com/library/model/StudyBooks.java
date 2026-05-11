package com.library.model;

public class StudyBooks extends Book {
    private String subject;
    private String gradeLevel;

    public StudyBooks(String bookId, Author author, String name,
                      double price, String edition, String dateOfPurchase,
                      String subject, String gradeLevel) {
        super(bookId, author, name, price, edition, dateOfPurchase);
        this.subject = subject;
        this.gradeLevel = gradeLevel;
    }

    @Override
    public String getBookType() { return "Ders Kitabı"; }

    public String getSubject() { return subject; }
    public String getGradeLevel() { return gradeLevel; }
}