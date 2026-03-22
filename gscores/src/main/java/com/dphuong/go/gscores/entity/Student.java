package com.dphuong.go.gscores.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {

    @Id
    @Column(length = 10)
    private String sbd;

    @Column(name = "foreign_language_code", length = 10)
    private String foreignLanguageCode;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Score> scores = new ArrayList<>();
}
