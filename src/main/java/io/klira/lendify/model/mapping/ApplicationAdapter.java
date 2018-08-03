package io.klira.lendify.model.mapping;


import io.klira.lendify.model.LendifyApplication;
import org.openbroker.events.ApplicationCreated;
import org.openbroker.model.Applicant;
import org.openbroker.model.EmploymentStatus;
import org.openbroker.model.HousingType;
import org.openbroker.model.MaritalStatus;

public class ApplicationAdapter implements Adapter<ApplicationCreated, LendifyApplication> {


    @Override
    public LendifyApplication transform(ApplicationCreated application) {
        Applicant applicant = application.getApplication()
                .getApplicant();

        return new LendifyApplication.Builder()
                .setLoanAmount(application.getApplication()
                        .getLoanAmount())
                .setLoanMonths(application.getApplication()
                        .getTermMonths())
                .setPersonalNumber(applicant.getSsn())
                .setTelephone1(applicant.getPhone())
                .setEmail(applicant.getEmailAddress())
                .setMaritalStatus(transform(applicant.getMaritalStatus()))
                .setJobStatus(transform(applicant.getEmploymentStatus()))
                .setJobSince(String.format("%d-%d", applicant.getEmploymentStatusSinceYear(), applicant.getEmploymentStatusSinceMonth()))
                .setJobCompany(applicant.getEmployerName())
                .setMonthlyIncome(applicant.getMonthlyIncome())
                .setKids(applicant.getDependentChildren())
                .setLivingStatus(transform(applicant.getHousingType()))
                .setLivingStatusSince("1900-01")
                .setLivingLoanExpenses(applicant.getHousingCostPerMonth())
                .setLoanExpenses(applicant.getMonthlyIncome())
                .setMonthlyExpensesOther(0)
                .setConsolidationAmount(0)
                .setLoanCategory("LoanConsolidation")
                .setStatus("New")
                .build();
    }

    private LendifyApplication.LivingStatus transform(HousingType housingType) {
        switch (housingType) {
            case RENTED:
                return LendifyApplication.LivingStatus.RENTING;
            case LIVE_IN:
                return LendifyApplication.LivingStatus.SHARED_LIVING;
            case OWN_APARTMENT:
                return LendifyApplication.LivingStatus.APARTMENT_OWNER;
            case OWN_HOUSE:
                return LendifyApplication.LivingStatus.HOUSE_OWNER;
            default:
                throw new IllegalArgumentException("Housing type was not found");
        }
    }

    private LendifyApplication.JobStatus transform(EmploymentStatus employmentStatus) {
        switch (employmentStatus) {
            case EARLY_RETIRED:
                return LendifyApplication.JobStatus.PENSIONER_EARLY;
            case FULL_TIME:
                return LendifyApplication.JobStatus.PERMANENT_EMPLOYEE;
            case HOURLY:
                return LendifyApplication.JobStatus.EMPLOYED_PER_HOUR;
            case RETIRED:
                return LendifyApplication.JobStatus.PENSIONER;
            case SELF_EMPLOYED:
                return LendifyApplication.JobStatus.SELF_EMPLOYED;
            case TEMPORARY:
                return LendifyApplication.JobStatus.TRIAL_EMPLOYEE;
            case TRIAL:
                return LendifyApplication.JobStatus.TRIAL_EMPLOYEE;
            case OTHER:
            case STUDENT:
            case UNEMPLOYED:
                return LendifyApplication.JobStatus.JOB_SEEKER;
            default:
                throw new IllegalArgumentException("Job status was not found");
        }
    }

    private LendifyApplication.MaritalStatus transform(MaritalStatus maritalStatus) {
        switch (maritalStatus) {
            case SINGLE:
                return LendifyApplication.MaritalStatus.SINGLE;
            case MARRIED:
                return LendifyApplication.MaritalStatus.MARRIED;
            case COHABITING:
                return LendifyApplication.MaritalStatus.SAMBO;
            default:
                    throw new IllegalArgumentException("Marial status was not found");
        }
    }
}
