package com.sabillamrayhan.siakad.controller;

import com.sabillamrayhan.siakad.model.GedungModel;
import com.sabillamrayhan.siakad.model.RuangModel;
import com.sabillamrayhan.siakad.service.GedungService;
import com.sabillamrayhan.siakad.service.RuangService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ruang")
@RequiredArgsConstructor
public class RuangController {
    private final RuangService ruangService;
    private final GedungService gedungService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/ruang/index");
        List<RuangModel> models = ruangService.getAll();

        view.addObject("dataList", models);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/ruang/add");

        List<GedungModel> gedungModel = gedungService.getAll();

        view.addObject("dataGedung", gedungModel);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute RuangModel request){
        if (request == null){
            return new ModelAndView("redirect:/ruang/add");
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/ruang/add");
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/ruang/add");
        }

        ruangService.save(request);
        return new ModelAndView("redirect:/ruang");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/ruang/edit");
        RuangModel model = ruangService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/ruang");
        }

        List<GedungModel> gedungModels = gedungService.getAll();

        view.addObject("dataGedung", gedungModels);

        view.addObject("ruang", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute RuangModel request){
        if (request == null){
            return new ModelAndView("redirect:/ruang/edit"+ request.getId());
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/ruang/edit"+ request.getId());
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/ruang/edit"+ request.getId());
        }
        ruangService.save(request);
        return new ModelAndView("redirect:/ruang");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ruangService.delete(id);
        return new ModelAndView("redirect:/ruang");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/ruang/detail");
        RuangModel model = ruangService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/ruang");
        }
        view.addObject("ruang", model);
        return view;
    }
}
