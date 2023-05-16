package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class LTUWebAutomationTestFinaldate {

    @BeforeAll
    static void setUp() {
        // Set up Selenide configuration
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Selenide.open("https://www.ltu.se/");
    }

    @Test
    void testLTU() throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(new FileReader("C:\\Users\\johan\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\untitled3\\JSON"));
        String username = json.get("facebookCredentials").getAsJsonObject().get("username").getAsString();
        String password = json.get("facebookCredentials").getAsJsonObject().get("password").getAsString();


        // Click on Cookie
        $(byXpath("/html/body/div[1]/div/div[4]/div[1]/div[2]/button[4]")).click();

        // Click on Student
        $(byXpath("/html/body/header/div[2]/div[1]/div[1]/div[3]/div/a[1]")).waitUntil(visible, 5000).click();
        // klicka på Registrerings intyg
        $(byXpath("/html/body/main/div/div/div[1]/div/div[2]/div/div/div/div/ul/li[1]/a")).click();

        String originalHandle = getWebDriver().getWindowHandle();
        for (String handle : getWebDriver().getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                getWebDriver().switchTo().window(handle);
                break;
            }
        }
        // Acc Cookies
        $(byXpath("/html/body/ladok-root/ladok-cookie-banner/div/div/div/div/div/div[2]/button[1]")).click();

        // Click on "Antagning"
        $(byXpath("/html/body/ladok-root/div/main/div/ladok-inloggning/div/div/" +
                "div/div/div/div/div/ladok-student/div[2]/a[2]/div/div[2]")).click();

        // Log in as user 2
        $(byXpath("/html/body/main/section/form/div[1]/input")).val(username);
        $(byXpath("/html/body/main/section/form/div[2]/input")).val(password);
        $(byXpath("/html/body/main/section/form/div[3]/button[1]")).click();
        // Navigate to the "Intyg" page
        $(byXpath("/html/body/div/ul/li/a")).click();
        // Öppnar kurs fliken
        $(byXpath("/html/body/ladok-root/div/main/div/ladok-startsida/ladok-aktuella-kurser/div/div/ladok-aktuell-kurs-kort[1]/ladok-card/div/div/div[1]/ladok-visa-mer/div/button")).click();
        // *coppy the start and end date of the course*


        String basePath = "C:\\Users\\johan\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\untitled3\\target";
        String fileName = "final_examination.jpeg";
        String screenshotFilePath = Paths.get(basePath, fileName).toString();

        try {
            // Take a screenshot
            SelenideElement element = $(("body"));

            File screenshotFile = element.screenshot();

            // Save the screenshot to the specified file path
            Path destinationPath = Paths.get(screenshotFilePath);
            Files.copy(screenshotFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Mission failed" + e.getMessage());
        }

        String datum = $(byXpath("/html/body/ladok-root/div/main/div/ladok-startsida/ladok-aktuella-kurser/div/div/ladok-aktuell-kurs-kort[1]/ladok-card/div/div/div[2]/ladok-card-body-extra/div/div/ladok-aktuell-kurs-kort-ex" +
                "tra-content/ladok-studiedeltagande-uppgifter/dl/dd[1]/span[1]/ladok-aria-datumperiod/span[1]")).getText();

        String datum2 = ("2023-03-27 - 2023-06-04");
        if (datum.equals(datum2)) {
            System.out.print("Yey rätt datum");
        } else {
            System.out.print("Wrong date");
        }

        //logg out
        $(byXpath("/html/body/ladok-root/div/ladok-meny/header/ladok-top-meny/div/div/div[2]/button")).click();
        $(byXpath("/html/body/ladok-root/div/ladok-sido-meny/nav/div[2]/ul[3]/li/a")).waitUntil(visible, 5000).click();


    }


        @AfterAll
        static void tearDown() {

            // Code to clean up after the test execution
            // Quit the WebDriver instance
            WebDriverRunner.closeWebDriver();
        }
        }