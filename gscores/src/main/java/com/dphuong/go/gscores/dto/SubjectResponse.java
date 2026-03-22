package com.dphuong.go.gscores.dto;

import com.dphuong.go.gscores.entity.Subject;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponse {
    private Long id;
    private String subjectName;

    public static SubjectResponse toResponse(Subject subject) {
        return SubjectResponse.builder()
                .id(subject.getId())
                .subjectName(subject.getName())
                .build();
    }
}
