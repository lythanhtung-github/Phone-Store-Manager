package model;

public class OrderItem {
    private String id;
    private String productId;
    private double price;
    private int quantity;
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(String id, String productId, double price, int quantity, String orderId) {
        this.id = id;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
