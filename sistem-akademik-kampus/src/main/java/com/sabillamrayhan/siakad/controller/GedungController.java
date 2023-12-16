package com.sabillamrayhan.siakad.controller;

import com.sabillamrayhan.siakad.model.GedungModel;
import com.sabillamrayhan.siakad.model.RuangModel;
import com.sabillamrayhan.siakad.service.GedungService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/gedung")
@RequiredArgsConstructor
public class GedungController {
    private final GedungService gedungService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/gedung/index");
        List<GedungModel> data = gedungService.getAll();

        view.addObject("dataList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/gedung/add");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute GedungModel request){
        if (request == null){
            return new ModelAndView("redirect:/gedung/add");
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/gedung/add");
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/gedung/add");
        }

        if (request.getJumlahLantai().isEmpty()){
            return new ModelAndView("redirect:/gedung/add");
        }

        gedungService.save(request);
        return new ModelAndView("redirect:/gedung");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/gedung/edit");
        GedungModel model = gedungService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/gedung");
        }
        view.addObject("gedung", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute GedungModel request){
        if (request == null){
            return new ModelAndView("redirect:/gedung/edit"+ request.getId());
        }

        if (request.getCode().isEmpty()){
            return new ModelAndView("redirect:/gedung/edit"+ request.getId());
        }

        if (request.getName().isEmpty()){
            return new ModelAndView("redirect:/gedung/edit"+ request.getId());
        }

        if (request.getJumlahLantai().isEmpty()){
            return new ModelAndView("redirect:/gedung/edit"+ request.getId());
        }

        gedungService.update(request, request.getId());
        return new ModelAndView("redirect:/gedung");
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        gedungService.delete(id);
        return new ModelAndView("redirect:/gedung");
    }

    @GetMapping("detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/gedung/detail");
        GedungModel model = gedungService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/gedung");
        }
        view.addObject("gedung",model);
        return view;
    }

}
