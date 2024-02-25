package ru.penzin.app.warehouse.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.penzin.app.warehouse.product.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
