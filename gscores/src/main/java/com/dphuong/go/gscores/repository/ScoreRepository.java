package com.dphuong.go.gscores.repository;

import com.dphuong.go.gscores.dto.ChartDataResponse;
import com.dphuong.go.gscores.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByStudentSbd(String sbd);

    @Query(value = """
    WITH Top10Sbd AS (
        SELECT\s
            s2.sbd  AS sbd,\s
            SUM(s2.score) AS total_score
        FROM scores s2
        JOIN group_subjects gs ON s2.subject_id = gs.subject_id
        JOIN exam_groups eg ON gs.group_id = eg.id
        WHERE eg.group_name = :groupName
        GROUP BY s2.sbd\s
        HAVING COUNT(s2.subject_id) = 3
        ORDER BY total_score DESC
        LIMIT 10
    )
    SELECT\s
        t.sbd,
        :groupName AS group_name,
        t.total_score AS total,
        json_agg(
            json_build_object(
                'id', sub.id, \s
                'name', sub.name,\s
                'score', sc.score
            )
        ) AS subjects
    FROM Top10Sbd t
    JOIN scores sc ON t.sbd = sc.sbd\s
    JOIN subjects sub ON sc.subject_id = sub.id
    JOIN group_subjects gs2 ON sub.id = gs2.subject_id
    JOIN exam_groups eg2 ON gs2.group_id = eg2.id
    WHERE eg2.group_name = :groupName
    GROUP BY t.sbd, t.total_score
    ORDER BY t.total_score DESC;
    """, nativeQuery = true)
    List<Object[]> findTop10ByGroupName(@Param("groupName") String groupName);


    @Query(value = """
            SELECT\s
    sub.id AS subject_id,
    sub.name AS subject_name,
    stats.excellent,
    stats.good,
    stats.average,
    stats.weak,
    stats.total_students
FROM subjects sub
LEFT JOIN (
    SELECT\s
        subject_id,
        COUNT(CASE WHEN score >= 8 THEN 1 END) AS excellent,
        COUNT(CASE WHEN score >= 6 AND score < 8 THEN 1 END) AS good,
        COUNT(CASE WHEN score >= 4 AND score < 6 THEN 1 END) AS average,
        COUNT(CASE WHEN score < 4 THEN 1 END) AS weak,
        COUNT(*) AS total_students
    FROM scores
    GROUP BY subject_id
) stats ON sub.id = stats.subject_id
ORDER BY sub.id;
""", nativeQuery = true)
    List<ChartDataResponse> getChartData();

}
