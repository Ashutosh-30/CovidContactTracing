package com.ash.pp.codesample.service;

import com.ash.pp.codesample.dao.WriteDao;
import com.ash.pp.codesample.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class WriteService {

    @Autowired
    WriteDao writeDao;

    @Autowired
    ReadService readService;

    public int insertEmployeeList(List<Employee> employeeList) {
        return writeDao.insertEmployeeList(employeeList);
    }

    public int insertMeetingList(List<Meeting> meetingList) {
        return writeDao.insertMeetingList(meetingList);
    }

    public int insertEmployeeMeetingInteractionList(List<EmployeeMeetingInteraction> employeeMeetingInteractionList) {
        return writeDao.insertEmployeeMeetingInteractionList(employeeMeetingInteractionList);
    }

    public int insertScanList(List<Scan> scanList) {
        return writeDao.insertScanList(scanList);
    }

    public ContactTracingResult insertTest(Test test) throws Exception {

        ContactTracingResult contactTracingResult = new ContactTracingResult();

        if(test.getTestResult().equals(TestResult.Negative)) {
            writeDao.insertTest(test);
        }
        else if(test.getTestResult().equals(TestResult.Positive)) { // contact tracing kicks in
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currDateTime = sdf.format(cal.getTime());


            Employee covidPositiveEmployee = readService.getEmployeeByEmpId(test.getEmployeeId());

            Case positiveCase = new Case();
            positiveCase.setCaseId(readService.getLastPKCase()+1);
            positiveCase.setTestId(test.getTestId());
            positiveCase.setCaseResolution("Just Diagnosed");

            List<Notification> notificationList = new ArrayList<>();
            int nPk = readService.getLastPKNotification();
            //get ""optional" notification employees
            List<Employee> sameFloorEmployees = readService.getEmployeeListByFloor(covidPositiveEmployee.getFloorNumber());
            for(Employee e : sameFloorEmployees) {
                if(e.getEmployeeId() != covidPositiveEmployee.getEmployeeId()) {
                    Notification notification = new Notification();
                    notification.setNotificationId(++nPk);
                    notification.setEmployeeId(e.getEmployeeId());
                    notification.setNotificationType(NotificationType.Optional);
                    notification.setNotificationDate(currDateTime);
                    notificationList.add(notification);
                }
            }

            //get ""mandatory" notification employees
            List<Employee> directlyAffectedEmployees = readService.getDirectlyAffectedEmployees(covidPositiveEmployee.getEmployeeId());
            for(Employee e : directlyAffectedEmployees) {
                Notification notification = new Notification();
                notification.setNotificationId(++nPk);
                notification.setEmployeeId(e.getEmployeeId());
                notification.setNotificationType(NotificationType.Mandatory);
                notification.setNotificationDate(currDateTime);
                notificationList.add(notification);
            }

            writeDao.insertContactTracingData(test, positiveCase, covidPositiveEmployee, notificationList);

            contactTracingResult.setCovidPositiveEmployee(covidPositiveEmployee);
            contactTracingResult.setDirectlyAffectedEmployees(directlyAffectedEmployees);
            contactTracingResult.setIndirectlyAffectedEmployees(sameFloorEmployees);
        }
        else {
            throw new Exception("Test Result field's value is not valid.");
        }

        return contactTracingResult;
    }

    public int insertHealthStatusList(List<HealthStatus> healthStatusList) {
        return writeDao.insertHealthStatusList(healthStatusList);
    }

    public int insertSelfReportSymptomList(List<SelfReportSymptom> selfReportSymptomList) {
        Map<Symptom,Integer> symptomToIdMap = readService.getSymptomIdMap();

        return writeDao.insertSelfReportSymptomList(selfReportSymptomList, symptomToIdMap);
    }
}
