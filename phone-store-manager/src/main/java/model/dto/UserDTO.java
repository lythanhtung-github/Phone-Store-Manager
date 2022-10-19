package model.dto;

public class UserDTO {
    private String userImage;
    private String userFullName;
    private String userAddress;
    private String userPhoneNumber;
    private int orderCount;

    public UserDTO(String userImage, String userFullName, String userAddress, String userPhoneNumber, int orderCount) {
        this.userImage = userImage;
        this.userFullName = userFullName;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.orderCount = orderCount;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}
