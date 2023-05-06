package ru.penzin.app.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.penzin.app.warehouse.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
