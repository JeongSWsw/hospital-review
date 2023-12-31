package com.hospital.review.service;

import com.hospital.review.domain.Hospital;
import com.hospital.review.domain.Review;
import com.hospital.review.domain.dto.ReviewCreateRequest;
import com.hospital.review.domain.dto.ReviewCreateResponse;
import com.hospital.review.repository.HospitalRepository;
import com.hospital.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    private final HospitalRepository hospitalRepository; // new Reivew() 안에 Hospital을 넣어야 하므로 dto의 hospitalId값으로 조회필요
    private final ReviewRepository reviewRepository;

    public ReviewService(HospitalRepository hospitalRepository, ReviewRepository reviewRepository) {
        this.hospitalRepository = hospitalRepository;
        this.reviewRepository = reviewRepository;
    }

    public ReviewCreateResponse createReview(ReviewCreateRequest dto){
        // Hospital 불러오기
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(dto.getHospitalId());
        // ReviewEntity 만들기
        Review review = Review.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .patientName(dto.getUserName())
                .hospital(hospitalOptional.get())
                .build();
        // 저장
        Review savedReview = reviewRepository.save(review);
        return ReviewCreateResponse.builder()
                .title(savedReview.getTitle())
                .content(savedReview.getContent())
                .userName(savedReview.getPatientName())
                .message("리뷰 등록이 성공했습니다.")
                .build();
    }
}
