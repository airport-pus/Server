package com.airportpus.domain.parking.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Parking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String airportCodeName;

  @NotNull
  private String congestion;

  @NotNull
  private String congestionDegree;

  @NotNull
  private int occupiedSpace;

  @NotNull
  private int totalSpace;

  @NotNull
  private int remainingSpace;

  @CreatedDate
  private LocalDateTime createdAt;

  @Builder
  public Parking(String airportCodeName, String congestion, String congestionDegree, int occupiedSpace, int totalSpace, int remainingSpace) {
    this.airportCodeName = airportCodeName;
    this.congestion = congestion;
    this.congestionDegree = congestionDegree;
    this.occupiedSpace = occupiedSpace;
    this.totalSpace = totalSpace;
    this.remainingSpace = remainingSpace;
  }
}