package com.sabillamrayhan.siakad.controller;

import com.sabillamrayhan.siakad.model.DosenModel;
import com.sabillamrayhan.siakad.model.KelasModel;
import com.sabillamrayhan.siakad.model.MataKuliahModel;
import com.sabillamrayhan.siakad.model.RuangModel;
import com.sabillamrayhan.siakad.service.DosenService;
import com.sabillamrayhan.siakad.service.KelasService;
import com.sabillamrayhan.siakad.service.MataKuliahService;
import com.sabillamrayhan.siakad.service.RuangService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/kelas")
@RequiredArgsConstructor
public class KelasController {
    private final KelasService kelasService;
    private final RuangService ruangService;
    private final DosenService dosenService;
    private final MataKuliahService mataKuliahService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/kelas/index");
        List<KelasModel> models = kelasService.getAll();

        view.addObject("dataList", models);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/kelas/add");
        List<RuangModel> ruangModels = ruangService.getAll();
        List<DosenModel> dosenModels = dosenService.getAll();
        List<MataKuliahModel> models = mataKuliahService.getAll();

        view.addObject("dataRuang", ruangModels);
        view.addObject("dataDosen", dosenModels);
        view.addObject("dataMataKuliah", models);

        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute KelasModel request){
        if (request == null){
            return new ModelAndView("redirect:/kelas/add");
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/kelas/add");
        }

        if (request.getNameHari().isEmpty()){
            return new ModelAndView("redirect:/kelas/add");
        }

        kelasService.save(request);
        return new ModelAndView("redirect:/kelas ");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/kelas/edit");
        KelasModel model = kelasService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/kelas");
        }

        List<RuangModel> ruang = ruangService.getAll();

        List<DosenModel> dosenModels = dosenService.getAll();

        List<MataKuliahModel> models = mataKuliahService.getAll();

        view.addObject("dataRuang", ruang);
        view.addObject("dataDosen", dosenModels);
        view.addObject("dataMataKuliah", models);

        view.addObject("kelas", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute KelasModel request){
        if (request == null){
            return new ModelAndView("redirect:/kelas/edit"+ request.getId());
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/kelas/edit"+ request.getId());
        }

        if (request.getNameHari().isEmpty()){
            return new ModelAndView("redirect:/kelas/edit"+ request.getId());
        }

        kelasService.update(request, request.getId());
        return new ModelAndView("redirect:/kelas");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        kelasService.delete(id);
        return new ModelAndView("redirect:/kelas");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/kelas/detail");
        KelasModel model = kelasService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/kelas");
        }
        view.addObject("kelas", model);
        return view;
    }
}
