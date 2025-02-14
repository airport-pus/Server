package com.airportpus.domain.congestion.domain.repository;

import com.airportpus.domain.congestion.domain.Congestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongestionRepository extends JpaRepository<Congestion, Long> {
}
