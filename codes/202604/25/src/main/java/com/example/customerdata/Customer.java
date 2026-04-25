package com.example.customerdata;

// 고객 정보를 담는 POJO (Plain Old Java Object)
public class Customer {
    private String id;
    private String name;
    private String email;
    private String membershipLevel;

    public Customer(String id, String name, String email, String membershipLevel) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.membershipLevel = membershipLevel;
    }

    // Getter 메서드들
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    @Override
    public String toString() {
        return "Customer{" +
               "id='" + id + ''' +
               ", name='" + name + ''' +
               ", email='" + email + ''' +
               ", membershipLevel='" + membershipLevel + ''' +
               '}';
    }
}
