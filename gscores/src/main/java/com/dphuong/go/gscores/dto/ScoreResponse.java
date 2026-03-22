package com.dphuong.go.gscores.dto;

import com.dphuong.go.gscores.entity.Score;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoreResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private BigDecimal score;

    public static ScoreResponse toResponse(Score entity) {
        return ScoreResponse.builder()
                .id(entity.getId())
                .name(entity.getSubject().getName())
                .score(entity.getScore())
                .build();
    }
}
