package com.hms.repository.dmo;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = true, length = 20)
    private String dob;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = true, length = 20)
    private Long phoneNo;

    @Column(nullable = true, length = 100)
    private String fatherName;

    @Column(nullable = true, length = 100)
    private String motherName;

    @Column(nullable = true, length = 20)
    private Long parentPhoneNo;

    @Column(nullable = true, length = 200)
    private String address;

    @Column(nullable = true, length = 10)
    private Long roomNo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Long getParentPhoneNo() {
        return parentPhoneNo;
    }

    public void setParentPhoneNo(Long parentPhoneNo) {
        this.parentPhoneNo = parentPhoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Long roomNo) {
        this.roomNo = roomNo;
    }
}
