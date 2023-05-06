package ru.penzin.app.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.penzin.app.warehouse.entity.Product;
import ru.penzin.app.warehouse.repository.ProductRepository;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = {"/", ""})
    public String getProductList(Model model) {
        model.addAttribute("productList", productRepository.findAll());
        return "products/index";
    }

    @GetMapping(path = {"/add"})
    public String getProductAddPage(Model model, Product product) {
        model.addAttribute("product", product);
        return "products/add";
    }

    @PostMapping(path = "/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }
}
