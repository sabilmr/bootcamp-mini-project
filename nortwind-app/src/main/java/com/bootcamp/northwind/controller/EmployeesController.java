package com.bootcamp.northwind.controller;

import com.bootcamp.northwind.model.response.EmployeesResponse;
import com.bootcamp.northwind.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeesService employeesService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/employees/index");
        List<EmployeesResponse> employees = employeesService.getAll();

        view.addObject("employeesList", employees);
        return view;
    }

    @GetMapping("/add-modal")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/employees/add");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute EmployeesResponse response){
        employeesService.save(response);
        return new ModelAndView("redirect:/employees");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/employees/edit");
        EmployeesResponse response = employeesService.getById(id).orElse(null);
        if (response == null){
            return new ModelAndView("redirect:/employees");
        }

        view.addObject("employees", response);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute EmployeesResponse response){
        employeesService.update(response, response.getId());
        return new ModelAndView("redirect:/employees");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/employees/delete");
        EmployeesResponse response = employeesService.getById(id).orElse(null);
        if (response == null){
            return new ModelAndView("redirect:/employees");
        }

        view.addObject("employees", response);
        return view;

    }

    @PostMapping("/delete-save")
    public ModelAndView delete(@ModelAttribute EmployeesResponse response){
        employeesService.delete(response.getId());
        return new ModelAndView("redirect:/employees");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/employees/detail");
        EmployeesResponse employeesResponse = employeesService.getById(id).orElse(null);
        if (employeesResponse == null){
            return new ModelAndView("redirect:/employees");
        }

        view.addObject("employees", employeesResponse);
        return view;
    }
}
