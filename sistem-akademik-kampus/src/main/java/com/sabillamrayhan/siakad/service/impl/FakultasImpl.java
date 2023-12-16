package com.sabillamrayhan.siakad.service.impl;

import com.sabillamrayhan.siakad.entity.FakultasEntity;
import com.sabillamrayhan.siakad.model.FakultasModel;
import com.sabillamrayhan.siakad.repository.FakultasRepo;
import com.sabillamrayhan.siakad.service.FakultasService;
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
public class FakultasImpl implements FakultasService {
    private final FakultasRepo fakultasRepo;
    @Override
    public List<FakultasModel> getAll() {
        return fakultasRepo.findAll()
                .stream()
                .map(FakultasModel::new).collect(Collectors.toList());
    }

    @Override
    public FakultasModel getById(String id) {
        if (id == null){
            return new FakultasModel();
        }
        return fakultasRepo.findById(id).map(FakultasModel::new).orElse(new FakultasModel());
    }

    @Override
    public Optional<FakultasModel> save(FakultasModel request) {
        if (request == null){
           return Optional.empty();
        }

        FakultasEntity entity = new FakultasEntity(request);
        try {
            fakultasRepo.save(entity);
            log.info("Save fakultas success");
            return Optional.of(new FakultasModel(entity));

        }catch (Exception e){
            log.error("Save fakultas failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasModel> update(FakultasModel request, String id) {
        FakultasEntity entity = fakultasRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy("SYSTEM");
        entity.setUpdateAt(LocalDateTime.now());
        entity.setUpdateBy("SYSTEM");
        try {
           fakultasRepo.save(entity);
           log.info("Update fakultas success");
           return Optional.of(new FakultasModel(entity));
        }catch (Exception e){
            log.error("Update fakultas failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasModel> delete(String id) {
        FakultasEntity entity = fakultasRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Fakultas with id : {} not found",id);
            return Optional.empty();
        }
        try {
            fakultasRepo.delete(entity);
            log.info("Delete fakultas success");
            return Optional.of(new FakultasModel(entity));
        }catch (Exception e){
            log.error("Delete fakultas failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }
}
