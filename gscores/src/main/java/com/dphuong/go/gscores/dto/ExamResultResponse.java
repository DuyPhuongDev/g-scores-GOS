package com.dphuong.go.gscores.dto;

import com.dphuong.go.gscores.entity.ExamResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.sql.rowset.spi.SyncResolver;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class ExamResultResponse {
    private String sbd;
    private BigDecimal math;
    private BigDecimal literature;
    private BigDecimal foreignLanguage;
    private BigDecimal physics;
    private BigDecimal chemistry;
    private BigDecimal biology;
    private BigDecimal history;
    private BigDecimal geography;
    private BigDecimal civicEducation;
    private String foreignLanguageCode;
    private BigDecimal total;

    public static ExamResultResponse toResponse(ExamResult examResult) {
        return ExamResultResponse.builder()
                .sbd(examResult.getSbd())
                .math(examResult.getMath())
                .literature(examResult.getLiterature())
                .foreignLanguage(examResult.getForeignLanguage())
                .physics(examResult.getPhysics())
                .chemistry(examResult.getChemistry())
                .biology(examResult.getBiology())
                .history(examResult.getHistory())
                .geography(examResult.getGeography())
                .civicEducation(examResult.getCivicEducation())
                .foreignLanguageCode(examResult.getForeignLanguageCode())
                .build();
    }
}
