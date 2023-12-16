package com.sabillamrayhan.siakad.service;

import com.sabillamrayhan.siakad.model.DosenModel;

import java.util.List;
import java.util.Optional;

public interface DosenService {
    List<DosenModel> getAll();
    DosenModel getById(String id);
    Optional<DosenModel> save(DosenModel request);
    Optional<DosenModel> update(DosenModel request, String id);
    Optional<DosenModel> delete(String id);
}
