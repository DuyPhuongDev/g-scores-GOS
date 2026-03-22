package com.dphuong.go.gscores.service.impl;

import com.dphuong.go.gscores.dto.*;
import com.dphuong.go.gscores.entity.Student;
import com.dphuong.go.gscores.repository.ExamGroupRepository;
import com.dphuong.go.gscores.repository.ScoreRepository;
import com.dphuong.go.gscores.repository.StudentRepository;
import com.dphuong.go.gscores.service.ExamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamServiceImpl implements ExamService {

    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;
    private final ObjectMapper objectMapper;
    private final ExamGroupRepository examGroupRepository;

    @Override
    @Transactional(readOnly = true)
    public ExamResultResponse getExamResultBySbd(String sbd) {
        log.info("getExamResultBySbd: {}", sbd);

        Student student = studentRepository.findById(sbd)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + sbd));

        List<ScoreResponse> scores = student.getScores().stream().map(ScoreResponse::toResponse).toList();


        return ExamResultResponse.builder()
                .sbd(student.getSbd())
                .foreignLanguageCode(student.getForeignLanguageCode())
                .scores(scores)
                .build();
    }

    @Override
    @Cacheable(value = "top10Group", key = "#groupName")
    public List<Top10ScoresResponse> top10Group(String groupName) {
        log.info("top10Group: {}", groupName);

        if(!examGroupRepository.existsByGroupName(groupName)) throw new EntityNotFoundException("Group not found: " + groupName);

        List<Object[]> results = scoreRepository.findTop10ByGroupName(groupName);

        return parseTop10Response(results);
    }

    @Override
    @Cacheable(value = "stats")
    public List<ChartDataResponse> getStats() {
        return scoreRepository.getChartData();
    }

    private List<Top10ScoresResponse> parseTop10Response(List<Object[]> rawResults) {
        // PHẢI có return và collect ở cuối stream
        return rawResults.stream().map(row -> {
            String sbd = String.valueOf(row[0]);
            String groupName = String.valueOf(row[1]);

            // 1. Xử lý Total an toàn
            BigDecimal total = BigDecimal.ZERO;
            if (row[2] != null) {
                total = (row[2] instanceof BigDecimal) ? (BigDecimal) row[2] : new BigDecimal(row[2].toString());
            }

            // 2. Parse JSON Subjects
            List<ScoreResponse> scores = new ArrayList<>();
            if (row[3] != null) {
                try {
                    String jsonStr = row[3].toString();
                    // GÁN LẠI giá trị cho biến subjects sau khi parse
                    scores = objectMapper.readValue(jsonStr, new TypeReference<List<ScoreResponse>>() {});
                } catch (JsonProcessingException e) {
                    log.error("Lỗi parse JSON cho SBD {}: {}", sbd, e.getMessage());
                    // Trả về list rỗng cho subjects nếu lỗi parse dòng này
                }
            }

            // 3. Build Response
            return Top10ScoresResponse.builder()
                    .sbd(sbd)
                    .groupName(groupName)
                    .total(total)
                    .scores(scores)
                    .build();
        }).collect(Collectors.toList()); // THÊM DÒNG NÀY ĐỂ TRẢ VỀ LIST
    }

}
