package com.airportpus.domain.parking.domain.repository;

import com.airportpus.domain.parking.domain.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
}
