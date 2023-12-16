package com.bootcamp.northwind.controller;

import com.bootcamp.northwind.model.response.CustomerResponse;
import com.bootcamp.northwind.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/customer/index");
        List<CustomerResponse> customer = customerService.getAll();

        view.addObject("customerList", customer);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/customer/add");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CustomerResponse response){
        customerService.save(response);
        return new ModelAndView("redirect:/customer");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/customer/edit");
        CustomerResponse customer = customerService.getById(id).orElse(null);
        if (customer == null){
            return new ModelAndView("redirect:/customer");
        }

        view.addObject("customer", customer);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute CustomerResponse response){
        customerService.update(response, response.getId());
        return new ModelAndView("redirect:/customer");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/customer/detail");
        CustomerResponse response = customerService.getById(id).orElse(null);
        if (response == null){
            return new ModelAndView("redirect:/customer");
        }

        view.addObject("customer", response);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/customer/delete");
        CustomerResponse customerResponse = customerService.getById(id).orElse(null);
        if (customerResponse == null){
            return new ModelAndView("redirect:/customer");
        }

        view.addObject("customer", customerResponse);
        return view;
    }

    @PostMapping("/delete-save")
    public String delete(@ModelAttribute CustomerResponse response){
        customerService.delete(response.getId());
        return "redirect:/customer";
    }
}
