package pages;

import helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class OpenPositionsPage {
    public OpenPositionsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "select2-filter-by-location-container")
    public WebElement filterLocationBtn;

    @FindBy(xpath = "//li[contains(@id, 'Istanbul, Turkey')]")
    public WebElement filterLocationIstanbul;

    @FindBy(id = "select2-filter-by-department-container")
    public WebElement filterDepartmentBtn;

    @FindBy(xpath = "//li[contains(@id, 'Quality Assurance')]")
    public WebElement filterDepartmentQualityAssurance;

    @FindBy(id = "jobs-list")
    public WebElement jobsListContainer;

    @FindBy(id = "deneme")
    public WebElement totalPositionText;

    public void filterJobsByLocation() {
        Helper.wait(2);
        filterLocationBtn.click();
        filterLocationIstanbul.click();
    }

    public void filterJobsByDepartment() {
        filterDepartmentBtn.click();
        filterDepartmentQualityAssurance.click();
    }

    public void assertJobsList() {
        Helper.assertElementIsDisplayed(jobsListContainer);
    }

    public void verifyListingPositions(String positionTitle, String positionAbbreviation, String department, String location) {
        Helper.wait(3);
        int totalPositions = Integer.parseInt(totalPositionText.getText());

        for (int i = 1; i <= totalPositions; i++) {
            Helper.assertElementTextContains(Helper.findByXpath(("(//p[contains(@class, 'position-title')])[" + i + "]")), positionTitle, positionAbbreviation);
            Helper.assertElementText(Helper.findByXpath(("(//span[contains(@class, 'position-department')])[" + i + "]")), department);
            Helper.assertElementText(Helper.findByXpath(("(//div[contains(@class, 'position-location')])[" + i + "]")), location);
            Helper.assertElementIsEnabled(Helper.findByXpath(("(//a[text()= 'Apply Now'])[" + i + "]")));
        }
    }

    public void verifyApplyNowButtonNavigation() {
        int totalPositions = Integer.parseInt(totalPositionText.getText());

        for (int i = 1; i <= totalPositions; i++) {
            Helper.click(Helper.findByXpath(("(//a[text()= 'Apply Now'])[" + i + "]")));
            Helper.switchLastTab();
            Helper.assertElementIsEnabled(Helper.findByXpath("//img[contains (@alt, 'Insider. logo')]"));
            Helper.assertUrlContains("https://jobs.lever.co/useinsider");
            Helper.closeTab();
            Helper.switchToMainTab();
        }
    }

}
