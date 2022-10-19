package model;

public class OrderItem {
    private int id;
    private String productId;
    private int price;
    private int quantity;
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(String productId, int price, int quantity, String orderId) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public OrderItem(int id, String productId, int price, int quantity, String orderId) {
        this.id = id;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
