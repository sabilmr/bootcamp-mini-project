package com.sabillamrayhan.siakad.service;

import com.sabillamrayhan.siakad.model.GedungModel;

import java.util.List;
import java.util.Optional;

public interface GedungService {
    List<GedungModel> getAll();
    GedungModel getById(String id);
    Optional<GedungModel> save(GedungModel request);
    Optional<GedungModel> update(GedungModel request, String id);
    Optional<GedungModel> delete(String id);
}
