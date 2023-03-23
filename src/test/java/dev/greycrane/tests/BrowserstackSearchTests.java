package dev.greycrane.tests;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("mobile")
public class BrowserstackSearchTests extends TestBase {

    @Tag("android")
    @DisplayName("Поиск статьи про Java в Википедии")
    @Test
    void searchTestSample() {
        step("Поиск статьи в Википедии", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Проверка найденных результатов", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Tag("android")
    @DisplayName("Поиск статьи про Испанию в Википедии")
    @Test
    void searchTestMyOwn() {
        step("Поиск статьи в Википедии", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Spain");
        });
        step("Проверка найденных результатов", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(itemWithText("Spain")));
    }

    @Tag("ios")
    @DisplayName("Переход на вкладку Show HN")
    @Test
    void goToShowHNTab() {
        step("Перейти на вкладку Show HN", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(Selectors.byText("Show HN")).click();
        });
        step("Проверка списка новостей", () ->
                $$x("/XCUIElementTypeApplication/XCUIElementTypeWindow[1]" +
                        "/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/" +
                        "XCUIElementTypeOther/XCUIElementTypeTable").shouldHave(itemWithText("Show HN:")));
    }
}
