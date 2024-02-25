package ru.penzin.app.warehouse.product.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.penzin.app.warehouse.product.repository.ProductImageRepository;

@RestController
@RequestMapping(path = "api/products/image")
public class ProductImageController {

    private final ProductImageRepository productImageRepository;

    @Autowired
    public ProductImageController(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @GetMapping(path = "/{id}", produces = "image/*")
    public Resource downloadImage(@PathVariable Long id) {
        byte[] image = productImageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(image, "product image");
    }
}
