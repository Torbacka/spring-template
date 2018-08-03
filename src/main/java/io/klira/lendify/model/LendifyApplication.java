package io.klira.lendify.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonClassDescription("Application")
public class LendifyApplication {
    @JsonProperty("LoanAmount")
    private Integer loanAmount;
    @JsonProperty("LoanMonths")
    private Integer loanMonths;
    @JsonProperty("PersonalNumber")
    private String personalNumber;
    @JsonProperty("Telephone1")
    private String telephone1;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("MaritalStatus")
    private MaritalStatus maritalStatus;
    @JsonProperty("JobStatus")
    private JobStatus jobStatus;
    @JsonProperty("JobSince")
    private String jobSince;
    @JsonProperty("JobCompany")
    private String jobCompany;
    @JsonProperty("MonthlyIncome")
    private Integer monthlyIncome;
    @JsonProperty("Kids")
    private Integer kids;
    @JsonProperty("LivingStatus")
    private LivingStatus livingStatus;
    @JsonProperty("LivingStatusSince")
    private String livingStatusSince;
    @JsonProperty("LivingLoanExpenses")
    private Integer livingLoanExpenses;
    @JsonProperty("LoanExpenses")
    private Integer loanExpenses;
    @JsonProperty("MonthlyExpensesOther")
    private Integer monthlyExpensesOther;
    @JsonProperty("ConsolidationAmount")
    private Integer consolidationAmount;
    @JsonProperty("LoanCategory")
    private String loanCategory;
    @JsonProperty("Status")
    private String status;


    public enum MaritalStatus {
        @JsonProperty("Married")
        MARRIED,
        @JsonProperty("Single")
        SINGLE,
        @JsonProperty("Divorced")
        DIVORCED,
        @JsonProperty("Sambo")
        SAMBO
    }

    public enum JobStatus {
        @JsonProperty("PermanentEmployee")
        PERMANENT_EMPLOYEE,
        @JsonProperty("TrialEmployee")
        TRIAL_EMPLOYEE,
        @JsonProperty("Pensioner")
        PENSIONER,
        @JsonProperty("EmployedPerHour")
        EMPLOYED_PER_HOUR,
        @JsonProperty("PensionerEarly")
        PENSIONER_EARLY,
        @JsonProperty("TemporaryEmployment")
        TEMPORARY_EMPLOYMENT,
        @JsonProperty("SelfEmployed")
        SELF_EMPLOYED,
        @JsonProperty("JobSeeker")
        JOB_SEEKER
    }

    public enum LivingStatus {
        @JsonProperty("Renting")
        RENTING,
        @JsonProperty("ApartmentOwner")
        APARTMENT_OWNER,
        @JsonProperty("SharedLiving")
        SHARED_LIVING,
        @JsonProperty("HouseOwner")
        HOUSE_OWNER
    }


    public static class Builder {
        private LendifyApplication lendifyApplication = new LendifyApplication();


        public Builder setLoanAmount(Integer loanAmount) {
            lendifyApplication.loanAmount = loanAmount;
            return this;
        }

        public Builder setLoanMonths(Integer loanMonths) {
            lendifyApplication.loanMonths = loanMonths;
            return this;
        }

        public Builder setPersonalNumber(String personalNumber) {
            lendifyApplication.personalNumber = personalNumber;
            return this;
        }

        public Builder setTelephone1(String telephone1) {
            lendifyApplication.telephone1 = telephone1;
            return this;
        }

        public Builder setEmail(String email) {
            lendifyApplication.email = email;
            return this;
        }

        public Builder setMaritalStatus(MaritalStatus maritalStatus) {
            lendifyApplication.maritalStatus = maritalStatus;
            return this;
        }

        public Builder setJobStatus(JobStatus jobStatus) {
            lendifyApplication.jobStatus = jobStatus;
            return this;
        }

        public Builder setJobSince(String jobSince) {
            lendifyApplication.jobSince = jobSince;
            return this;
        }

        public Builder setJobCompany(String jobCompany) {
            lendifyApplication.jobCompany = jobCompany;
            return this;
        }

        public Builder setMonthlyIncome(Integer monthlyIncome) {
            lendifyApplication.monthlyIncome = monthlyIncome;
            return this;
        }

        public Builder setKids(Integer kids) {
            lendifyApplication.kids = kids;
            return this;
        }

        public Builder setLivingStatus(LivingStatus livingStatus) {
            lendifyApplication.livingStatus = livingStatus;
            return this;
        }

        public Builder setLivingStatusSince(String livingStatusSince) {
            lendifyApplication.livingStatusSince = livingStatusSince;
            return this;
        }

        public Builder setLivingLoanExpenses(Integer livingLoanExpenses) {
            lendifyApplication.livingLoanExpenses = livingLoanExpenses;
            return this;
        }

        public Builder setLoanExpenses(Integer loanExpenses) {
            lendifyApplication.loanExpenses = loanExpenses;
            return this;
        }

        public Builder setMonthlyExpensesOther(Integer monthlyExpensesOther) {
            lendifyApplication.monthlyExpensesOther = monthlyExpensesOther;
            return this;
        }

        public Builder setConsolidationAmount(Integer consolidationAmount) {
            lendifyApplication.consolidationAmount = consolidationAmount;
            return this;
        }

        public Builder setLoanCategory(String loanCategory) {
            lendifyApplication.loanCategory = loanCategory;
            return this;
        }

        public Builder setStatus(String status) {
            lendifyApplication.status = status;
            return this;
        }
        
        public LendifyApplication build() {
            return lendifyApplication;
        }
    }
}



