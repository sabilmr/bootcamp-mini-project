package com.sabillamrayhan.siakad.service.impl;

import com.sabillamrayhan.siakad.entity.FakultasEntity;
import com.sabillamrayhan.siakad.entity.JurusanEntity;
import com.sabillamrayhan.siakad.model.JurusanModel;
import com.sabillamrayhan.siakad.repository.FakultasRepo;
import com.sabillamrayhan.siakad.repository.JurusanRepo;
import com.sabillamrayhan.siakad.service.JurusanService;
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
public class JurusanImpl implements JurusanService {
    private final JurusanRepo jurusanRepo;
    private final FakultasRepo fakultasRepo;

    @Override
    public List<JurusanModel> getAll() {
        return jurusanRepo.findAll()
                .stream()
                .map(JurusanModel::new).collect(Collectors.toList());
    }

    @Override
    public JurusanModel getById(String id) {
        if (id == null){
            return new JurusanModel();
        }
        return jurusanRepo.findById(id).map(JurusanModel::new).orElse(null);
    }

    @Override
    public Optional<JurusanModel> save(JurusanModel request) {
        if (request == null){
            return Optional.empty();
        }

        FakultasEntity fakultas = fakultasRepo.findById(request.getFakultasId()).orElse(null);
        if (fakultas == null){
            return Optional.empty();
        }

        JurusanEntity entity = new JurusanEntity(request, fakultas);
        try {
            jurusanRepo.save(entity);
            log.info("save jurusan success");
            return Optional.of(new JurusanModel(entity));
        }catch (Exception e){
            log.error("Save jurusan failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<JurusanModel> update(JurusanModel request, String id) {
        JurusanEntity entity = jurusanRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request,entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy("SYSTEM");
        entity.setUpdateAt(LocalDateTime.now());
        entity.setUpdateBy("SYSTEM");
        try {
            jurusanRepo.save(entity);
            log.info("Update jurusan success");
            return Optional.of(new JurusanModel(entity));
        }catch (Exception e){
            log.error("Update jurusan failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<JurusanModel> delete(String id) {
        JurusanEntity entity = jurusanRepo.findById(id).orElse(null);
        if (entity == null){
            log.warn("Jurusan with id: {} not found",id);
            return Optional.empty();
        }
        try {
            jurusanRepo.delete(entity);
            log.info("Delete jurusan success");
            return Optional.of(new JurusanModel(entity));
        }catch (Exception e){
            log.error("Delete jurusan failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }
}
