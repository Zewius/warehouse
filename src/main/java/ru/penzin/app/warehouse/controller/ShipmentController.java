package ru.penzin.app.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.penzin.app.warehouse.repository.ShipmentRepository;

@Controller
@RequestMapping(path = "/shipments")
public class ShipmentController {

    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentController(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @GetMapping(path = {"/", ""})
    public String getShipmentList(Model model) {
        model.addAttribute("shipmentList", shipmentRepository.findAll());
        return "shipments/index";
    }
}
