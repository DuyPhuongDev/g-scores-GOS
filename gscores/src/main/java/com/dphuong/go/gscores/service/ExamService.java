package com.dphuong.go.gscores.service;

import com.dphuong.go.gscores.dto.ChartDataResponse;
import com.dphuong.go.gscores.dto.ExamResultResponse;
import com.dphuong.go.gscores.dto.Top10ScoresResponse;


import java.util.List;

public interface ExamService {

    ExamResultResponse getExamResultBySbd(String sbd);

    List<Top10ScoresResponse> top10Group(String groupName);

    List<ChartDataResponse> getStats();
}
