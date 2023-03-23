package dev.greycrane.drivers;

import com.codeborne.selenide.WebDriverProvider;
import dev.greycrane.config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    public static BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        mutableCapabilities.setCapability("browserstack.user", config.getLogin());
        mutableCapabilities.setCapability("browserstack.key", config.getPassword());
        mutableCapabilities.setCapability("app", config.getAppUrl());
        mutableCapabilities.setCapability("device", config.getDevice());
        mutableCapabilities.setCapability("os_version", config.getOsVersion());
        mutableCapabilities.setCapability("project", config.getProjectName());
        mutableCapabilities.setCapability("build", config.getBuildName());
        mutableCapabilities.setCapability("name", config.getTestName());

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(config.getRemoteUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}