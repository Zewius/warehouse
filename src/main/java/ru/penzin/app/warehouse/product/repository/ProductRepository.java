package ru.penzin.app.warehouse.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.penzin.app.warehouse.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
