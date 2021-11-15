package com.ash.pp.codesample.model;

public enum TestResult {

    Positive("p"),
    Negative("n");

    private String testResult;

    private TestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTestResult() {
        return testResult;
    }

}
