package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.io.Files;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static com.codeborne.selenide.Condition.visible;
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
    void testLTU() throws FileNotFoundException, InterruptedException {
        // Read credentials from JSON file
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(new FileReader("C:\\Users\\johan\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\untitled3\\JSON"));
        String username = json.get("facebookCredentials").getAsJsonObject().get("username").getAsString();
        String password = json.get("facebookCredentials").getAsJsonObject().get("password").getAsString();


        // Click on Cookie
        $(By.xpath("/html/body/div[1]/div/div[4]/div[1]/div[2]/button[4]")).click();

        // Click on Student
        $(By.xpath("/html/body/header/div[2]/div[1]/div[1]/div[3]/div/a[1]")).waitUntil(visible, 5000).click();
        // klicka på Registrerings intyg
        $(By.xpath("/html/body/main/div/div/div[1]/div/div[2]/div/div/div/div/ul/li[1]/a")).click();

        String originalHandle = WebDriverRunner.getWebDriver().getWindowHandle();
        for (String handle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                WebDriverRunner.getWebDriver().switchTo().window(handle);
                break;
            }
        }
        // Acc Cookies
        $(By.xpath("/html/body/ladok-root/ladok-cookie-banner/div/div/div/div/div/div[2]/button[1]")).click();

        // Click on "Antagning"
        $(By.xpath("/html/body/ladok-root/div/main/div/ladok-inloggning/div/div/" +
                "div/div/div/div/div/ladok-student/div[2]/a[2]/div/div[2]")).click();

        // Log in as user 2
        $(By.xpath("/html/body/main/section/form/div[1]/input")).val(username);
        $(By.xpath("/html/body/main/section/form/div[2]/input")).val(password);
        $(By.xpath("/html/body/main/section/form/div[3]/button[1]")).click();

        // Navigate to the "Intyg" page
        $(By.xpath("/html/body/div/ul/li/a")).click();
        $(By.xpath("/html/body/ladok-root/div/ladok-meny/header/ladok-top-meny/div/div/div[2]/button")).click();
        $(By.xpath("/html/body/ladok-root/div/ladok-sido-meny/nav/div[2]/ul[1]/li[3]/ladok-behorighetsstyrd-nav-link/a")).click();
        $(By.xpath("/html/body/ladok-root/div/main/div/ladok-intyg/ladok-listning-av-skapade-intyg" +
                "/div/div/ladok-accordion/div/ladok-list-kort/div/div[1]/div/div[1]/a")).click();

        // Wait for file to download
        Thread.sleep(5000);

// Get the downloads folder location
        String downloadsFolder = System.getProperty("user.home") + "\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\untitled3\\build\\downloads\\";

// Find the most recently created folder in the downloads directory
        File[] files = new File(downloadsFolder).listFiles(File::isDirectory);
        Arrays.sort(files, Comparator.comparing(File::lastModified).reversed());
        String mostRecentFolderName = files[0].getName();

// Construct the path to the downloaded file using the most recent folder name
        String filePath = downloadsFolder + mostRecentFolderName + "\\intyg.pdf";
        String path = "C:\\Users\\johan\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\untitled3\\target";

// Move the downloaded file to the target path
        File downloadedFile = new File(filePath);
        try {
            Files.move(downloadedFile.toPath().toFile(), Paths.get(path).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        }


        @AfterAll
        static void tearDown () {
            // Code to clean up after the test execution
            // Wait for user input before terminating the program
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press any key to exit...");
            scanner.nextLine();
        }
    }
