package model;

import java.time.LocalDateTime;

public class Customer {
    private String id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String image;
    private String password;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public Customer() {
    }

    public Customer(String id, String fullName, String email, String phoneNumber, String address, String image, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.image = image;
        this.password = password;
    }

    public Customer(String id, String fullName, String email, String phoneNumber, String address, String image, String password, LocalDateTime createdTime) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.image = image;
        this.password = password;
        this.createdTime = createdTime;
    }

    public Customer(String id, String fullName, String email, String phoneNumber, String address, String image, String password, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.image = image;
        this.password = password;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
