package com.sabillamrayhan.siakad.service.impl;

import com.sabillamrayhan.siakad.entity.KelasEntity;
import com.sabillamrayhan.siakad.entity.RuangEntity;
import com.sabillamrayhan.siakad.model.KelasModel;
import com.sabillamrayhan.siakad.repository.KelasRepo;
import com.sabillamrayhan.siakad.service.KelasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class KelasImpl implements KelasService {
    private final KelasRepo kelasRepo;

    @Override
    public List<KelasModel> getAll() {
        return kelasRepo.findAll()
                .stream()
                .map(KelasModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public KelasModel getById(String id) {
        return kelasRepo.findById(id)
                .map(KelasModel::new)
                .orElse(null);
    }

    @Override
    public Optional<KelasModel> save(KelasModel request) {
        if (request == null){
            return Optional.empty();
        }

        KelasEntity entity = new KelasEntity(request);
        try {
            kelasRepo.save(entity);
            log.info("Save kelas success");
            return Optional.of(new KelasModel(entity));
        }catch (Exception e){
            log.error("Save kelas failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasModel> update(KelasModel request, String id) {
        KelasEntity entity = kelasRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request,entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy("SYSTEM");
        entity.setUpdateAt(LocalDateTime.now());
        entity.setUpdateBy("SYSTEM");
        try {
            kelasRepo.save(entity);
            log.info("Update kelas success");
            return Optional.of(new KelasModel(entity));
        }catch (Exception e){
            log.error("Update kelas failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelasModel> delete(String id) {
        KelasEntity entity = kelasRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        try {
            kelasRepo.delete(entity);
            log.info("Delete kelas success");
            return Optional.of(new KelasModel(entity));
        }catch (Exception e){
            log.error("Delete kelas failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }
}
