package com.ash.pp.codesample.model;

import java.util.List;

public class ContactTracingResult {

    private Employee covidPositiveEmployee;

    private List<Employee> directlyAffectedEmployees;

    private List<Employee> indirectlyAffectedEmployees;

    public Employee getCovidPositiveEmployee() {
        return covidPositiveEmployee;
    }

    public void setCovidPositiveEmployee(Employee covidPositiveEmployee) {
        this.covidPositiveEmployee = covidPositiveEmployee;
    }

    public List<Employee> getDirectlyAffectedEmployees() {
        return directlyAffectedEmployees;
    }

    public void setDirectlyAffectedEmployees(List<Employee> directlyAffectedEmployees) {
        this.directlyAffectedEmployees = directlyAffectedEmployees;
    }

    public List<Employee> getIndirectlyAffectedEmployees() {
        return indirectlyAffectedEmployees;
    }

    public void setIndirectlyAffectedEmployees(List<Employee> indirectlyAffectedEmployees) {
        this.indirectlyAffectedEmployees = indirectlyAffectedEmployees;
    }
}
