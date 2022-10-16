package dao;

import model.Product;

import java.util.List;

public interface IProductDAO {
    void insertProduct(Product product);

    Product selectProduct(String id);

    List<Product> selectAllProduct();

    boolean deleteProduct(String id);

    boolean updateProduct(Product product);

    boolean checkNameExits(String productName);

    List<Product> searchProduct(String searchStr);
}
