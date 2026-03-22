package com.dphuong.go.gscores.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartDataResponse {
    private Long subjectId;
    private String subjectName;
    private Long excellent;
    private Long good;
    private Long average;
    private Long weak;
    private Long totalStudents;
}
