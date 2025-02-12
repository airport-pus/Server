package com.airportpus.domain.parking.domain.repository;

import com.airportpus.domain.parking.domain.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
}
