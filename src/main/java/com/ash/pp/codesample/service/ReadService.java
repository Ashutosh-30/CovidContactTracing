package com.ash.pp.codesample.service;

import com.ash.pp.codesample.dao.ReadDao;
import com.ash.pp.codesample.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadService {

    @Autowired
    ReadDao readDao;

    public List<Employee> getAllEmployees() {
        return readDao.getAllEmployees();
    }
}