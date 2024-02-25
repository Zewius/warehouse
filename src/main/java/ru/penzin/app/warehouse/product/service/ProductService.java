package ru.penzin.app.warehouse.product.service;

import org.springframework.stereotype.Service;
import ru.penzin.app.warehouse.product.dto.AddProductDTO;
import ru.penzin.app.warehouse.product.dto.ProductDTO;
import ru.penzin.app.warehouse.product.entity.Product;
import ru.penzin.app.warehouse.product.entity.ProductImage;
import ru.penzin.app.warehouse.product.repository.ProductImageRepository;
import ru.penzin.app.warehouse.product.repository.ProductRepository;

import java.util.List;

/**
 * Сервис работы с товарами склада
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository,
                          ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(this::mapEntityToDTO)
                .toList();
    }

    public void save(AddProductDTO productDTO) {
        productRepository.save(mapDTOtoEntity(productDTO));
    }

    public void save(AddProductDTO productDTO, ProductImage image) {
        productImageRepository.save(image);

        Product product = mapDTOtoEntity(productDTO);
        product.setImage(image);
        productRepository.save(product);
    }

    private ProductDTO mapEntityToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .barcode(product.getBarcode())
                .name(product.getName())
                .stock(product.getStock())
                .imageId(product.getImage() != null ? product.getImage().getId() : null)
                .build();
    }

    private Product mapDTOtoEntity(AddProductDTO productDTO) {
        Product product = new Product();
        product.setBarcode(productDTO.barcode());
        product.setName(productDTO.name());
        product.setStock(productDTO.stock());
        return product;
    }
}
