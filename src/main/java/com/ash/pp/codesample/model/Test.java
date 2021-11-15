package com.ash.pp.codesample.model;

public class Test {

    private int testId;

    private int employeeId;

    private String testDate;

    private TestResult testResult;

    private TestLocation testLocation;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public TestLocation getTestLocation() {
        return testLocation;
    }

    public void setTestLocation(TestLocation testLocation) {
        this.testLocation = testLocation;
    }
}
