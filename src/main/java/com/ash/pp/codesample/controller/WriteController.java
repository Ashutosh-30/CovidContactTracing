package com.ash.pp.codesample.controller;

import com.ash.pp.codesample.model.*;
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

    @PostMapping(path="insertMeetings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int insertMeetings(@RequestBody List<Meeting> meetingList) throws Exception {
        int rowsInserted = writeService.insertMeetingList(meetingList);

        if(rowsInserted == 0) {
            throw new ServerException("Unable to persist meetings. Please check your input.");
        }
        else {
            return rowsInserted;
        }
    }

    @PostMapping(path="insertEmployeeMeetingInteractions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int insertEmployeeMeetingInteractions(@RequestBody List<EmployeeMeetingInteraction> employeeMeetingInteractionList) throws Exception {
        int rowsInserted = writeService.insertEmployeeMeetingInteractionList(employeeMeetingInteractionList);

        if(rowsInserted == 0) {
            throw new ServerException("Unable to persist employee-meeting-interactions. Please check your input.");
        }
        else {
            return rowsInserted;
        }
    }

    @PostMapping(path="insertScans", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int insertScans(@RequestBody List<Scan> scanList) throws Exception {
        int rowsInserted = writeService.insertScanList(scanList);

        if(rowsInserted == 0) {
            throw new ServerException("Unable to persist scans. Please check your input.");
        }
        else {
            return rowsInserted;
        }
    }

    @PostMapping(path="insertTest", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ContactTracingResult insertTest(@RequestBody Test test) throws Exception {
        ContactTracingResult contactTracingResult = null;

        contactTracingResult = writeService.insertTest(test);

        if(contactTracingResult == null) {
            throw new ServerException("Unable to persist test. Please check your input.");
        }
        else {
            return contactTracingResult;
        }
    }

    @PostMapping(path="insertSelfReportSymptoms", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int insertSelfReportedSymptom(@RequestBody List<SelfReportSymptom> selfReportSymptomList) throws Exception {
        int rowsInserted = writeService.insertSelfReportSymptomList(selfReportSymptomList);

        if(rowsInserted == 0) {
            throw new ServerException("Unable to persist self report symptoms. Please check your input.");
        }
        else {
            return rowsInserted;
        }
    }
}
