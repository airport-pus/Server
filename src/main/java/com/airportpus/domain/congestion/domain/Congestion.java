package com.airportpus.domain.congestion.domain;

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
public class Congestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private int cgdrAllLvl;

  @NotNull
  private int cgdrALvl;

  @NotNull
  private int cgdrBLvl;

  @NotNull
  private int cgdrCLvl;

  @CreatedDate
  private LocalDateTime createdAt;

  @Builder
  public Congestion(int cgdrAllLvl, int cgdrALvl, int cgdrBLvl, int cgdrCLvl) {
    this.cgdrAllLvl = cgdrAllLvl;
    this.cgdrALvl = cgdrALvl;
    this.cgdrBLvl = cgdrBLvl;
    this.cgdrCLvl = cgdrCLvl;
  }
}
