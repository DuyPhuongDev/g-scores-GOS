package com.dphuong.go.gscores.controller;

import com.dphuong.go.gscores.dto.ExamResultResponse;
import com.dphuong.go.gscores.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/top-10-highest-scores")
    public ResponseEntity<List<ExamResultResponse>> getTopNGroup(
            @RequestParam(name = "group", defaultValue = "A00") String group) {

        return ResponseEntity.ok(examService.top10Group(group));
    }

}
