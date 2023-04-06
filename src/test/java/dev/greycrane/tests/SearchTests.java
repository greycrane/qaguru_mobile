package dev.greycrane.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("mobile")
public class SearchTests extends TestBase {

    @Tag("android")
    @DisplayName("Поиск статьи про Java в Википедии")
    @Test
    void searchTestSample() {
        step("Пропуск онбординга", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
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
        step("Пропуск онбординга", () ->
                $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
        step("Поиск статьи в Википедии", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Spain");
        });
        step("Проверка найденных результатов", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(itemWithText("Spain")));
    }

    @Tag("android")
    @DisplayName("Проверка онбординг экранов")
    @Test
    void threeContinueOnboardingScreen() {
        step("Проверка текста на первом экране онбординга", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Send anonymous data"));
        });
        step("Переход на второй экран онбординга", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Проверка текста на втором экране онбординга", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Send anonymous data"));
        });
        step("Переход на третий экран онбординга", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Проверка текста на третьем экране онбординга", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Send anonymous data"));
        });
        step("Переход на четвёртый экран онбординга", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Проверка текста на четвёртом экране онбординга", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Send anonymous data"));
        });
    }

    @Tag("ios")
    @DisplayName("Проверка отображения введённого текста")
    @Test
    void goToShowHNTab() {
        step("Ввод текста", () -> {
            $(id("Text Button")).click();
            $(id("Text Input")).sendKeys("some random text");
            $(id("Text Input")).pressEnter();
        });
        step("Проверка текста", () -> {
            $(id("Text Output")).shouldHave(text("some random text"));
        });
    }
}