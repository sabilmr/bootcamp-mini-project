package com.bootcamp.northwind.controller;

import com.bootcamp.northwind.model.response.CategoryResponse;
import com.bootcamp.northwind.model.response.ProductResponse;
import com.bootcamp.northwind.model.response.SupplierResponse;
import com.bootcamp.northwind.service.CategoryService;
import com.bootcamp.northwind.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final SupplierService supplierService;
    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/category/index");
        List<CategoryResponse> category = categoryService.getAll();

        view.addObject("listCategory", category);
        return view;
    }

    @GetMapping("/add-modal")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/category/add");
        List<SupplierResponse> supplierResponses = supplierService.getAll();

        view.addObject("supplier", supplierResponses );
        return view;
    }

    @GetMapping("/add-product/{index}")
    public ModelAndView addCategory(@PathVariable("index") int index){
        ModelAndView view = new ModelAndView("pages/category/_product");
        view.addObject("index", index);

        List<SupplierResponse> supplier = supplierService.getAll();
        view.addObject("supplier", supplier);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CategoryResponse response){
        categoryService.save(response);
        return new ModelAndView ("redirect:/category");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/category/edit");
        CategoryResponse response = categoryService.getById(id).orElse(null);
        if (response == null){
            return new ModelAndView("redirect:/category");
        }

        List<SupplierResponse> supplier = supplierService.getAll();
        view.addObject("supplier", supplier);

        view.addObject("category", response);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute CategoryResponse response){
        categoryService.update(response, response.getId());
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/category/delete");
        CategoryResponse data = categoryService.getById(id).orElse(null);
        if (data == null) {
            return new ModelAndView("redirect:/category");
        }

        List<SupplierResponse> supplier = supplierService.getAll();
        view.addObject("supplier", supplier);

        view.addObject("data",data);
        return view;
    }



    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/category/detail");
        CategoryResponse response = categoryService.getById(id).orElse(null);
        if (response == null){
            return new ModelAndView("redirect:/category");
        }

        List<SupplierResponse> supplier = supplierService.getAll();
        view.addObject("suplier", supplier);

        view.addObject("category", response);
        return view;
    }

    @PostMapping("/delete-save")
    public String delete(@ModelAttribute CategoryResponse response){
        categoryService.delete(response.getId());
        return "redirect:/category";
    }
}
