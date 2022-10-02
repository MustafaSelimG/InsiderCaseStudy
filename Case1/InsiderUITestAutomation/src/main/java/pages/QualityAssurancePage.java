package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class QualityAssurancePage {
    public QualityAssurancePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(className = "w-md-50")
    public WebElement seeAllQaJobsBtn;

    public void clickSeeAllQaJobs() {
        seeAllQaJobsBtn.click();
    }
}
