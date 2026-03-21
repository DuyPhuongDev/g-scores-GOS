package com.dphuong.go.gscores.service;

import com.dphuong.go.gscores.dto.ExamResultResponse;


import java.util.List;

public interface ExamService {

    ExamResultResponse getExamResultBySbd(String sbd);

    List<ExamResultResponse> top10GroupA();

    List<ExamResultResponse> top10Group(String groupName);
}
