package ru.penzin.app.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.penzin.app.warehouse.entity.Shipment;
import ru.penzin.app.warehouse.repository.CounterpartyRepository;
import ru.penzin.app.warehouse.repository.ProductRepository;
import ru.penzin.app.warehouse.repository.ShipmentRepository;

@Controller
@RequestMapping(path = "/shipments")
public class ShipmentController {

    private final ShipmentRepository shipmentRepository;
    private final ProductRepository productRepository;
    private final CounterpartyRepository counterpartyRepository;

    @Autowired
    public ShipmentController(ShipmentRepository shipmentRepository, ProductRepository productRepository, CounterpartyRepository counterpartyRepository) {
        this.shipmentRepository = shipmentRepository;
        this.productRepository = productRepository;
        this.counterpartyRepository = counterpartyRepository;
    }

    @GetMapping(path = {"/", ""})
    public String getShipmentList(Model model) {
        model.addAttribute("shipmentList", shipmentRepository.findAll());
        return "shipments/index";
    }

    @GetMapping(path = "/add")
    public String getShipmentAddPage(Model model, Shipment shipment) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("counterparts", counterpartyRepository.findAll());
        model.addAttribute("shipment", shipment);
        return "shipments/add";
    }

    @PostMapping(path = "/add")
    public String addShipment(@RequestParam("product") Long productId,
                              @RequestParam("counterparty") Long counterpartyId,
                              @ModelAttribute("shipment") Shipment shipment) {
        shipment.setProduct(
                productRepository.findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        shipment.setCounterparty(
                counterpartyRepository.findById(counterpartyId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        shipmentRepository.save(shipment);
        return "redirect:/shipments";
    }
}
