package com.airportpus.domain.congestion.presentation.dto;

import com.airportpus.domain.congestion.service.dto.CongestionApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공항 혼잡도 응답 DTO")
public record CongestionResponse(
    @Schema(description = "전체구간 혼잡도 레벨(1:원활 , 2:보통, 3:혼잡, 4:매우혼잡)", example = "4") int cgdrAllLvl,
    @Schema(description = "1구간 혼잡도 레벨(1:원활 , 2:보통, 3:혼잡, 4:매우혼잡)", example = "1") int cgdrALvl,
    @Schema(description = "2구간 혼잡도 레벨(1:원활 , 2:보통, 3:혼잡, 4:매우혼잡)", example = "2") int cgdrBLvl,
    @Schema(description = "3구간 혼잡도 레벨(1:원활 , 2:보통, 3:혼잡, 4:매우혼잡)", example = "3") int cgdrCLvl
) {

  public static CongestionResponse from(CongestionApiResponse.CongestionInfo info) {
    return new CongestionResponse(
        info.cgdrAllLvl(),
        info.cgdrALvl(),
        info.cgdrBLvl(),
        info.cgdrCLvl()
    );
  }
}
