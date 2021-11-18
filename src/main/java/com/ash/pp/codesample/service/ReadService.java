package com.ash.pp.codesample.service;

import com.ash.pp.codesample.dao.ReadDao;
import com.ash.pp.codesample.model.Employee;
import com.ash.pp.codesample.model.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReadService {

    @Autowired
    ReadDao readDao;

    public List<Employee> getAllEmployees() {
        return readDao.getAllEmployees();
    }

    public Employee getEmployeeByEmpId(int employeeId) {
        return readDao.getEmployeeById(employeeId);
    }

    public List<Employee> getEmployeeListByFloor(int floorNumber) {
        return readDao.getEmployeeListByFloor(floorNumber);
    }

    public List<Employee> getDirectlyAffectedEmployees(int employeeId) {
        return readDao.getDirectlyAffectedEmployeeList(employeeId);
    }

    public int getLastPKCase() {
        return readDao.getLastPKCase();
    }

    public int getLastPKNotification() {
        return readDao.getLastPKNotification();
    }

    public Map<Symptom,Integer> getSymptomIdMap() {
        return readDao.getSymptomIdMap();
    }

    public String getMostSelfReportedSymptom() {
        return readDao.getMostReportedSymptom();
    }

    public Integer getMostInfectedFloor() {
        return readDao.getMostInfectedFloor();
    }

    public List<String> commonSymptomsAmongstInfectedEmployees() {
        return readDao.commonSymptomsAmongstInfectedEmployees();
    }
}
