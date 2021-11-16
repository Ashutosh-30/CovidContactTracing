package com.ash.pp.codesample.controller;

import com.ash.pp.codesample.model.Employee;
import com.ash.pp.codesample.service.WriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.ServerException;
import java.util.List;

@RestController
public class WriteController {

    @Autowired
    WriteService writeService;

    @PostMapping(path="insertEmployees", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int insertEmployees(@RequestBody List<Employee> employeeList) throws Exception {
        int rowsInserted = writeService.insertEmployeeList(employeeList);

        if(rowsInserted == 0) {
            throw new ServerException("Unable to persist employees. Please check your input.");
        }
        else {
            return rowsInserted;
        }
    }
}
