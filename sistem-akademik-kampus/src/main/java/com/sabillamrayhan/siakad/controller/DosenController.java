package com.sabillamrayhan.siakad.controller;

import com.sabillamrayhan.siakad.model.DosenModel;
import com.sabillamrayhan.siakad.service.DosenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/dosen")
@RequiredArgsConstructor
public class DosenController {
    private final DosenService dosenService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/dosen/index");
        List<DosenModel> data = dosenService.getAll();

        view.addObject("dataList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/dosen/add");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute DosenModel request){
        if (request == null){
            return new ModelAndView("redirect:/dosen/add");
        }

        if (request.getNip().isEmpty()){
            return new ModelAndView("redirect:/dosen/add");
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/dosen/add");
        }

        if (request.getJk().isEmpty()){
            return new ModelAndView("redirect:/dosen/add");
        }

        if (request.getAlamat().isEmpty()){
            return new ModelAndView("redirect:/dosen/add");
        }

        if (request.getGelar().isEmpty()){
            return new ModelAndView("redirect:/dosen/add");
        }

        dosenService.save(request);
        return new ModelAndView("redirect:/dosen");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/dosen/edit");
        DosenModel model = dosenService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/dosen");
        }

        view.addObject("dosen", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute DosenModel request){
        if (request == null){
            return new ModelAndView("redirect:/dosen/edit"+ request.getId());
        }

        if (request.getNip().isEmpty()){
            return new ModelAndView("redirect:/dosen/edit"+ request.getId());
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/dosen/edit"+ request.getId());
        }

        if (request.getJk().isEmpty()){
            return new ModelAndView("redirect:/dosen/edit"+ request.getId());
        }

        if (request.getAlamat().isEmpty()){
            return new ModelAndView("redirect:/dosen/edit"+ request.getId());
        }

        if (request.getGelar().isEmpty()){
            return new ModelAndView("redirect:/dosen/edit"+ request.getId());
        }
        this.dosenService.update(request, request.getId());
        return new ModelAndView("redirect:/dosen");
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        dosenService.delete(id);
        return new ModelAndView("redirect:/dosen");
    }

    @GetMapping("detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/dosen/detail");
        DosenModel model = dosenService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/dosen");
        }
        view.addObject("dosen", model);
        return view;
    }
}
