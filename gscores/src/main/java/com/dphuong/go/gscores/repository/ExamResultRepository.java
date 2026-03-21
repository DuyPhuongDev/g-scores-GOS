package com.dphuong.go.gscores.repository;

import com.dphuong.go.gscores.dto.ExamResultResponse;
import com.dphuong.go.gscores.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, String> {


    @Query(value = "SELECT *,(COALESCE(math, 0) + COALESCE(physics, 0) + COALESCE(chemistry, 0))  " +
            "FROM exam_result ORDER BY (COALESCE(math, 0) + COALESCE(physics, 0) + COALESCE(chemistry, 0)) DESC" +
            " LIMIT 10", nativeQuery = true)
    List<ExamResultResponse> getTop10A00();

    @Query(value = "SELECT *, (COALESCE(math, 0) + COALESCE(physics, 0) + COALESCE(foreign_language, 0))" +
            " FROM exam_result ORDER BY (COALESCE(math, 0) + COALESCE(physics, 0) + COALESCE(foreign_language, 0)) DESC LIMIT 10", nativeQuery = true)
    List<ExamResultResponse> getTop10A01();

    @Query(value = "SELECT *, (COALESCE(math, 0) + COALESCE(chemistry, 0) + COALESCE(biology, 0))" +
            " FROM exam_result ORDER BY (COALESCE(math, 0) + COALESCE(chemistry, 0) + COALESCE(biology, 0)) DESC LIMIT 10", nativeQuery = true)
    List<ExamResultResponse> getTop10B();

    @Query(value = "SELECT *, (COALESCE(literature, 0) + COALESCE(history, 0) + COALESCE(geography, 0)) " +
            "FROM exam_result ORDER BY (COALESCE(literature, 0) + COALESCE(history, 0) + COALESCE(geography, 0)) DESC LIMIT 10", nativeQuery = true)
    List<ExamResultResponse> getTop10C();

    @Query(value = "SELECT *, (COALESCE(math, 0) + COALESCE(literature, 0) + COALESCE(foreign_language, 0))" +
            " FROM exam_result ORDER BY (COALESCE(math, 0) + COALESCE(literature, 0) + COALESCE(foreign_language, 0)) DESC LIMIT 10", nativeQuery = true)
    List<ExamResultResponse> getTop10D01();

    @Query(value = "SELECT *, (COALESCE(math, 0) + COALESCE(chemistry, 0) + COALESCE(foreign_language, 0))" +
            " FROM exam_result ORDER BY (COALESCE(math, 0) + COALESCE(chemistry, 0) + COALESCE(foreign_language, 0)) DESC LIMIT 10", nativeQuery = true)
    List<ExamResultResponse> getTop10D07();
}
