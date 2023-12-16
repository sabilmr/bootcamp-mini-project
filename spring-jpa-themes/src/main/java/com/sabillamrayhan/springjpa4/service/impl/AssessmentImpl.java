package com.sabillamrayhan.springjpa4.service.impl;

import com.sabillamrayhan.springjpa4.entity.AssessmentEntity;
import com.sabillamrayhan.springjpa4.model.AssessmentModel;
import com.sabillamrayhan.springjpa4.repository.AssessmentRepo;
import com.sabillamrayhan.springjpa4.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssessmentImpl implements AssessmentService {
    private final AssessmentRepo assessmentRepo;

    @Override
    public List<AssessmentModel> getAll() {
        List<AssessmentEntity> result = this.assessmentRepo.findAll();
        if(result.isEmpty()){
            return Collections.emptyList();
        }

        return result.stream()
                .map(AssessmentModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AssessmentModel> getById(Long id) {
        AssessmentEntity assessment = this.assessmentRepo.findById(id).orElse(null);
        if(assessment == null) {
            return Optional.empty();
        }

        AssessmentModel result = new AssessmentModel(assessment);
        return Optional.of(result);
    }


    @Override
    public Optional<AssessmentModel> save(AssessmentModel request) {
        return Optional.empty();
    }
}
