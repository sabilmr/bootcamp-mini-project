package com.sabillamrayhan.siakad.service;


import com.sabillamrayhan.siakad.model.JurusanModel;

import java.util.List;
import java.util.Optional;

public interface JurusanService{
    List<JurusanModel> getAll();
    JurusanModel getById(String id);
    Optional<JurusanModel> save(JurusanModel request);
    Optional<JurusanModel> update(JurusanModel request, String id);
    Optional<JurusanModel> delete(String id);

}
