package com.sabillamrayhan.springjpa4.controller;

import com.sabillamrayhan.springjpa4.model.CategoryModel;
import com.sabillamrayhan.springjpa4.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/category/index");
        // get data from service
        List<CategoryModel> data = categoryService.gets();
        // send data to view
        view.addObject("dataList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView addCategory(){
        ModelAndView view = new ModelAndView("pages/category/add");
        return view;
    }

    @GetMapping("/add-modal")
    public ModelAndView addModel(){
        ModelAndView view = new ModelAndView("pages/category/_addPartial");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView saveCategory(@ModelAttribute CategoryModel request){
        // call save from service
        categoryService.save(request);
        // redirect to index
        return new ModelAndView("redirect:/category");
    }

    // edit method get
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("pages/category/edit");
        CategoryModel data = categoryService.getById(id).orElse(null);
        if(data == null){
            return new ModelAndView("redirect:/category");
        }

        // data send to view
        view.addObject("data", data);
        return view;
    }

    // update data
    @PostMapping("/update")
    public ModelAndView updateCategory(@ModelAttribute CategoryModel request){
        // call save from service
        categoryService.update(request, request.getId());
        // redirect to index
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") long id){
        ModelAndView view = new ModelAndView("pages/category/detail");
        // get data from service
        CategoryModel data = categoryService.getById(id).orElse(null);
        if(data == null){
            return new ModelAndView("redirect:/category");
        }

        // send data to view
        view.addObject("data",data);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView getDelete(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("pages/category/delete");

        CategoryModel data = categoryService.getById(id).orElse(null);
        if (data == null) {
            return new ModelAndView("redirect:/category");
        }

        view.addObject("data",data);
        return view;
    }

    @PostMapping("/delete-save")
    public String delete(@ModelAttribute CategoryModel request){
        categoryService.delete(request.getId());
        return "redirect:/category";
    }
}
