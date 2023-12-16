package com.bootcamp.northwind.controller;

import com.bootcamp.northwind.model.response.CustomerDemographicsResponse;
import com.bootcamp.northwind.service.CustomerDemograpService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customerDemograp")
public class CustomerDemograpController {
    private final CustomerDemograpService customerDemograpService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/_customerDemograp/index");
        List<CustomerDemographicsResponse> responses = customerDemograpService.getAll();

        view.addObject("customerDemograp", responses);
        return view;
    }

    @GetMapping("/add-modal")
    public ModelAndView addModal(){
        ModelAndView view = new ModelAndView("pages/_customerDemograp/add");
        return view;
    }
}
