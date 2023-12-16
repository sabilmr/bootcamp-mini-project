package com.sabillamrayhan.siakad.controller;

import com.sabillamrayhan.siakad.model.FakultasModel;
import com.sabillamrayhan.siakad.model.JurusanModel;
import com.sabillamrayhan.siakad.service.FakultasService;
import com.sabillamrayhan.siakad.service.JurusanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/jurusan")
@RequiredArgsConstructor
public class JurusanController {
    private final JurusanService jurusanService;
    private final FakultasService fakultasService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/jurusan/index");
        List<JurusanModel> data = jurusanService.getAll();

        view.addObject("dataList",data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/jurusan/add");
        //get data fakultas
        List<FakultasModel> dataFakultas = fakultasService.getAll();

        //data kirim ke view
        view.addObject("dataFakultas", dataFakultas);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute JurusanModel request){
        if (request == null){
            return new ModelAndView("redirect:/jurusan/add");
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/jurusan/add");
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/jurusan/add");
        }

        jurusanService.save(request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/jurusan/edit");
        JurusanModel model = jurusanService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/jurusan");
        }

        List<FakultasModel> fakultas = fakultasService.getAll();

        view.addObject("dataFakultas", fakultas);

        view.addObject("jurusan",model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute JurusanModel request){
        if (request == null){
            return new ModelAndView("redirect:/jurusan/edit/"+ request.getId());
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/jurusan/edit/"+ request.getId());
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/jurusan/edit/"+ request.getId());
        }
        jurusanService.update(request, request.getId());
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        jurusanService.delete(id);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/jurusan/detail");
        JurusanModel model = jurusanService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/jurusan");
        }
        view.addObject("jurusan", model);
        return view;
    }
}
