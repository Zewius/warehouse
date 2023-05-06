package ru.penzin.app.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.penzin.app.warehouse.entity.Counterparty;
import ru.penzin.app.warehouse.repository.CounterpartyRepository;

@Controller
@RequestMapping(path = "/counterparts")
public class CounterpartyController {

    private final CounterpartyRepository counterpartyRepository;

    @Autowired
    public CounterpartyController(CounterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }

    @GetMapping(path = {"/", ""})
    public String getCounterpartyList(Model model) {
        model.addAttribute("counterpartyList", counterpartyRepository.findAll());
        return "counterparts/index";
    }

    @GetMapping(path = {"/add"})
    public String getCounterpartyAddPage(Model model, Counterparty counterparty) {
        model.addAttribute("counterparty", counterparty);
        return "counterparts/add";
    }

    @PostMapping(path = "/add")
    public String addCounterparty(@ModelAttribute("counterparty") Counterparty counterparty) {
        counterpartyRepository.save(counterparty);
        return "redirect:/counterparts";
    }
}
