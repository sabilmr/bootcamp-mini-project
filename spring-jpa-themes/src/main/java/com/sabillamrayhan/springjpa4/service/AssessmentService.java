package com.sabillamrayhan.springjpa4.service;

import com.sabillamrayhan.springjpa4.model.AssessmentModel;

import java.util.List;
import java.util.Optional;

public interface AssessmentService {
    List<AssessmentModel> getAll();
    Optional<AssessmentModel> getById(Long id);
    Optional<AssessmentModel> save(AssessmentModel request);
}
