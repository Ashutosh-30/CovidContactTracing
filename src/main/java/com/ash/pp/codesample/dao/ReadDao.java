package com.ash.pp.codesample.dao;

import com.ash.pp.codesample.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

}
