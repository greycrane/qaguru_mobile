package dev.greycrane.drivers;

import com.codeborne.selenide.WebDriverProvider;
import dev.greycrane.config.MobileDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    public static MobileDriverConfig config = ConfigFactory.create(MobileDriverConfig.class, System.getProperties());

    public WebDriver createDriver(Capabilities capabilities) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("user", config.getUser());
        mutableCapabilities.setCapability("key", config.getKey());
        mutableCapabilities.setCapability("app", config.getApp());
        mutableCapabilities.setCapability("deviceName", config.getDeviceName());
        mutableCapabilities.setCapability("osVersion", config.getOsVersion());
        mutableCapabilities.setCapability("project", config.getProjectName());
        mutableCapabilities.setCapability("build", config.getBuildName());
        mutableCapabilities.setCapability("appActivity", config.getAppActivity());
        mutableCapabilities.setCapability("name", config.getTestName());

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(config.getLaunchUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}