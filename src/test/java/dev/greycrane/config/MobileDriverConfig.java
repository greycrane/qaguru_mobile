package dev.greycrane.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})

public interface MobileDriverConfig extends org.aeonbits.owner.Config {

    @Key("deviceName")
    String getDeviceName();

    @Key("platformVersion")
    String getPlatformVersion();

    @Key("platformName")
    String getPlatformName();

    @Key("projectName")
    String getProjectName();

    @Key("buildName")
    String getBuildName();

    @Key("testName")
    String getTestName();

    @Key("osVersion")
    String getOsVersion();

    @Key("browserstack.user")
    String getUser();

    @Key("browserstack.key")
    String getKey();

    @Key("localUrl")
    String getLocalUrl();

    @Key("app")
    String getApp();

    @Key("appPath")
    String getAppPath();

    @Key("launchUrl")
    String getLaunchUrl();

    @Key("appPackage")
    String getAppPackage();

    @Key("appActivity")
    String getAppActivity();
}

