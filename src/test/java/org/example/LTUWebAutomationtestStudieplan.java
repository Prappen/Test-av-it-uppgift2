package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;


public class LTUWebAutomationtestStudieplan {


    @BeforeAll
    static void setUp() {
        // Set up Selenide configuration
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Selenide.open("https://www.ltu.se/");

    }

    @Test
    void testLTU() {


        $(byXpath("/html/body/div[1]/div/div[4]/div[1]/div[2]/button[4]")).click();
        $(byXpath("/html/body/main/div/div/div[1]/div/div[3]/div/div/div/div/div/a")).click();
        $(byXpath("/html/body/main/div/div/div/div[1]/nav/div/ul/li[1]/div/a")).click();
        $(byXpath("/html/body/main/div/div/div/div[1]/nav/div/ul/li[1]/ul/li[4]/div/a")).click();
        $(byXpath("/html/body/main/div/div/div/div[1]/nav/div/ul/li[7]/a")).click();
        $(byXpath("/html/body/main/div/div/div/div[2]/div/article/div[1]/section/div[5]/aside/div[3]/a/div")).click();
        $(byXpath("/html/body/main/div/div/div/div[2]/div/article/section/form/a")).click();


        Selenide.sleep(10000);

        String Folderpath = "C:\\Users\\johan\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\untitled3\\build\\downloads";
        String Filename = "Studieplan";
        String Downloadfilepath = "C:\\Users\\johan\\OneDrive\\Skrivbord\\java\\openjfx-20_windows-x64_bin-sdk\\javafx-sdk-20\\lib\\untitled3\\build\\downloads";
        Path Sourcepath = Path.of(Downloadfilepath);
        Path Destinationpath = Path.of(Filename);

        try {
            Files.move(Sourcepath, Destinationpath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved in the correct folder.");
        } catch (Exception e) {
            System.out.println("Failed to save the file in the correct folder: " + e.getMessage());
        }

// Step 5: Check if the file exists in the specified folder
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
