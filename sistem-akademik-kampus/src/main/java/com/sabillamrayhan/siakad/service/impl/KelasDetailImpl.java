package com.sabillamrayhan.siakad.service.impl;

import com.sabillamrayhan.siakad.entity.KelasDetailEntity;
import com.sabillamrayhan.siakad.model.KelasDetailModel;
import com.sabillamrayhan.siakad.repository.KelasDetailRepo;
import com.sabillamrayhan.siakad.service.KelasDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class KelasDetailImpl implements KelasDetailService {
    private final KelasDetailRepo kelasDetailRepo;

    @Override
    public List<KelasDetailModel> getAll() {
        return kelasDetailRepo.findAll()
                .stream()
                .map(KelasDetailModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public KelasDetailModel getById(String id) {
        return kelasDetailRepo.findById(id)
                .map(KelasDetailModel::new)
                .orElse(null);
    }

    @Override
    public Optional<KelasDetailModel> save(KelasDetailModel request) {
        if (request == null){
            return Optional.empty();
        }

        KelasDetailEntity entity = new KelasDetailEntity(request);
        try {
            kelasDetailRepo.save(entity);
            log.info("Save kelas detail success");
            return Optional.of(new KelasDetailModel());
        }catch (Exception e){
            log.error("Save kelas detail failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasDetailModel> update(KelasDetailModel request, String id) {
        KelasDetailEntity entity = kelasDetailRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        BeanUtils.copyProperties(request, entity);
        try {
            kelasDetailRepo.save(entity);
            log.info("Update kelas detail success");
            return Optional.of(new KelasDetailModel());
        }catch (Exception e){
            log.error("Update kelas detail failed, error: {}",e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<KelasDetailModel> delete(String id) {
        KelasDetailEntity entity = kelasDetailRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        try {
            kelasDetailRepo.delete(entity);
            log.info("Update kelas detail success");
            return Optional.of(new KelasDetailModel());
        }catch (Exception e){
            log.error("Update kelas detail failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }
}
