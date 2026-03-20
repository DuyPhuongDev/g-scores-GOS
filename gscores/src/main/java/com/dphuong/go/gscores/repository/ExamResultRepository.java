package com.dphuong.go.gscores.repository;

import com.dphuong.go.gscores.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, String> {

    @Query(
            value = "select er, (COALESCE(math, 0) + COALESCE(physics, 0) + COALESCE(chemistry, 0)) AS total " +
                    "from exam_result er " +
                    "order by (COALESCE(math, 0) + COALESCE(physics, 0) + COALESCE(chemistry, 0)) desc " +
                    "limit 10",
            nativeQuery = true
    )
    List<ExamResult> getTop10GroupA();
}
