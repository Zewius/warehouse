package ru.penzin.app.warehouse.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.penzin.app.warehouse.product.dto.AddProductDTO;
import ru.penzin.app.warehouse.product.entity.ProductImage;
import ru.penzin.app.warehouse.product.service.ProductService;

import java.io.IOException;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = {"/", ""})
    public String getProductList(Model model) {
        model.addAttribute("productList", productService.findAll());
        return "products/index";
    }

    @GetMapping(path = {"/add"})
    public String getProductAddPage(Model model, AddProductDTO product) {
        model.addAttribute("product", product);
        return "products/add";
    }

    @PostMapping(path = "/add")
    public String addProduct(@ModelAttribute("product") AddProductDTO product,
                             @RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            saveProductWithImage(product, image);
        } else {
            saveProduct(product);
        }

        return "redirect:/products";
    }

    private void saveProduct(AddProductDTO product) {
        productService.save(product);
    }

    private void saveProductWithImage(AddProductDTO product, MultipartFile image) {
        try {
            ProductImage productImage = new ProductImage();
            productImage.setName(image.getOriginalFilename());
            productImage.setType(image.getContentType());
            productImage.setContent(image.getBytes());

            productService.save(product, productImage);
        } catch (IOException exception) {
            throw new IllegalArgumentException(exception);
        }

    }
}
