package ru.penzin.app.warehouse.counterparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.penzin.app.warehouse.counterparty.entity.Counterparty;

@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {
}
