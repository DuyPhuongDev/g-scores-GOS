package com.dphuong.go.gscores.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartDataResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long subjectId;
    private String subjectName;
    private Long excellent;
    private Long good;
    private Long average;
    private Long weak;
    private Long totalStudents;
}
