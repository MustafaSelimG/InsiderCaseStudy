package pages;

import helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class CareerPage {
    public CareerPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void assertCareerPageIsOpen() {
        Helper.assertUrl("https://useinsider.com/careers/");
    }

    @FindBy(id = "career-find-our-calling")
    public WebElement teamsSection;

    @FindBy(css = ".category-title-media")
    public WebElement teamsHeader;

    @FindBy(linkText = "See all teams")
    public WebElement seeAllTeamsBtn;

    @FindBy(id = "career-our-location")
    public WebElement locationSection;

    @FindBy(css = ".category-title-media.ml-0")
    public WebElement locationHeader;

    @FindBy(css = ".mx-auto.pl-0")
    public WebElement locationParagraph;

    @FindBy(xpath = "//section[@data-id='a8e7b90']")
    public WebElement lifeAtInsiderSection;

    @FindBy(xpath = "(//h2[@class='elementor-heading-title elementor-size-default'])[2]")
    public WebElement lifeAtInsiderHeader;

    @FindBy(xpath = "(//div[@class='elementor-text-editor elementor-clearfix']//p)[4]")
    public WebElement lifeAtInsiderParagraph;

    @FindBy(xpath = "//h3[text()='Quality Assurance']")
    public WebElement qualityAssuranceTeam;

    public void assertTeams() {
        Helper.assertElementIsDisplayed(teamsSection);
        Helper.assertElementText(teamsHeader, "Find your calling");
        Helper.assertElementIsDisplayed(seeAllTeamsBtn);
    }

    public void assertLocations() {
        Helper.assertElementIsDisplayed(locationSection);
        Helper.assertElementText(locationHeader, "Our Locations");
        Helper.assertElementText(locationParagraph, "25 offices across 5 continents, home to 600+ Insiders");
    }

    public void assertLifeAtInsider() {
        Helper.assertElementIsDisplayed(lifeAtInsiderSection);
        Helper.assertElementText(lifeAtInsiderHeader, "Life at Insider");
        Helper.assertElementText(lifeAtInsiderParagraph, "We’re here to grow and drive growth—as none of us did before. Together, we’re building a culture that inspires us to create our life’s work—and creates a bold(er) impact. We know that we’re smarter as a group than we are alone. Become one of us if you dare to play bigger.");
    }

    public void clickSeeAllTeams() {
        Helper.click(seeAllTeamsBtn);
    }

    public void clickQualityAssuranceTeam() {
        Helper.scrollToElement(qualityAssuranceTeam);
        Helper.click(qualityAssuranceTeam);
    }

}
