package com.sabillamrayhan.siakad.service;

import com.sabillamrayhan.siakad.model.MahasiswaModel;

import java.util.List;
import java.util.Optional;

public interface MahasiswaService {
    List<MahasiswaModel> getAll();
    MahasiswaModel getById(String id);
    Optional<MahasiswaModel> save(MahasiswaModel request);
    Optional<MahasiswaModel> update(MahasiswaModel request, String id);
    Optional<MahasiswaModel> delete(String id);
}
