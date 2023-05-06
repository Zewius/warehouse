package ru.penzin.app.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.penzin.app.warehouse.entity.Shipment;
import ru.penzin.app.warehouse.repository.CounterpartyRepository;
import ru.penzin.app.warehouse.repository.ProductRepository;
import ru.penzin.app.warehouse.repository.ShipmentRepository;
import ru.penzin.app.warehouse.service.ShipmentService;

@Controller
@RequestMapping(path = "/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping(path = {"/", ""})
    public String getShipmentList(Model model) {
        model.addAttribute("shipmentList", shipmentService.getShipments());
        return "shipments/index";
    }

    @GetMapping(path = "/add")
    public String getShipmentAddPage(RedirectAttributes redirectAttrs, Model model, Shipment shipment) {
        if (shipmentService.getProducts().isEmpty() || shipmentService.getCounterparts().isEmpty()) {
            redirectAttrs.addFlashAttribute("warn", "Внимание! В базе данных отсутствует информация о товарах или контрагентах, регистрация поставки недоступна!");
            return "redirect:/shipments";
        }

        model.addAttribute("products", shipmentService.getProducts());
        model.addAttribute("counterparts", shipmentService.getCounterparts());
        model.addAttribute("shipment", shipment);
        return "shipments/add";
    }

    @PostMapping(path = "/add")
    public String addShipment(@RequestParam("product") Long productId,
                              @RequestParam("counterparty") Long counterpartyId,
                              @ModelAttribute("shipment") Shipment shipment) {
        shipmentService.createShipment(shipment, productId, counterpartyId);
        return "redirect:/shipments";
    }
}
