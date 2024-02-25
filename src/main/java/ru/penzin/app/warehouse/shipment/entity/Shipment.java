package ru.penzin.app.warehouse.shipment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import ru.penzin.app.warehouse.counterparty.entity.Counterparty;
import ru.penzin.app.warehouse.product.entity.Product;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipment_type")
    private ShipmentType shipmentType;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "counterparty_id", referencedColumnName = "id")
    private Counterparty counterparty;

    @Column(name = "shipment_start")
    private LocalDateTime shipmentStart;

    @Column(name = "shipment_end")
    private LocalDateTime shipmentEnd;

    @Column(name = "count")
    private Integer count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Shipment shipment = (Shipment) o;
        return getId() != null && Objects.equals(getId(), shipment.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
