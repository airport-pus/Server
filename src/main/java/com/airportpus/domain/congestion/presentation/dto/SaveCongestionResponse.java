package com.airportpus.domain.congestion.presentation.dto;

import com.airportpus.domain.congestion.domain.Congestion;
import io.swagger.v3.oas.annotations.media.Schema;


import java.time.format.DateTimeFormatter;

@Schema(description = "저장된 공항 혼잡도 응답 DTO")
public record SaveCongestionResponse(
    @Schema(description = "전체구간 혼잡도 레벨(1:원활 , 2:보통, 3:혼잡, 4:매우혼잡)", example = "4") int cgdrAllLvl,
    @Schema(description = "1구간 혼잡도 레벨(1:원활 , 2:보통, 3:혼잡, 4:매우혼잡)", example = "1") int cgdrALvl,
    @Schema(description = "2구간 혼잡도 레벨(1:원활 , 2:보통, 3:혼잡, 4:매우혼잡)", example = "2") int cgdrBLvl,
    @Schema(description = "3구간 혼잡도 레벨(1:원활 , 2:보통, 3:혼잡, 4:매우혼잡)", example = "3") int cgdrCLvl,
    @Schema(description = "저장된 시간", example = "2025-02-14 09시") String date
) {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH시");

  public static SaveCongestionResponse from(Congestion congestion) {
    return new SaveCongestionResponse(
        congestion.getCgdrAllLvl(),
        congestion.getCgdrALvl(),
        congestion.getCgdrBLvl(),
        congestion.getCgdrCLvl(),
        congestion.getCreatedAt().format(FORMATTER)
    );
  }
}
