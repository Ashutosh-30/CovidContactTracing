package com.ash.pp.codesample.service;

import com.ash.pp.codesample.dao.WriteDao;
import com.ash.pp.codesample.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriteService {

    @Autowired
    WriteDao writeDao;

    public int insertEmployeeList(List<Employee> employeeList) {
        return writeDao.insertEmployeeList(employeeList);
    }
}
