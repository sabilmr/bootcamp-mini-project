package com.sabillamrayhan.siakad.service;

import com.sabillamrayhan.siakad.model.FakultasModel;

import java.util.List;
import java.util.Optional;

public interface FakultasService {
    List<FakultasModel> getAll();
    FakultasModel getById(String id);
    Optional<FakultasModel> save(FakultasModel request);
    Optional<FakultasModel> update(FakultasModel request, String id);
    Optional<FakultasModel> delete(String id);

}
