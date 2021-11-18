package com.ash.pp.codesample.controller;

import com.ash.pp.codesample.model.Employee;
import com.ash.pp.codesample.model.Symptom;
import com.ash.pp.codesample.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReadController {

    @Autowired
    ReadService readService;

    @GetMapping(value="/getAllEmployees")
    public List<Employee> getAllEmployee() {
        return readService.getAllEmployees();
    }

    @GetMapping(value="/getEmployeesByFloor")
    public List<Employee> getEmployeesByFloor(@RequestParam int floorNumber) {
        return readService.getEmployeeListByFloor(floorNumber);
    }

    @GetMapping(value="/getDirectlyAffectedEmployees")
    public List<Employee> getDirectlyAffectedEmployees(@RequestParam int employeeId) {
        return readService.getDirectlyAffectedEmployees(employeeId);
    }

    @GetMapping(value="/getEmployeeById")
    public Employee getEmployeeById(@RequestParam int employeeId) {
        return readService.getEmployeeByEmpId(employeeId);
    }

    @GetMapping(value="/getSymptomIdMap")
    public Map<Symptom,Integer> getSymptomIdMap() {
        return readService.getSymptomIdMap();
    }

    @GetMapping(value="/getMostSelfReportedSymptom")
    public String getMostSelfReportedSymptom() {
        return readService.getMostSelfReportedSymptom();
    }

    @GetMapping(value="/getMostInfectedFloor")
    public Integer getMostInfectedFloor() {
        return readService.getMostInfectedFloor();
    }

    @GetMapping(value="/commonSymptomsAmongstInfectedEmployees")
    public List<String> commonSymptomsAmongstInfectedEmployees() {
        return readService.commonSymptomsAmongstInfectedEmployees();
    }

    @GetMapping(value="/printSpiroPoints")
    public void printSpiroPoints() {
        double R=8.0, r=1.0, a=4.0;
        double x0 = R + r - a, y0 = 0;

        double pi = Math.PI;
        int nRev = 16;
        System.out.println("[");
        for (double t = 0.0; t < (pi * nRev); t += 0.1) {
            double x = (R + r) * Math.cos((r / R) * t) - a * Math.cos((1 + r / R) * t);
            double y = (R + r) * Math.sin((r / R) * t) - a * Math.sin((1 + r / R) * t);

            double scaledX = -118.2846063 + 0.0001 * x;
            double scaledY = 34.0210821 + 0.0001 * y;
            System.out.println("{");
            System.out.println("\"loc\": ["+scaledX+","+scaledY+"]");
            System.out.println("},");
        }
        System.out.println("]");
    }

}
