package com.airportpus.domain.apron.presentation.dto;

import com.airportpus.domain.apron.service.dto.ApronApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "주기장 도착 응답 DTO")
public record ApronInResponse(
    @Schema(description = "항공편 번호", example = "KE123") String flightNumber,
    @Schema(description = "항공사 영문명", example = "Korean Air") String airlineEnglish,
    @Schema(description = "항공사 한글명", example = "대한항공") String airlineKorean,
    @Schema(description = "도착 영문 정보", example = "Incheon") String arrivedEng,
    @Schema(description = "도착 한글 정보", example = "인천") String arrivedKor,
    @Schema(description = "수하물 클레임", example = "5") String baggageClaim,
    @Schema(description = "탑승 영문 정보", example = "Gimhae") String boardingEng,
    @Schema(description = "탑승 한글 정보", example = "부산/김해") String boardingKor,
    @Schema(description = "예정 출발 시간", example = "1800") String std,
    @Schema(description = "예상 출발 시간", example = "1810") String etd,
    @Schema(description = "입/출국 구분", example = "I") String io,
    @Schema(description = "노선 정보", example = "국제") String line,
    @Schema(description = "영문 비고", example = "BOARDING") String remarkEng,
    @Schema(description = "한글 비고", example = "탑승중") String remarkKor
) {

  public static ApronInResponse from(ApronApiResponse.ApronInfo info) {
    return new ApronInResponse(
        info.getFlightNumber(),
        info.getAirlineEnglish(),
        info.getAirlineKorean(),
        info.getArrivedEng(),
        info.getArrivedKor(),
        info.getBaggageClaim(),
        info.getBoardingEng(),
        info.getBoardingKor(),
        info.getStd(),
        info.getEtd(),
        info.getIo(),
        info.getLine(),
        info.getRemarkEng(),
        info.getRemarkKor()
    );
  }
}
