package ru.penzin.app.warehouse.shipment.entity;

public enum ShipmentType {
    RECEIPT("Приход"), SALE("Продажа");

    public final String title;

    ShipmentType(String title) {
        this.title = title;
    }
}
