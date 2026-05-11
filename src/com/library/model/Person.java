package com.library.model;

public abstract class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public abstract String whoyouare();

    @Override
    public String toString() {
        return whoyouare() + ": " + name;
    }
}