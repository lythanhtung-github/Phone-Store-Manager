package model.dto;

public class OrderDayDTO {
    private String customerFullName;
    private String customerAddress;
    private int orderGrandTotal;
    private String statusType;

    public OrderDayDTO(String customerFullName, String customerAddress, int orderGrandTotal, String statusType) {
        this.customerFullName = customerFullName;
        this.customerAddress = customerAddress;
        this.orderGrandTotal = orderGrandTotal;
        this.statusType = statusType;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getOrderGrandTotal() {
        return orderGrandTotal;
    }

    public void setOrderGrandTotal(int orderGrandTotal) {
        this.orderGrandTotal = orderGrandTotal;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }
}
