package com.dphuong.go.gscores.controller;

import com.dphuong.go.gscores.dto.ChartDataResponse;
import com.dphuong.go.gscores.dto.ExamResultResponse;
import com.dphuong.go.gscores.dto.Top10ScoresResponse;
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

    @GetMapping("/student")
    public ResponseEntity<ExamResultResponse> getExamResultBySbd(@RequestParam String sbd) {
        return ResponseEntity.ok(examService.getExamResultBySbd(sbd));
    }

    @GetMapping("/top-10-highest-scores")
    public ResponseEntity<List<Top10ScoresResponse>> getTop10Group(
            @RequestParam(name = "group", defaultValue = "A00") String group) {
        return ResponseEntity.ok(examService.top10Group(group));
    }

    @GetMapping("/stats")
    public ResponseEntity<List<ChartDataResponse>> getStats() {
        return ResponseEntity.ok(examService.getStats());
    }
}
