package com.sabillamrayhan.siakad.controller;

import com.sabillamrayhan.siakad.model.KelasDetailModel;
import com.sabillamrayhan.siakad.model.KelasModel;
import com.sabillamrayhan.siakad.model.MahasiswaModel;
import com.sabillamrayhan.siakad.service.KelasDetailService;
import com.sabillamrayhan.siakad.service.KelasService;
import com.sabillamrayhan.siakad.service.MahasiswaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/kelasdetail")
@RequiredArgsConstructor
public class KelasDetailController {
    private final KelasDetailService kelasDetailService;
    private final MahasiswaService mahasiswaService;
    private final KelasService kelasService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/kelasdetail/index");
        List<KelasDetailModel> model = kelasDetailService.getAll();

        view.addObject("dataKelasDetail", model);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/kelasdetail/add");
        List<MahasiswaModel> model = mahasiswaService.getAll();
        List<KelasModel> kelasModel = kelasService.getAll();

        view.addObject("dataSiswa", model);
        view.addObject("dataKelas", kelasModel);

        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute KelasDetailModel request){
        if (request == null){
            return new ModelAndView("redirect:/kelasdetail/add");
        }

        if (request.getStatus().isEmpty()){
            return new ModelAndView("redirect:/kelasdetail/add");
        }

        kelasDetailService.save(request);
        return new ModelAndView("redirect:/kelasdetail");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/kelasdetail/edit");
        KelasDetailModel model = kelasDetailService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/kelasdetail");
        }
        List<MahasiswaModel> mahasiswa = mahasiswaService.getAll();
        List<KelasModel> kelas = kelasService.getAll();

        view.addObject("kelasDetail", model);
        view.addObject("dataSiswa", mahasiswa);
        view.addObject("dataKelas", kelas);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute KelasDetailModel request){
        if (request == null){
            return new ModelAndView("redirect:/kelasdetail/edit"+ request.getId());
        }

        if (request.getStatus().isEmpty()){
            return new ModelAndView("redirect:/kelasdetail/edit"+ request.getId());
        }

        kelasDetailService.update(request, request.getId());
        return new ModelAndView("redirect:/kelasdetail");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        kelasDetailService.delete(id);
        return new ModelAndView("redirect:/kelasdetail");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/kelasdetail/detail");
        KelasDetailModel model = kelasDetailService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/kelasdetail");
        }
        view.addObject("dataKelasDetail", model);
        return view;
    }
}
