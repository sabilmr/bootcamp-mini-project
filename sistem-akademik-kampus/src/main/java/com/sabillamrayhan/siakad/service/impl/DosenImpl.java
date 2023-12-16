package com.sabillamrayhan.siakad.service.impl;

import com.sabillamrayhan.siakad.entity.DosenEntity;
import com.sabillamrayhan.siakad.model.DosenModel;
import com.sabillamrayhan.siakad.repository.DosenRepo;
import com.sabillamrayhan.siakad.service.DosenService;
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
public class DosenImpl implements DosenService {
    private final DosenRepo dosenRepo;
    @Override
    public List<DosenModel> getAll() {
       return this.dosenRepo.findAll()
               .stream()
               .map(DosenModel::new).collect(Collectors.toList());
    }

    @Override
    public DosenModel getById(String id) {
        if (id == null) {
            return new DosenModel();
        }
        return this.dosenRepo.findById(id).map(DosenModel::new).orElse(new DosenModel());
    }

    @Override
    public  Optional<DosenModel> save(DosenModel request) {
        if (request == null){
            return Optional.empty();
        }

        DosenEntity entity = new DosenEntity(request);
        try {
            dosenRepo.save(entity);
            log.info("Save dosen success");
            return Optional.of(new DosenModel(entity));

        }catch (Exception e){
            log.error("SAve dosen failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<DosenModel> update(DosenModel request, String id) {
        DosenEntity entity = this.dosenRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request,entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy("SYSTEM");
        entity.setUpdateAt(LocalDateTime.now());
        entity.setUpdateBy("SYSTEM");
        try {
            this.dosenRepo.save(entity);
            log.info("Update dosen success");
            return Optional.of(new DosenModel(entity));
        }catch (Exception e){
            log.error("Update dosen failed, error: {}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<DosenModel> delete(String id) {
       DosenEntity entity = this.dosenRepo.findById(id).orElse(null);
       if (entity == null){
           log.warn("Dosen with id : {} not found",id);
           return Optional.empty();
       }
       try {
           dosenRepo.delete(entity);
           log.info("Delete dosen success");
           return Optional.of(new DosenModel());
       }catch (Exception e){
           log.error("Delete dosen failed, error: {}",e.getMessage());
           return Optional.empty();
       }
    }
}
