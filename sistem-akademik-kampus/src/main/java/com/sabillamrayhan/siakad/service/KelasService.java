package com.sabillamrayhan.siakad.service;

import com.sabillamrayhan.siakad.model.KelasModel;

import java.util.List;
import java.util.Optional;

public interface KelasService {
    List<KelasModel> getAll();
    KelasModel getById(String id);
    Optional<KelasModel> save(KelasModel request);
    Optional<KelasModel> update(KelasModel request, String id);
    Optional<KelasModel> delete(String id);
}
