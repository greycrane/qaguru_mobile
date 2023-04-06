package dev.greycrane.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})

public interface MobileDriverConfig extends org.aeonbits.owner.Config {

    @Key("device")
    String getDeviceName();

    @Key("platformName")
    String getPlatformName();

    @Key("platformVersion")
    String getPlatformVersion();

    @Key("project")
    String getProject();

    @Key("build")
    String getBuild();

    @Key("name")
    String getName();

    @Key("browserstack.user")
    String getUser();

    @Key("browserstack.key")
    String getKey();

    @Key("app")
    String getApp();

    @Key("launchUrl")
    String getLaunchUrl();

    @Key("appPath")
    String getAppPath();

    @Key("appPackage")
    String getAppPackage();

    @Key("appActivity")
    String getAppActivity();
}