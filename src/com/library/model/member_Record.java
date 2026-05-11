package com.library.model;

public abstract class member_Record {
    private String memberId;
    private String type;
    private String dateOfMembership;
    private int noBooksIssued;
    private int maxBookLimit;
    private String name;
    private String address;
    private String phoneNo;
    private double bill;

    public member_Record(String memberId, String type, String dateOfMembership,
                         int maxBookLimit, String name, String address, String phoneNo) {
        this.memberId = memberId;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = 0;
        this.maxBookLimit = maxBookLimit;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.bill = 0.0;
    }

    public String getMember() {
        return String.format("ID: %s | Ad: %s | Tür: %s | Verilen: %d/%d | Telefon: %s",
                memberId, name, type, noBooksIssued, maxBookLimit, phoneNo);
    }

    public boolean incBookIssued() {
        if (noBooksIssued >= maxBookLimit) {
            System.out.println("❌ " + name + " kitap limitine ulaştı! (" + maxBookLimit + ")");
            return false;
        }
        noBooksIssued++;
        return true;
    }

    public void decBookIssued() {
        if (noBooksIssued > 0) noBooksIssued--;
    }

    public void payBill(double amount) {
        bill += amount;
        System.out.printf("💳 %s ödeme yaptı: %.2f TL | Toplam borç: %.2f TL%n",
                name, amount, bill);
    }

    public abstract String getMemberType();

    // Getters & Setters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public int getNoBooksIssued() { return noBooksIssued; }
    public int getMaxBookLimit() { return maxBookLimit; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
    public double getBill() { return bill; }
    public String getDateOfMembership() { return dateOfMembership; }
}