package utils;

import pages.*;

public class PageInitializer {
    public static PersonalDetailsPage personalDetailsPage;
    public static LoginPage loginPage;

    public static AddEmployeePage addEmployeePage;
    public static EmployeeSearchPage employeeSearchPage;
    public static DashboardPage dashboardPage;
    public static ContactDetailsPage contactDetailsPage;
    public static AddDependentPage addDependentPage;
    public static JobDetailsPage jobDetailsPage;
    public static MembershipPage MembershipPage;

    public static void initializePageObjects() {
        loginPage = new LoginPage();
        addEmployeePage = new AddEmployeePage();
        employeeSearchPage = new EmployeeSearchPage();
        dashboardPage = new DashboardPage();
        contactDetailsPage = new ContactDetailsPage();
        addDependentPage = new AddDependentPage();
        jobDetailsPage = new JobDetailsPage();
        personalDetailsPage = new PersonalDetailsPage();
        MembershipPage = new MembershipPage();
    }
}
