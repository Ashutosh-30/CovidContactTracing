package com.ash.pp.codesample.dao;

import com.ash.pp.codesample.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Not;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class WriteDao {

    Logger logger = LoggerFactory.getLogger(WriteDao.class);

    @Autowired
    JdbcTemplate writeJdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    public int insertEmployeeList(List<Employee> employeeList) {
        String sql = "insert into employee values(?,?,?,?,?,?)";

        writeJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Employee employee = employeeList.get(i);
                ps.setInt(1, employee.getEmployeeId());
                ps.setString(2, employee.getEmployeeName());
                ps.setInt(3, employee.getMobileNumber());
                ps.setString(4, employee.getEmail());
                ps.setInt(5, employee.getOfficeNumber());
                ps.setInt(6, employee.getFloorNumber());
            }

            @Override
            public int getBatchSize() {
                logger.info("Inserted employee list of size : "+employeeList.size());
                return employeeList.size();
            }
        });

        return employeeList.size();
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertMeetingList(List<Meeting> meetingList) {
        String sql = "insert into meeting values(?,?,?,?,?)";

        writeJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Meeting meeting = meetingList.get(i);
                ps.setInt(1, meeting.getMeetingId());
                ps.setInt(2, meeting.getMeetingRoomNumber());
                ps.setInt(3, meeting.getMeetingFloorNumber());
                ps.setString(4, meeting.getStartTime());
                ps.setString(5, meeting.getEndTime());
            }

            @Override
            public int getBatchSize() {
                logger.info("Inserted meeting list of size : "+meetingList.size());
                return meetingList.size();
            }
        });

        return meetingList.size();
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertEmployeeMeetingInteractionList(List<EmployeeMeetingInteraction> employeeMeetingInteractionList) {
        String sql = "insert into employee_meeting_interaction values(?,?,?)";

        writeJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                EmployeeMeetingInteraction eme = employeeMeetingInteractionList.get(i);
                ps.setInt(1, eme.getEmployeeMeetingInteractionId());
                ps.setInt(2, eme.getMeetingId());
                ps.setInt(3, eme.getEmployeeId());
            }

            @Override
            public int getBatchSize() {
                logger.info("Inserted employee-meeting-interaction list of size : "+employeeMeetingInteractionList.size());
                return employeeMeetingInteractionList.size();
            }
        });

        return employeeMeetingInteractionList.size();
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertScanList(List<Scan> scanList) {
        String sql = "insert into scan values(?,?,?,?)";

        writeJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Scan scan = scanList.get(i);
                ps.setInt(1, scan.getScanId());
                ps.setInt(2, scan.getEmployeeId());
                ps.setString(3, scan.getScanDate());
                ps.setInt(4, scan.getTemperature());
            }

            @Override
            public int getBatchSize() {
                logger.info("Inserted scan list of size : "+scanList.size());
                return scanList.size();
            }
        });

        return scanList.size();
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertTest(Test test) {
        String sql = "insert into test values(?,?,?,?,?)";

        return writeJdbcTemplate.update(sql,
                test.getTestId(),
                test.getEmployeeId(),
                test.getTestDate(),
                test.getTestResult().getTestResult(),
                test.getTestLocation().toString());
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertCase(Case c) {
        String sql = "insert into positive_case values(?,?,?)";

        return writeJdbcTemplate.update(sql,
                c.getCaseId(),
                c.getTestId(),
                c.getCaseResolution());
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertNotificationList(List<Notification> notificationList) {
        String sql = "insert into notification values(?,?,?,?)";

        writeJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Notification notification = notificationList.get(i);
                ps.setInt(1, notification.getNotificationId());
                ps.setInt(2, notification.getEmployeeId());
                ps.setString(3, notification.getNotificationDate());
                ps.setString(4, notification.getNotificationType().toString());
            }

            @Override
            public int getBatchSize() {
                logger.info("Inserted notification list of size : "+notificationList.size());
                return notificationList.size();
            }
        });

        return notificationList.size();
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertContactTracingData(Test test, Case positiveCase, Employee covidPositiveEmployee, List<Notification> notificationList) {
        insertTest(test);

        insertCase(positiveCase);

        insertNotificationList(notificationList);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertHealthStatusList(List<HealthStatus> healthStatusList) {
        String sql = "insert into health_status values(?,?,?,?)";

        writeJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                HealthStatus healthStatus = healthStatusList.get(i);
                ps.setInt(1, healthStatus.getHealthStatusId());
                ps.setInt(2, healthStatus.getCaseId());
                ps.setString(3, healthStatus.getStatusDate());
                ps.setString(4, healthStatus.getStatusDescription());
            }

            @Override
            public int getBatchSize() {
                logger.info("Inserted health status list of size : "+healthStatusList.size());
                return healthStatusList.size();
            }
        });

        return healthStatusList.size();
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertSelfReportSymptomList(List<SelfReportSymptom> selfReportSymptomList, Map<Symptom,Integer> symptomToIdMap) {
        String sql = "insert into self_report_symptom values(?,?,?,?)";

        writeJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SelfReportSymptom selfReportSymptom = selfReportSymptomList.get(i);
                ps.setInt(1, selfReportSymptom.getSelfReportSymptomId());
                ps.setInt(2, selfReportSymptom.getEmployeeId());
                ps.setInt(3, symptomToIdMap.get(selfReportSymptom.getSymptom()));
                ps.setString(4, selfReportSymptom.getReportedDate());
            }

            @Override
            public int getBatchSize() {
                logger.info("Inserted self reported symptom list of size : "+selfReportSymptomList.size());
                return selfReportSymptomList.size();
            }
        });

        return selfReportSymptomList.size();
    }

}
