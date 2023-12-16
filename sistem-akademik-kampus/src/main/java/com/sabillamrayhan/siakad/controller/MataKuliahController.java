package com.sabillamrayhan.siakad.controller;

import com.sabillamrayhan.siakad.model.MataKuliahModel;
import com.sabillamrayhan.siakad.service.MataKuliahService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/matakuliah")
@RequiredArgsConstructor
public class MataKuliahController {
    private final MataKuliahService mataKuliahService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/matakuliah/index");
        List<MataKuliahModel> models = mataKuliahService.getAll();

        view.addObject("dataList", models);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/matakuliah/add");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MataKuliahModel request){
        if (request == null){
            return new ModelAndView("redirect:/matakuliah/add");
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/matakuliah/add");
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/matakuliah/add");
        }

        mataKuliahService.save(request);
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/matakuliah/edit");
        MataKuliahModel model = mataKuliahService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/matakuliah");
        }
        view.addObject("mataKuliah",model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MataKuliahModel request){
        if (request == null){
            return new ModelAndView("redirect:/matakuliah/edit"+ request.getId());
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/matakuliah/edit"+ request.getId());
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/matakuliah/edit"+ request.getId());
        }
        mataKuliahService.update(request, request.getId());
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        mataKuliahService.delete(id);
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/matakuliah/detail");
        MataKuliahModel model = mataKuliahService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/matakuliah");
        }
        view.addObject("mataKuliah", model);
        return view;
    }
}
