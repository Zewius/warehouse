package ru.penzin.app.warehouse.product.dto;

import lombok.Builder;

@Builder
public record ProductDTO(Long id, String barcode, String name, Integer stock, Long imageId) {
}
