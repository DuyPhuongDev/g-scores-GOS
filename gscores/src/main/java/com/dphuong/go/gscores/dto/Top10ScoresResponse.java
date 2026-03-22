package com.dphuong.go.gscores.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Top10ScoresResponse {
    private String sbd;
    private String groupName;
    private List<ScoreResponse> scores;
    private BigDecimal total;
}
