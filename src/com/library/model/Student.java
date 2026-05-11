package com.library.model;

public class Student extends member_Record {
    private String studentId;
    private String department;

    public Student(String memberId, String dateOfMembership,
                   String name, String address, String phoneNo,
                   String studentId, String department) {
        super(memberId, "Öğrenci", dateOfMembership, 5, name, address, phoneNo);
        this.studentId = studentId;
        this.department = department;
    }

    @Override
    public String getMemberType() { return "Öğrenci"; }

    public String getStudentId() { return studentId; }
    public String getDepartment() { return department; }

    @Override
    public String getMember() {
        return super.getMember() + " | Bölüm: " + department;
    }
}