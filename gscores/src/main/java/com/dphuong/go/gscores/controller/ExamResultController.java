package com.dphuong.go.gscores.controller;

import com.dphuong.go.gscores.dto.ExamResultResponse;
import com.dphuong.go.gscores.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exam-results")
@RequiredArgsConstructor
public class ExamResultController {

    private final ExamService examService;

    @GetMapping("/{sbd}")
    public ResponseEntity<ExamResultResponse> getExamResultBySbd(@PathVariable String sbd) {
        return ResponseEntity.ok(examService.getExamResultBySbd(sbd));
    }

    @GetMapping("/top-10-group-a")
    public ResponseEntity<List<ExamResultResponse>> getTop10GroupA() {
        return ResponseEntity.ok(examService.top10GroupA());
    }

}
