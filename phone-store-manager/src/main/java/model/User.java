package model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private String id;
    private String fullName;
    private LocalDate birthDay;
    private String phoneNumber;
    private String email;
    private String address;
    private String image;
    private int role;
    private String password;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public User() {
    }

    public User(String id, String fullName, LocalDate birthDay, String phoneNumber, String email, String address, String image, int role) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.image = image;
        this.role = role;
    }

    public User(String id, String fullName, LocalDate birthDay, String phoneNumber, String email, String address, String image, int role, String password) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.image = image;
        this.role = role;
        this.password = password;
    }

    public User(String id, String fullName, LocalDate birthDay, String phoneNumber, String email, String address, String image, int role, String password, LocalDateTime createdTime) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.image = image;
        this.role = role;
        this.password = password;
        this.createdTime = createdTime;
    }

    public User(String id, String fullName, LocalDate birthDay, String phoneNumber, String email, String address, String image, int role, String password, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.image = image;
        this.role = role;
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
