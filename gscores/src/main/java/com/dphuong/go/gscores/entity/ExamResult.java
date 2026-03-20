package com.dphuong.go.gscores.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "exam_result")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamResult {

    @Id
    @Column(length = 20)
    private String sbd;

    @Column(precision = 5, scale = 3)
    private BigDecimal math;

    @Column(precision = 5, scale = 3)
    private BigDecimal literature;

    @Column(precision = 5, scale = 3)
    private BigDecimal foreignLanguage;

    @Column(precision = 5, scale = 3)
    private BigDecimal physics;

    @Column(precision = 5, scale = 3)
    private BigDecimal chemistry;

    @Column(precision = 5, scale = 3)
    private BigDecimal biology;

    @Column(precision = 5, scale = 3)
    private BigDecimal history;

    @Column(precision = 5, scale = 3)
    private BigDecimal geography;

    @Column(precision = 5, scale = 3)
    private BigDecimal civicEducation;

    @Column(length = 10)
    private String foreignLanguageCode;
}
