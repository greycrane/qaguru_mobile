package dev.greycrane.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import dev.greycrane.drivers.BrowserstackMobileDriver;
import dev.greycrane.drivers.LocalMobileDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import dev.greycrane.helpers.Attach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    public static String env = System.getProperty("env");

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = BrowserstackMobileDriver.class.getName();
        Configuration.browserSize = null;

        if (env == null) {
            env = "androidBrowserstack";
        }

        switch (env) {
            case "androidEmulator":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            case "androidBrowserstack":
            case "iosBrowserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
        }
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {

        String sessionId = Selenide.sessionId().toString();

        Attach.pageSource();

        closeWebDriver();

        switch (env) {
            case "android":
            case "iphone":
                Attach.addVideo(sessionId);
                break;
        }
    }
}
