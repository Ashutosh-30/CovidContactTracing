package com.ash.pp.codesample.dao;

import com.ash.pp.codesample.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class WriteDao {

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
                return employeeList.size();
            }
        });

        return employeeList.size();
    }

}
