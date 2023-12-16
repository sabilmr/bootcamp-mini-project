package com.bootcamp.northwind.controller;

import com.bootcamp.northwind.model.response.SupplierResponse;
import com.bootcamp.northwind.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/supplier/index");
        List<SupplierResponse> request = supplierService.getAll();

        view.addObject("dataSupplier", request);
        return view;
    }

    @GetMapping("/add-modal")
    public ModelAndView addModal(){
        ModelAndView view = new ModelAndView("pages/supplier/add");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute SupplierResponse request) {
        if (request == null){
            return new ModelAndView("redirect:/supplier/add");
        }

        supplierService.save(request);
        return new ModelAndView("redirect:/supplier");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/supplier/edit");
        SupplierResponse response = supplierService.getById(id).orElse(null);
        if (response == null){
            return new ModelAndView("redirect:/supplier");
        }
        view.addObject("editSupplier", response);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute SupplierResponse response){
        supplierService.update(response, response.getId());
        return new ModelAndView("redirect:/supplier");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/supplier/delete");
        SupplierResponse response = supplierService.getById(id).orElse(null);
        if (response == null){
            return new ModelAndView("redirect:/supplier");
        }

        view.addObject("supplier", response);
        return view;
    }

    @PostMapping("/delete-save")
    public String delete(@ModelAttribute SupplierResponse response){
        supplierService.delete(response.getId());
        return "redirect:/supplier";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("pages/supplier/detail");
        SupplierResponse response = supplierService.getById(id).orElse(null);
        if (response == null) {
            return new ModelAndView("redirect:/supplier");
        }
        view.addObject("detailSupplier", response);
        return view;
    }
}
