package com.ash.pp.codesample.dao;

import com.ash.pp.codesample.model.Employee;
import com.ash.pp.codesample.model.Symptom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class ReadDao {

    Logger logger = LoggerFactory.getLogger(ReadDao.class);

    @Autowired
    private JdbcTemplate readJdbcTemplate;

    public List<Employee> getAllEmployees() {
        String sql = "select * from employee;";

        return readJdbcTemplate.query(sql, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getInt("employee_id"));
            employee.setEmployeeName(rs.getString("employee_name"));
            employee.setEmail(rs.getString("email"));
            employee.setMobileNumber(rs.getInt("mobile_number"));
            employee.setOfficeNumber(rs.getInt("employee_office_number"));
            employee.setFloorNumber(rs.getInt("employee_floor_number"));
            return employee;
        }
        );
    }

    public Employee getEmployeeById(int employeeId) {
        String sql = "select * from employee where employee_id = ?;";

        return readJdbcTemplate.queryForObject(sql, new Object[]{employeeId}, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setEmployeeName(rs.getString("employee_name"));
                employee.setEmail(rs.getString("email"));
                employee.setMobileNumber(rs.getInt("mobile_number"));
                employee.setOfficeNumber(rs.getInt("employee_office_number"));
                employee.setFloorNumber(rs.getInt("employee_floor_number"));
                return employee;
            }
        });
    }

    public List<Employee> getEmployeeListByFloor(int floorNumber) {
        String sql = "select * from employee where employee_floor_number = ?;";

        return readJdbcTemplate.query(sql, new Object[]{floorNumber}, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getInt("employee_id"));
            employee.setEmployeeName(rs.getString("employee_name"));
            employee.setEmail(rs.getString("email"));
            employee.setMobileNumber(rs.getInt("mobile_number"));
            employee.setOfficeNumber(rs.getInt("employee_office_number"));
            employee.setFloorNumber(rs.getInt("employee_floor_number"));
            return employee;
        });
    }

    public List<Employee> getDirectlyAffectedEmployeeList(int employeeId) {
        String sql = "select * from employee\n" +
                "where employee_id in(\n" +
                "select distinct(employee_id) from employee_meeting_interaction\n" +
                "where meeting_id in(select meeting_id from employee_meeting_interaction\n" +
                "where employee_id = ?)\n" +
                ");";

        return readJdbcTemplate.query(sql, new Object[]{employeeId}, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getInt("employee_id"));
            employee.setEmployeeName(rs.getString("employee_name"));
            employee.setEmail(rs.getString("email"));
            employee.setMobileNumber(rs.getInt("mobile_number"));
            employee.setOfficeNumber(rs.getInt("employee_office_number"));
            employee.setFloorNumber(rs.getInt("employee_floor_number"));
            return employee;
        });
    }

    public int getLastPKCase() {
        String sql = "select max(case_id) from positive_case;";

        return readJdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int getLastPKNotification() {
        String sql = "select max(notification_id) from notification;";

        return readJdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Map<Symptom, Integer> getSymptomIdMap() {
        String sql = "select * from symptom";

        return readJdbcTemplate.query(sql, (ResultSet rs) -> {
            Map<Symptom,Integer> map = new HashMap<>();
            while(rs.next()) {
                map.put(Symptom.valueOf(rs.getString("symptom_description")), rs.getInt("symptom_id"));
            }
            return map;
        });
    }

    public String getMostReportedSymptom() {
        String sql = "select s.symptom_description, count(srs.symptom_id) as freq\n" +
                "from self_report_symptom srs\n" +
                "join symptom s on s.symptom_id = srs.symptom_id\n" +
                "group by s.symptom_description\n" +
                "order by freq desc\n" +
                "limit 1;";

        return readJdbcTemplate.query(sql, (ResultSet rs) -> {
            String mostFreqSymptom = "";
            while(rs.next()) {
                mostFreqSymptom = (rs.getString("symptom_description"));
            }
            return mostFreqSymptom;
        });
    }

    public Integer getMostInfectedFloor() {
        String sql = "select e.employee_floor_number, count(e.employee_floor_number) as freq \n" +
                "from positive_case pc\n" +
                "join test t on t.test_id = pc.test_id\n" +
                "join employee e on e.employee_id = t.employee_id\n" +
                "group by e.employee_floor_number\n" +
                "order by freq desc\n" +
                "limit 1;";

        return readJdbcTemplate.query(sql, (ResultSet rs) -> {
            Integer mostInfectedFloor = 0;
            while(rs.next()) {
                mostInfectedFloor = (rs.getInt("employee_floor_number"));
            }
            return mostInfectedFloor;
        });
    }


    public List<String> commonSymptomsAmongstInfectedEmployees() {

        if(!viewExists("positive_employee_ids")) {
            String viewSql = "create view positive_employee_ids as\n" +
                    "select t.employee_id \n" +
                    "from positive_case pc\n" +
                    "join test t on t.test_id = pc.test_id;";

            readJdbcTemplate.execute(viewSql);
        }

        String tableDivisionSql = "select distinct srs.symptom_id, s.symptom_description\n" +
                "from self_report_symptom srs\n" +
                "join symptom s on s.symptom_id = srs.symptom_id\n" +
                "where not exists\n" +
                "(select pei.employee_id\n" +
                "from positive_employee_ids pei\n" +
                "where pei.employee_id not in (select srs2.employee_id from self_report_symptom srs2 where srs2.symptom_id = srs.symptom_id)\n" +
                ");";

        return readJdbcTemplate.query(tableDivisionSql, (ResultSet rs) -> {
            List<String> symptoms = new ArrayList<>();
            while(rs.next()) {
                symptoms.add(rs.getString("symptom_description"));
            }
            return symptoms;
        });
    }

    public boolean viewExists(String viewName) {
        String sql = "select count(*) \n" +
                "from information_schema.tables \n" +
                "where table_name = ?;";

        int result = readJdbcTemplate.queryForObject(sql, Integer.class, viewName);

        return result == 1;
    }

    public Map<String,Integer> symptomsOfInfectedEmployeesByFreq() {
        String sql = "select s.symptom_description, count(srs.symptom_id) as freq\n" +
                "from self_report_symptom srs\n" +
                "join symptom s on s.symptom_id = srs.symptom_id\n" +
                "join positive_employee_ids pei on pei.employee_id = srs.employee_id\n" +
                "group by s.symptom_description\n" +
                "order by freq desc;";

        return readJdbcTemplate.query(sql, (ResultSet rs) -> {
            Map<String,Integer> symptomFreqMap = new LinkedHashMap<>();
            while(rs.next()) {
                symptomFreqMap.put(rs.getString("symptom_description"), rs.getInt("freq"));
            }
            return symptomFreqMap;
        });
    }
}
