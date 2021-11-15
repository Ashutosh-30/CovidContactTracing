package com.ash.pp.codesample.dao;

import com.ash.pp.codesample.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ReadDao {

    @Autowired
    private JdbcTemplate readJdbcTemplate;

    public List<Employee> getAllEmployees() {
        String sql = "select * from employee;";

        return readJdbcTemplate.query(sql, new RowMapper<Employee>() {
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
                }
        );
    }


}
