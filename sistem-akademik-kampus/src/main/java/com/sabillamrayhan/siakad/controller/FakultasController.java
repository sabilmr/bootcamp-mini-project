package com.sabillamrayhan.siakad.controller;

import com.sabillamrayhan.siakad.model.FakultasModel;
import com.sabillamrayhan.siakad.service.FakultasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/fakultas")
@RequiredArgsConstructor
public class FakultasController {
    private final FakultasService fakultasService;

    @GetMapping
    public ModelAndView index(){
         ModelAndView view = new ModelAndView("pages/fakultas/index");
        List<FakultasModel> data = fakultasService.getAll();

        view.addObject("dataList",data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/fakultas/add");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute FakultasModel request){
        if (request == null){
            return new ModelAndView("redirect:/fakultas/add");
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/fakultas/add");
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/fakultas/add");
        }

        fakultasService.save(request);
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/fakultas/edit");
        FakultasModel model = fakultasService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/fakultas");
        }
        view.addObject("fakultas", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute FakultasModel request){
        if (request == null){
            return new ModelAndView("redirect:/fakultas/edit/"+ request.getId());
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/fakultas/edit/"+ request.getId());
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/fakultas/edit/"+ request.getId());
        }

        fakultasService.update(request, request.getId());
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        fakultasService.delete(id);
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/fakultas/detail");
        FakultasModel model = fakultasService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/fakultas");
        }
        view.addObject("fakultas", model);
        return view;
    }
}
