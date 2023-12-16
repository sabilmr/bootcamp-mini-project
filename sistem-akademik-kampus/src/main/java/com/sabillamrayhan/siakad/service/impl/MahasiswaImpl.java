package com.sabillamrayhan.siakad.service.impl;

import com.sabillamrayhan.siakad.entity.MahasiswaEntity;
import com.sabillamrayhan.siakad.model.MahasiswaModel;
import com.sabillamrayhan.siakad.repository.MahasiswaRepo;
import com.sabillamrayhan.siakad.service.MahasiswaService;
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
public class MahasiswaImpl implements MahasiswaService {
    private final MahasiswaRepo mahasiswaRepo;

    @Override
    public List<MahasiswaModel> getAll() {
        return mahasiswaRepo.findAll()
                .stream()
                .map(MahasiswaModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public MahasiswaModel getById(String id) {
        return mahasiswaRepo.findById(id)
                .map(MahasiswaModel::new)
                .orElse(null);
    }

    @Override
    public Optional<MahasiswaModel> save(MahasiswaModel request) {
        if (request == null){
            return Optional.empty();
        }
        MahasiswaEntity entity = new MahasiswaEntity(request);
        try {
            mahasiswaRepo.save(entity);
            log.info("Save mahasiswa success");
            return Optional.of(new MahasiswaModel(entity));
        }catch (Exception e){
            log.error("Save mahasiswa failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<MahasiswaModel> update(MahasiswaModel request, String id) {
        MahasiswaEntity entity = mahasiswaRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        BeanUtils.copyProperties(request, entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy("SYSTEM");
        entity.setUpdateAt(LocalDateTime.now());
        entity.setUpdateBy("SYSTEM");
        try {
            mahasiswaRepo.save(entity);
            log.info("Update mahasiswa success");
            return Optional.of(new MahasiswaModel(entity));
        }catch (Exception e){
            log.error("Update mahasiswa failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<MahasiswaModel> delete(String id) {
        MahasiswaEntity entity = mahasiswaRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }
        try {
            mahasiswaRepo.delete(entity);
            log.info("Delete mahasiswa success");
            return Optional.of(new MahasiswaModel(entity));
        }catch (Exception e){
            log.error("Delete mahasiswa failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }
}
