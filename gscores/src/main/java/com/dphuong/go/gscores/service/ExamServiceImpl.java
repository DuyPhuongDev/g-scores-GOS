package com.dphuong.go.gscores.service;

import com.dphuong.go.gscores.dto.ExamResultResponse;
import com.dphuong.go.gscores.repository.ExamResultRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamServiceImpl implements ExamService {
    private final ExamResultRepository examResultRepository;

    @Override
    @Transactional(readOnly = true)
    public ExamResultResponse getExamResultBySbd(String sbd) {
        log.info("getExamResultBySbd {}", sbd);
        return ExamResultResponse.toResponse(examResultRepository.findById(sbd)
                .orElseThrow(() -> new EntityNotFoundException("Exam Result Not Found")));
    }

    @Override
    public List<ExamResultResponse> top10GroupA() {
        return examResultRepository.getTop10GroupA().stream().map(ExamResultResponse::toResponse).toList();
    }
}
