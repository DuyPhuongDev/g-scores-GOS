package com.dphuong.go.gscores.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Top10DTO {
    private String sbd;
    private String groupName;
    private BigDecimal total;
    private String subjectsInfo;
}