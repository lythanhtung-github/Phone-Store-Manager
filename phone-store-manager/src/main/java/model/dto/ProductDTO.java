package model.dto;

public class ProductDTO {
    private String productImage;
    private String productName;
    private int productPrice;
    private int orderCount;
    private String productDescription;

    public ProductDTO(String productImage, String productName, int productPrice, int orderCount, String productDescription) {
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderCount = orderCount;
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
