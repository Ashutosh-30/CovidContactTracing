package com.ash.pp.codesample.model;

public class SelfReportSymptom {

    private int selfReportSymptomId;

    private int employeeId;

    private Symptom symptom;

    private String reportedDate;

    public int getSelfReportSymptomId() {
        return selfReportSymptomId;
    }

    public void setSelfReportSymptomId(int selfReportSymptomId) {
        this.selfReportSymptomId = selfReportSymptomId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(String reportedDate) {
        this.reportedDate = reportedDate;
    }
}
