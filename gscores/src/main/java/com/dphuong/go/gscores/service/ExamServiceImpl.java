package com.dphuong.go.gscores.service;

import com.dphuong.go.gscores.dto.ExamResultResponse;
import com.dphuong.go.gscores.entity.ExamResult;
import com.dphuong.go.gscores.repository.ExamResultRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return examResultRepository.getTop10A00();
    }

    @Override
    public List<ExamResultResponse> top10Group(String groupName) {
        return switch (groupName.trim().toUpperCase()) {
            case "A00" -> examResultRepository.getTop10A00();
            case "A01" -> examResultRepository.getTop10A01();
            case "B" -> examResultRepository.getTop10B();
            case "C" -> examResultRepository.getTop10C();
            case "D01" -> examResultRepository.getTop10D01();
            case "D07" -> examResultRepository.getTop10D07();
            default -> throw new IllegalArgumentException("Invalid group name");
        };
    }



}
