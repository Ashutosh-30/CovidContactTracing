package com.ash.pp.codesample.model;

public class HealthStatus {

    private int healthStatusId;

    private int caseId;

    private String statusDate;

    private String statusDescription;

    public int getHealthStatusId() {
        return healthStatusId;
    }

    public void setHealthStatusId(int healthStatusId) {
        this.healthStatusId = healthStatusId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
