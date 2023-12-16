package com.sabillamrayhan.siakad.service.impl;

import com.sabillamrayhan.siakad.entity.MataKuliahEntity;
import com.sabillamrayhan.siakad.model.MataKuliahModel;
import com.sabillamrayhan.siakad.repository.MataKuliahRepo;
import com.sabillamrayhan.siakad.service.MataKuliahService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MataKuliahImpl implements MataKuliahService {
    private final MataKuliahRepo mataKuliahRepo;

    @Override
    public List<MataKuliahModel> getAll() {
        return mataKuliahRepo.findAll()
                .stream()
                .map(MataKuliahModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public MataKuliahModel getById(String id) {
        return mataKuliahRepo.findById(id)
                .map(MataKuliahModel::new)
                .orElse(null);
    }

    @Override
    public Optional<MataKuliahModel> save(MataKuliahModel request) {
        if (request == null){
            return Optional.empty();
        }

        MataKuliahEntity entity = new MataKuliahEntity(request);
        try {
            mataKuliahRepo.save(entity);
            log.info("Save mata kuliah success");
            return Optional.of(new MataKuliahModel(entity));
        }catch (Exception e){
            log.error("Save mata kuliah failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<MataKuliahModel> update(MataKuliahModel request, String id) {
        MataKuliahEntity entity = mataKuliahRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request,entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy("SYSTEM");
        entity.setUpdateAt(LocalDateTime.now());
        entity.setUpdateBy("SYSTEM");
        try {
            mataKuliahRepo.save(entity);
            log.info("Update mata kuliah success");
            return Optional.of(new MataKuliahModel(entity));
        }catch (Exception e){
            log.error("Update mata kuliah failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<MataKuliahModel> delete(String id) {
        MataKuliahEntity entity = mataKuliahRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        try {
            mataKuliahRepo.delete(entity);
            log.info("Delete mata kuliah success");
            return Optional.of(new MataKuliahModel(entity));
        }catch (Exception e){
            log.error("Delete mata kuliah failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }
}
