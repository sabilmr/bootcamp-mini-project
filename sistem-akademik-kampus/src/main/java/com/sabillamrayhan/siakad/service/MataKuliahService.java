package com.sabillamrayhan.siakad.service;

import com.sabillamrayhan.siakad.model.MataKuliahModel;

import java.util.List;
import java.util.Optional;

public interface MataKuliahService {
    List<MataKuliahModel> getAll();
    MataKuliahModel getById(String id);
    Optional<MataKuliahModel> save(MataKuliahModel request);
    Optional<MataKuliahModel> update(MataKuliahModel request, String id);
    Optional<MataKuliahModel> delete(String id);
}
