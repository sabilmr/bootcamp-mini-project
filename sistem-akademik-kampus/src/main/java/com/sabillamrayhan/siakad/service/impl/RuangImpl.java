package com.sabillamrayhan.siakad.service.impl;

import com.sabillamrayhan.siakad.entity.RuangEntity;
import com.sabillamrayhan.siakad.model.RuangModel;
import com.sabillamrayhan.siakad.repository.RuangRepo;
import com.sabillamrayhan.siakad.service.RuangService;
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
public class RuangImpl implements RuangService {
    private final RuangRepo ruangRepo;
    @Override
    public List<RuangModel> getAll() {
        return ruangRepo.findAll()
                .stream()
                .map(RuangModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public RuangModel getById(String id) {
        return ruangRepo.findById(id)
                .map(RuangModel::new)
                .orElse(null);
    }

    @Override
    public Optional<RuangModel> save(RuangModel request) {
        if (request == null){
            return Optional.empty();
        }
        RuangEntity entity = new RuangEntity(request);
        try {
            ruangRepo.save(entity);
            log.info("Save ruang success");
            return Optional.of(new RuangModel(entity));
        }catch (Exception e){
            log.error("Save ruang failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<RuangModel> update(RuangModel request, String id) {
        RuangEntity entity = ruangRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy("SYSTEM");
        entity.setUpdateAt(LocalDateTime.now());
        entity.setUpdateBy("SYSTEM");
        try {
            ruangRepo.save(entity);
            log.info("Update ruang success");
            return Optional.of(new RuangModel(entity));
        }catch (Exception e){
            log.error("Update ruang failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<RuangModel> delete(String id) {
        RuangEntity entity = ruangRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        try {
            ruangRepo.delete(entity);
            log.info("Delete ruang success");
            return Optional.of(new RuangModel(entity));
        }catch (Exception e){
            log.error("Delete ruang failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }
}
