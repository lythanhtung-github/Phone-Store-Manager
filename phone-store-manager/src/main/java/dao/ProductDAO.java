package dao;

import model.Product;
import utils.AppUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    private static ProductDAO instance;

    private ProductDAO() {
    }

    public static ProductDAO getInstance() {
        if (instance == null) instance = new ProductDAO();
        return instance;
    }

    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();
    private static final String INSERT_PRODUCT = " insert into product(id, name, price, quantity, description, image, created_time) VALUE (?,?,?,?,?,?,?);";
    private static final String SELECT_PRODUCT_BY_ID = "select * from product where id =?;";
    private static final String SELECT_ALL_PRODUCTS = "select * from product;";
    private static final String DELETE_PRODUCT = "delete from product where id = ?;";
    private static final String UPDATE_PRODUCT = "update product set name =?, price=?, quantity =?, description =?, image=?, updated_time=? where id = ?;";

    private static final String CHECK_NAME_EXISTS = "SELECT * FROM product where name = ?";
    private static final String SEARCH_PRODUCT = "SELECT * FROM product WHERE id LIKE ? OR name LIKE ? OR description LIKE ? OR  price LIKE ? OR quantity LIKE ?;";

    @Override
    public void insertProduct(Product product) {
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {
            preparedStatement.setString(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getImage());
            preparedStatement.setString(7, AppUtils.localDateTimeToString(product.getCreatedTime()));
            System.out.println(this.getClass() + " insertProduct " + preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
    }

    @Override
    public Product selectProduct(String id) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectProduct: " + preparedStatement);
            while (rs.next()) {
                String productId = rs.getString("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String image = rs.getString("image");
                Product product = new Product(productId, name, price, quantity, description, image);
                LocalDateTime createdTime = AppUtils.stringToLocalDateTime(rs.getString("created_time"));
                product.setCreatedTime(createdTime);
                if (rs.getString("updated_time") != null) {
                    LocalDateTime updatedTime = AppUtils.stringToLocalDateTime(rs.getString("updated_time"));
                    product.setUpdatedTime(updatedTime);
                }
                return product;
            }
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllProduct " + preparedStatement);
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String image = rs.getString("image");
                Product product = new Product(id, name, price, quantity, description, image);
                LocalDateTime createdTime = AppUtils.stringToLocalDateTime(rs.getString("created_time"));
                product.setCreatedTime(createdTime);
                if (rs.getString("updated_time") != null) {
                    LocalDateTime updatedTime = AppUtils.stringToLocalDateTime(rs.getString("updated_time"));
                    product.setUpdatedTime(updatedTime);
                }
                products.add(product);
            }
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return products;
    }

    @Override
    public boolean deleteProduct(String id) {
        boolean rowDeleted = false;
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);) {
            preparedStatement.setString(1, id);
            System.out.println(this.getClass() + " deleteProduct " + preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean rowUpdated = false;
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setString(6, AppUtils.localDateTimeToString(product.getUpdatedTime()));
            preparedStatement.setString(7, product.getId());
            System.out.println(this.getClass() + " updateProduct " + preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return rowUpdated;
    }

    @Override
    public boolean checkNameExits(String productName) {
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_NAME_EXISTS);
            preparedStatement.setString(1, productName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        }catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return false;
    }

    @Override
    public List<Product> searchProduct(String searchStr) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT)) {
            preparedStatement.setString(1, "%"+searchStr+"%");
            preparedStatement.setString(2, "%"+searchStr+"%");
            preparedStatement.setString(3, "%"+searchStr+"%");
            preparedStatement.setString(4, "%"+searchStr+"%");
            preparedStatement.setString(5, "%"+searchStr+"%");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " searchProduct " + preparedStatement);
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String image = rs.getString("image");
                Product product = new Product(id, name, price, quantity, description, image);
                LocalDateTime createdTime = AppUtils.stringToLocalDateTime(rs.getString("created_time"));
                product.setCreatedTime(createdTime);
                if (rs.getString("updated_time") != null) {
                    LocalDateTime updatedTime = AppUtils.stringToLocalDateTime(rs.getString("updated_time"));
                    product.setUpdatedTime(updatedTime);
                }
                products.add(product);
            }
        } catch (SQLException ex) {
            AppUtils.printSQLException(ex);
        }
        return products;
    }
}
