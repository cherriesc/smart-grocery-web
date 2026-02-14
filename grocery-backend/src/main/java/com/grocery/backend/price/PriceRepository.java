package com.grocery.backend.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select max(p.date) from Price p")
    LocalDate findLatestDate();

    List<Price> findByDateAndProduct_IdIn(LocalDate date, Collection<Long> productIds);
}

