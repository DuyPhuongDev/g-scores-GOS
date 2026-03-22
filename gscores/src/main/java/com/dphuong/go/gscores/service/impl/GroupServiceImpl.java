package com.dphuong.go.gscores.service.impl;

import com.dphuong.go.gscores.dto.ExamGroupResponse;
import com.dphuong.go.gscores.repository.ExamGroupRepository;
import com.dphuong.go.gscores.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final ExamGroupRepository examGroupRepository;

    @Override
    public List<ExamGroupResponse> getAllGroups() {
        return examGroupRepository.findAll(Sort.by("groupName").ascending()).stream().map(ExamGroupResponse::toResponse).toList();
    }
}
