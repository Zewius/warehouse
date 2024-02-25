package ru.penzin.app.warehouse.shipment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.penzin.app.warehouse.counterparty.entity.Counterparty;
import ru.penzin.app.warehouse.product.entity.Product;
import ru.penzin.app.warehouse.shipment.entity.Shipment;
import ru.penzin.app.warehouse.counterparty.repository.CounterpartyRepository;
import ru.penzin.app.warehouse.product.repository.ProductRepository;
import ru.penzin.app.warehouse.shipment.repository.ShipmentRepository;

import java.util.List;

import static ru.penzin.app.warehouse.shipment.entity.ShipmentType.SALE;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ProductRepository productRepository;
    private final CounterpartyRepository counterpartyRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository, ProductRepository productRepository, CounterpartyRepository counterpartyRepository) {
        this.shipmentRepository = shipmentRepository;
        this.productRepository = productRepository;
        this.counterpartyRepository = counterpartyRepository;
    }

    public List<Shipment> getShipments() {
        return shipmentRepository.findAll();
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Counterparty> getCounterparts() {
        return counterpartyRepository.findAll();
    }

    public void createShipment(Shipment shipment, Long productId, Long counterpartyId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        Counterparty counterparty = counterpartyRepository.findById(counterpartyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        if (shipment.getCount() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (shipment.getShipmentType().equals(SALE)) {
            if (shipment.getCount() > product.getStock()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            product.setStock(product.getStock() - shipment.getCount());
        } else {
            product.setStock(product.getStock() + shipment.getCount());
        }

        productRepository.save(product);

        shipment.setProduct(product);
        shipment.setCounterparty(counterparty);

        shipmentRepository.save(shipment);
    }
}
