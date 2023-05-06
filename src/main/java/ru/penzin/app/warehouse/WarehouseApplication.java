package ru.penzin.app.warehouse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.penzin.app.warehouse.entity.Counterparty;
import ru.penzin.app.warehouse.entity.Product;
import ru.penzin.app.warehouse.entity.Shipment;
import ru.penzin.app.warehouse.entity.ShipmentType;
import ru.penzin.app.warehouse.repository.CounterpartyRepository;
import ru.penzin.app.warehouse.repository.ProductRepository;
import ru.penzin.app.warehouse.repository.ShipmentRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(ProductRepository productRepository,
                                             CounterpartyRepository counterpartyRepository,
                                             ShipmentRepository shipmentRepository) {
        return args -> {
            Product product = new Product();
            product.setName("Монитор Dell");
            product.setBarcode("11111111");
            product.setStock(200);

            productRepository.save(product);

            Counterparty counterparty = new Counterparty();
            counterparty.setName("ООО Рога и Копыта");
            counterparty.setAddress("г. Екатеринбург, ТРЦ Гринвич");

            counterpartyRepository.save(counterparty);

            Shipment shipment = new Shipment();
            shipment.setProduct(product);
            shipment.setCounterparty(counterparty);
            shipment.setShipmentType(ShipmentType.RECEIPT);
            shipment.setCount(200);
            shipment.setShipmentStart(LocalDateTime.of(2023, 5, 1, 8, 31));
            shipment.setShipmentEnd(LocalDateTime.of(2023, 5, 1, 9, 17));

            shipmentRepository.save(shipment);
        };
    }

}
