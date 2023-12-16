package com.sabillamrayhan.siakad.service.impl;

import com.sabillamrayhan.siakad.entity.GedungEntity;
import com.sabillamrayhan.siakad.model.GedungModel;
import com.sabillamrayhan.siakad.repository.GedungRepo;
import com.sabillamrayhan.siakad.service.GedungService;
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
public class GedungImpl implements GedungService {
    private final GedungRepo gedungRepo;

    @Override
    public List<GedungModel> getAll() {
        return gedungRepo.findAll()
                .stream()
                .map(GedungModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public GedungModel getById(String id) {
        if (id == null){
            return null;
        }
        return gedungRepo.findById(id)
                .map(GedungModel::new)
                .orElse(null);
    }

    @Override
    public Optional<GedungModel> save(GedungModel request) {
        if (request == null){
            return Optional.empty();
        }
        GedungEntity entity = new GedungEntity(request);
        try {
            gedungRepo.save(entity);
            log.info("Save gedung success");
            return Optional.of(new GedungModel(entity));
        }catch (Exception sabil){
            log.error("Save gedung failed, error: {}",sabil.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<GedungModel> update(GedungModel request, String id) {
        GedungEntity entity = gedungRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy("SYSTEM");
        entity.setUpdateAt(LocalDateTime.now());
        entity.setUpdateBy("SYSTEM");
        try {
            gedungRepo.save(entity);
            log.info("Update gedung success");
            return Optional.of(new GedungModel(entity));
        }catch (Exception e){
            log.error("Update gedung failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<GedungModel> delete(String id) {
        GedungEntity entity = gedungRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Gedung with id : {} not found",id);
            return Optional.empty();
        }
        try {
            gedungRepo.delete(entity);
            log.info("Delete gedung success");
            return Optional.of(new GedungModel(entity));
        }catch (Exception e){
            log.error("Delete gedung failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }
}
