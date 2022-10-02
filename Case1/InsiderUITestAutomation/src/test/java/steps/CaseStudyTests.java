package steps;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CareerPage;
import pages.Homepage;
import pages.OpenPositionsPage;
import pages.QualityAssurancePage;

public class CaseStudyTests {

    Hooks hooks = new Hooks();
    Homepage homepage = new Homepage();
    CareerPage careerPage = new CareerPage();
    QualityAssurancePage qualityAssurancePage = new QualityAssurancePage();
    OpenPositionsPage openPositionsPage = new OpenPositionsPage();

    @Test(testName = "firstStep", priority = 1)
    public void firstStep() {
        homepage.verifyHomepageIsOpen();
        homepage.closeCookies();
    }

    @Test(testName = "secondStep", priority = 2)
    public void secondStep() {
        homepage.selectFromMenu("More");
        homepage.clickCareers();
        careerPage.assertCareerPageIsOpen();
        careerPage.assertTeams();
        careerPage.assertLocations();
        careerPage.assertLifeAtInsider();
    }

    @Test(testName = "thirdStep", priority = 3)
    public void thirdStep() {
        careerPage.clickSeeAllTeams();
        careerPage.clickQualityAssuranceTeam();
        qualityAssurancePage.clickSeeAllQaJobs();
        openPositionsPage.filterJobsByLocation();
        openPositionsPage.filterJobsByDepartment();
        openPositionsPage.assertJobsList();
    }

    @Test(testName = "fourthStep", priority = 4)
    public void fourthStep() {
        openPositionsPage.verifyListingPositions("Quality Assurance", "QA", "Quality Assurance", "Istanbul, Turkey");
    }

    @Test(testName = "fifthStep", priority = 5)
    public void fifthStep() {
        openPositionsPage.verifyApplyNowButtonNavigation();
    }

    @BeforeTest
    public void setup() {
        hooks.setDriver();
    }

    @AfterTest
    public void teardown() {
        hooks.closeDriver();
    }

    @AfterMethod
    public void screenshotCapture(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            hooks.captureScreenshot(result);
        }
    }
}
