package com.marulov.youcontribute.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "issues")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private String title;

    private String body;

    private String htmlUrl;
}