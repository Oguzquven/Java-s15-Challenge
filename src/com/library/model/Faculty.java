package com.library.model;

public class Faculty extends member_Record {
    private String facultyId;
    private String department;
    private String position;

    public Faculty(String memberId, String dateOfMembership,
                   String name, String address, String phoneNo,
                   String facultyId, String department, String position) {
        super(memberId, "Akademisyen", dateOfMembership, 10, name, address, phoneNo);
        this.facultyId = facultyId;
        this.department = department;
        this.position = position;
    }

    @Override
    public String getMemberType() { return "Akademisyen"; }

    public String getFacultyId() { return facultyId; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }

    @Override
    public String getMember() {
        return super.getMember() + " | Unvan: " + position + " | Bölüm: " + department;
    }
}