package com.dphuong.go.gscores.repository;

import com.dphuong.go.gscores.entity.ExamGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamGroupRepository extends JpaRepository<ExamGroup, Long> {

    Optional<ExamGroup> findByGroupName(String groupName);


    boolean existsByGroupName(String groupName);
}
