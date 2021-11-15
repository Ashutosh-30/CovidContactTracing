package com.ash.pp.codesample.controller;

import com.ash.pp.codesample.model.Employee;
import com.ash.pp.codesample.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReadController {

    @Autowired
    ReadService readService;

    @RequestMapping(value="/getAllEmployees")
    public List<Employee> getAllEmployee() {
        return readService.getAllEmployees();
    }

}
