package com.sabillamrayhan.siakad.service;

import com.sabillamrayhan.siakad.model.RuangModel;

import java.util.List;
import java.util.Optional;

public interface RuangService {
    List<RuangModel> getAll();
    RuangModel getById(String id);
    Optional<RuangModel> save(RuangModel request);
    Optional<RuangModel> update(RuangModel request, String id);
    Optional<RuangModel> delete(String id);
}
