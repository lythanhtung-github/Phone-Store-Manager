package dao.dto;

import model.dto.ProductDTO;

import java.util.List;

public interface IProductDTODAO {
    List<ProductDTO> selectProductRank();
}
