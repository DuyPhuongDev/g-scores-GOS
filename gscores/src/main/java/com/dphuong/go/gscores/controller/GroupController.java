package com.dphuong.go.gscores.controller;

import com.dphuong.go.gscores.dto.ExamGroupResponse;
import com.dphuong.go.gscores.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<List<ExamGroupResponse>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }
}
