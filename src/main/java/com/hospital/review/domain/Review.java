package com.hospital.review.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String patientName;


    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
