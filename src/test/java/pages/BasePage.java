package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// Parent class for all page objects, containing common page interactions.
// It encapsulates common WebDriver initialization and utility functions like wait, scroll, etc.
// 🔧 This class is designed for mobile testing using AppiumDriver.

public class BasePage {

    // -------------------------------------------------------------------------
    // ✅ Driver and Wait Declarations
    // -------------------------------------------------------------------------

    protected AppiumDriver mobileDriver;   // Appium driver for mobile testing for all page classes
    protected WebDriverWait wait;          // Explicit wait to handle dynamic UI elements

    // Constructor for Appium (Mobile) to initialize the Page Object
    public BasePage(AppiumDriver driver)
    {
        System.out.println("[INFO] Initializing BasePage for mobile driver...");
        this.mobileDriver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize all @FindBy elements in child classes
        PageFactory.initElements(driver, this);
        System.out.println("[SUCCESS] Page elements initialized via PageFactory.");
    }

    // Additional utility methods like waitForVisibility(), scrollToElement(), etc. can be added here.S
    // -------------------------------------------------------------------------
    // 🔧 Reusable methods like:
    // - waitForVisibility()
    // - scrollToElement()
    // - isElementDisplayed()
    // - tapOnElement()
    // Can be defined here and used by all child page classes.
    // -------------------------------------------------------------------------

}

