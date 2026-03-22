package com.dphuong.go.gscores.dto;

import com.dphuong.go.gscores.entity.ExamGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamGroupResponse {
    private Long id;
    private String groupName;
    private List<SubjectResponse> subjects;

    public static ExamGroupResponse toResponse(ExamGroup examGroup) {
        return ExamGroupResponse.builder()
                .id(examGroup.getId())
                .groupName(examGroup.getGroupName())
                .subjects(examGroup.getSubjects().stream().map(SubjectResponse::toResponse).toList())
                .build();
    }
}
