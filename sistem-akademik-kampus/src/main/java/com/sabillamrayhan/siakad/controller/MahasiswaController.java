package com.sabillamrayhan.siakad.controller;

import com.sabillamrayhan.siakad.model.FakultasModel;
import com.sabillamrayhan.siakad.model.JurusanModel;
import com.sabillamrayhan.siakad.model.MahasiswaModel;
import com.sabillamrayhan.siakad.repository.JurusanRepo;
import com.sabillamrayhan.siakad.service.FakultasService;
import com.sabillamrayhan.siakad.service.JurusanService;
import com.sabillamrayhan.siakad.service.MahasiswaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mahasiswa")
@RequiredArgsConstructor
public class MahasiswaController {
    private final MahasiswaService mahasiswaService;
    private final JurusanService jurusanService;
    private final FakultasService fakultasService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/mahasiswa/index");
        List<MahasiswaModel> mahasiswaModel = mahasiswaService.getAll();
        view.addObject("dataList", mahasiswaModel);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/mahasiswa/add");
        List<JurusanModel> jurusanModels = jurusanService.getAll();
        List<FakultasModel> fakultas = fakultasService.getAll();

        view.addObject("dataFakultas", fakultas);
        view.addObject("dataJurusan", jurusanModels);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MahasiswaModel request){
        if (request == null){
            return new ModelAndView("redirect:/mahasiswa/add");
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/mahasiswa/add");
        }

        if (request.getGender().isEmpty()){
            return new ModelAndView("redirect:/mahasiswa/add");
        }

        mahasiswaService.save(request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/mahasiswa/edit");
        MahasiswaModel model = mahasiswaService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/mahasiswa");
        }
        List<JurusanModel> jurusanModels = jurusanService.getAll();
        List<FakultasModel> fakultas = fakultasService.getAll();

        view.addObject("dataFakultas", fakultas);
        view.addObject("dataJurusan", jurusanModels);

        view.addObject("mahasiswa", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MahasiswaModel request){
        if (request == null){
            return new ModelAndView("redirect:/mahasiswa/edit/"+ request.getId());
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/mahasiswa/edit/"+ request.getId());
        }

        if (request.getGender().isEmpty()){
            return new ModelAndView("redirect:/mahasiswa/edit/"+ request.getId());
        }
        mahasiswaService.update(request, request.getId());
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        mahasiswaService.delete(id);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/mahasiswa/detail");
        MahasiswaModel model = mahasiswaService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/mahasiswa");
        }
        view.addObject("mahasiswa", model);
        return view;
    }
}
