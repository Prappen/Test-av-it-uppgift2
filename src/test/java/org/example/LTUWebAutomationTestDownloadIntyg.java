package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
public class LTUWebAutomationTestDownloadIntyg {



    @BeforeAll
    static void setUp() {
        // Set up Selenide configuration
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Selenide.open("https://www.ltu.se/");
    }

    @Test
    void testLTU() throws FileNotFoundException {
        // Read credentials from JSON file
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

        String originalHandle = WebDriverRunner.getWebDriver().getWindowHandle();
        for (String handle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                WebDriverRunner.getWebDriver().switchTo().window(handle);
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
        $(byXpath("/html/body/ladok-root/div/ladok-meny/header/ladok-top-meny/div/div/div[2]/button")).click();
        $(byXpath("/html/body/ladok-root/div/ladok-sido-meny/nav/div[2]/ul[1]/li[3]/ladok-behorighetsstyrd-nav-link/a")).click();
        $(byXpath("/html/body/ladok-root/div/main/div/ladok-intyg/ladok-listning-av-skapade-intyg" +
                "/div/div/ladok-accordion/div/ladok-list-kort/div/div[1]/div/div[1]/a")).click();


        Selenide.sleep(10000);

        String Folderpath = "C:\\Users\\johan\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\untitled3\\build\\downloads";
        String Filename = "intyg.ext";
        String Downloadfilepath = "C:\\Users\\johan\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\untitled3\\build\\downloads";
        Path Sourcepath = Path.of(Downloadfilepath);
        Path Destinationpath = Path.of(Filename);

        try {
            Files.move(Sourcepath, Destinationpath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved in the correct folder.");
        } catch (Exception e) {
            System.out.println("Failed to save the file in the correct folder: " + e.getMessage());
        }

 // Check if the file exists in the specified folder
        boolean fileExists = Files.exists(Destinationpath);
        if (fileExists) {
            System.out.println("File saved in the correct folder.");
        } else {
            System.out.println("File not saved in the correct folder.");
        }
    }


    @AfterAll
    static void tearDown () {

        // Close the web browser and end the test
        WebDriverRunner.closeWebDriver();
    }
}

