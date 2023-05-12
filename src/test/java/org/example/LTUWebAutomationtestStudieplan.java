package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.util.Scanner;

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
        void testLTU() throws FileNotFoundException, InterruptedException {

            // Click on Cookie
            $(By.xpath("/html/body/div[1]/div/div[4]/div[1]/div[2]/button[4]")).click();
            // Click on utbildning
            $(By.xpath("/html/body/main/div/div/div[1]/div/div[3]/div/div/div/div/div/a")).click();
            $(By.xpath("/html/body/main/div/div/div/div[1]/nav/div/ul/li[1]/div/a")).click();
            $(By.xpath("/html/body/main/div/div/div/div[1]/nav/div/ul/li[1]/ul/li[4]/div/a")).click();
            $(By.xpath("/html/body/main/div/div/div/div[1]/nav/div/ul/li[7]/a")).click();
            $(By.xpath("/html/body/main/div/div/div/div[2]/div/article/div[1]/section/div[5]/aside/div[3]/a/div")).click();
            $(By.xpath("/html/body/main/div/div/div/div[2]/div/article/section/form/a")).click();


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

