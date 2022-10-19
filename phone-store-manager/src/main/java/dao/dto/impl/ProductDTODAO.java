package dao.dto.impl;

import dao.dto.IProductDTODAO;
import dao.impl.ConnectionDAO;
import model.dto.CustomerDTO;
import model.dto.ProductDTO;
import utils.AppUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDTODAO implements IProductDTODAO {
    private static ProductDTODAO instance;

    private ProductDTODAO() {
    }

    public static ProductDTODAO getInstance() {
        if (instance == null) instance = new ProductDTODAO();
        return instance;
    }

    private final ConnectionDAO connectionDAO = ConnectionDAO.getInstance();

    private static final String SELECT_PRODUCT_RANK =
            "SELECT product.image as product_image\n" +
                    ", product.name as product_name\n" +
                    ", product.price as product_price\n" +
                    ", SUM(order_item.quantity) as order_count\n" +
                    ", product.description as product_description\n" +
                    "FROM product \n" +
                    "JOIN order_item\n" +
                    "ON product.id = order_item.product_id\n" +
                    "GROUP BY order_item.product_id\n" +
                    "LIMIT 0,5;\n";

    @Override
    public List<ProductDTO> selectProductRank() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        try {
            Connection connection = connectionDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_RANK);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectProductRank " + preparedStatement);
            while (rs.next()) {
                ProductDTO productDTO = getProductDTOFromResultSet(rs);
                productDTOList.add(productDTO);
            }
            return productDTOList;
        } catch (SQLException e) {
            AppUtils.printSQLException(e);
        }
        return null;
    }

    private ProductDTO getProductDTOFromResultSet(ResultSet rs) throws SQLException {
        String productImage = rs.getString("product_image");
        String productName = rs.getString("product_name");
        int productPrice = rs.getInt("product_price");
        int orderCount = rs.getInt("order_count");
        String productDescription = rs.getString("product_description");
        ProductDTO productDTO = new ProductDTO(
                productImage,
                productName,
                productPrice,
                orderCount,
                productDescription
        );
        return productDTO;
    }

}
